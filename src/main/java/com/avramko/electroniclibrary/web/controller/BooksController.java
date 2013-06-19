package com.avramko.electroniclibrary.web.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.avramko.electroniclibrary.domain.Authors;
import com.avramko.electroniclibrary.domain.Books;
import com.avramko.electroniclibrary.domain.Comments;
import com.avramko.electroniclibrary.domain.FilesOfBook;
import com.avramko.electroniclibrary.domain.Tags;
import com.avramko.electroniclibrary.service.AuthorsService;
import com.avramko.electroniclibrary.service.BooksService;
import com.avramko.electroniclibrary.service.CommentsService;
import com.avramko.electroniclibrary.service.PublishersService;
import com.avramko.electroniclibrary.service.TagsService;
import com.avramko.electroniclibrary.web.form.Message;
import com.avramko.electroniclibrary.web.form.PageParams;
import com.avramko.electroniclibrary.web.form.RecordsOnPage;
import com.avramko.electroniclibrary.web.form.SearchBooks;
import com.avramko.electroniclibrary.web.form.SearchFieldsOfBooks;
import com.avramko.electroniclibrary.web.util.UrlUtil;
import com.avramko.electroniclibrary.web.validator.BooksFileValidator;
import com.avramko.electroniclibrary.web.validator.BooksImageValidator;

@RequestMapping("/books")
@Controller
@SessionAttributes({"searchBook","pageRequest"})
public class BooksController {
	
	@ModelAttribute("pageParams")
	public PageParams setPageParams(HttpServletRequest httpServletRequest, Locale locale) {
		PageParams pageParams = new PageParams();
		pageParams.setMenuText("action_all_book", messageSource, locale);
		pageParams.setMenuUrl("/books", httpServletRequest);
		return pageParams;
	}
	
	@ModelAttribute("searchBook")
	public SearchBooks getSearchBooks() {  
        return new SearchBooks();
	}
	
	@ModelAttribute("pageRequest")
	public PageRequest getPageRequest() {  
        return new PageRequest(0, RecordsOnPage.show.BY_10.getNumberOfRecords(), Direction.ASC, "booksName");
	}
	
	/**
	 * @uml.property  name="messageSource"
	 * @uml.associationEnd  readOnly="true"
	 */
	@Autowired
	MessageSource messageSource;
	
	/**
	 * @uml.property  name="bookService"
	 * @uml.associationEnd  readOnly="true"
	 */
	@Autowired
    private BooksService bookService;
	
	/**
	 * @uml.property  name="authorService"
	 * @uml.associationEnd  readOnly="true"
	 */
	@Autowired
    private AuthorsService authorService;
	
	/**
	 * @uml.property  name="publisherService"
	 * @uml.associationEnd  readOnly="true"
	 */
	@Autowired
    private PublishersService publisherService;
	
	/**
	 * @uml.property  name="tagService"
	 * @uml.associationEnd  readOnly="true"
	 */
	@Autowired
    private TagsService tagService;
	
	/**
	 * @uml.property  name="commentService"
	 * @uml.associationEnd  readOnly="true"
	 */
	@Autowired
    private CommentsService commentService;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) throws Exception {
		
		binder.registerCustomEditor(Set.class, "authorsOfBooks", new CustomCollectionEditor(Set.class) {
	        protected Object convertElement(Object element) {
	            if (element instanceof Authors) {
	                return element;
	            }
	            if (element instanceof String) {
	                return authorService.getAuthorById(Integer.valueOf(element.toString()));
	            }
	            return null;
	        }
	    });
	    
	    binder.registerCustomEditor(Set.class, "tagsOfBooks", new CustomCollectionEditor(Set.class) {
	        protected Object convertElement(Object element) {
	            if (element instanceof Tags) {
	                return element;
	            }
	            if (element instanceof String) {
	                return tagService.getTagById(Integer.valueOf(element.toString()));
	            }
	            return null;
	        }
	    });
	}
	
	@RequestMapping(method=RequestMethod.GET)
    public String list(@ModelAttribute("searchBook") SearchBooks searchBook,
    				   @ModelAttribute("pageRequest") PageRequest pageRequest,
    				   @ModelAttribute("pageParams") PageParams pageParams,
    				   BindingResult bindingResult, Model uiModel, Locale locale) {
		
		String searchString = searchBook.getSearchString();
		if (searchString == "") {
			searchString = "%";
		} else {
			searchString = "%" + searchString + "%";
        }
		
		Page<Books> page = null;
		String searchField = searchBook.getSearchField().toString();
        page = bookService.getBooksByCustomField(searchField, searchString, pageRequest);

	    int current = page.getNumber()+1;
	    int begin = Math.max(1, current - 5);
	    int end = Math.min(begin + 10, page.getTotalPages());
	    int pageSize = page.getSize();
	    
	    pageParams.setHeaderText("label_book_list", messageSource, locale);
	
	    uiModel.addAttribute("listSearchFields", SearchFieldsOfBooks.Fields.values());
	    uiModel.addAttribute("books", page.getContent());
    	uiModel.addAttribute("beginIndex", begin);
    	uiModel.addAttribute("endIndex", end);
    	uiModel.addAttribute("pageSize", pageSize);
    	uiModel.addAttribute("currentIndex", current);
    	uiModel.addAttribute("pages", page.getTotalPages());
    	uiModel.addAttribute("recordsOnPage", RecordsOnPage.show.values());
    	
    	return "books/list";
    }
	
	@RequestMapping(params={"sort"}, method=RequestMethod.GET)
    public String list_sort(@ModelAttribute("searchBook") SearchBooks searchBook,
    						@ModelAttribute("pageRequest") PageRequest pageRequest,
    						@RequestParam(value="sort", required=false) String paramSort,
    						BindingResult bindingResult, Model uiModel) {

		int pageNumber = pageRequest.getPageNumber();
		int pageSize = pageRequest.getPageSize();
		Integer ind = paramSort.indexOf("_");
		String sortField = paramSort.substring(0, ind);
		Direction sortDirection = Sort.Direction.fromString(paramSort.substring(++ind));
		Sort sort = new Sort(sortDirection, sortField);

		PageRequest newPageRequest = new PageRequest(pageNumber, pageSize, sort);
		uiModel.addAttribute("pageRequest", newPageRequest);
		
		return "redirect:/books";
	}
	
	@RequestMapping(params = "page", method=RequestMethod.GET)
    public String list_page(@ModelAttribute("pageRequest") PageRequest pageRequest,
    				   		@RequestParam(value="page", required=false) int paramPage,
    				   		BindingResult bindingResult, Model uiModel) {
		
		int pageNumber = paramPage-1;
		int pageSize = pageRequest.getPageSize();		
		Sort paramSort = pageRequest.getSort();

		PageRequest newPageRequest = new PageRequest(pageNumber, pageSize, paramSort);
		uiModel.addAttribute("pageRequest", newPageRequest);
		
		return "redirect:/books";
	}
	
	@RequestMapping(params = "size", method=RequestMethod.GET)
    public String list_size(@ModelAttribute("pageRequest") PageRequest pageRequest,
    				   		@RequestParam(value="size", required=false) int paramSize,
    				   		BindingResult bindingResult, Model uiModel) {
		
		int pageNumber = 0;
		int pageSize = paramSize;		
		Sort paramSort = pageRequest.getSort();

		PageRequest newPageRequest = new PageRequest(pageNumber, pageSize, paramSort);
		uiModel.addAttribute("pageRequest", newPageRequest);
		
		return "redirect:/books";
	}
	
    @PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value="/{id}", method = RequestMethod.POST)
    public String addComment(@ModelAttribute("newComment") @Valid Comments newComment,
    						 @PathVariable("id") Integer id, BindingResult bindingResult, Model uiModel, 
    						 HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale) {
    	
        if (bindingResult.hasErrors()) {
			uiModel.addAttribute("message", new Message("error", messageSource.getMessage("message_save_fail", new Object[]{}, locale)));
            uiModel.addAttribute("newComment", newComment);
            return "books/view";
        }
        Books book = bookService.getBookById(id);
        newComment.setCommentBook(book);
        newComment.setCommentDate(new DateTime());
        newComment.setCommentUser(SecurityContextHolder.getContext().getAuthentication().getName());
        uiModel.asMap().clear();
        
        commentService.save(newComment);
        return "redirect:/books/" + UrlUtil.encodeUrlPathSegment(newComment.getCommentBook().getIdBooks().toString(), httpServletRequest);
    }	
    
    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    public String view(@ModelAttribute("pageParams") PageParams pageParams,
    				   @PathVariable("id") Integer id, Model uiModel, Locale locale) {
    	Books book = bookService.getBookById(id);
    	Comments newComment = new Comments(SecurityContextHolder.getContext().getAuthentication().getName(), book);
    	List<Authors> authors = authorService.getAuthorsByBook(book);
    	List<Tags> tags = tagService.getTagsByBook(book);
    	List<Comments> comments = commentService.getCommentsByBook(book);
	    pageParams.setHeaderText("label_book_info", messageSource, locale);

    	uiModel.addAttribute("book", book);
    	uiModel.addAttribute("listAuthors", authors);
    	uiModel.addAttribute("listTags", tags);
    	uiModel.addAttribute("listComments", comments);
    	uiModel.addAttribute("newComment", newComment);

    	return "books/view";
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.POST)
    public String  update(@ModelAttribute("book") @Valid Books book,
    					  BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest,
    					  RedirectAttributes redirectAttributes, Locale locale,
    					  @Valid @RequestParam(value="image", required=false) MultipartFile fileImage,
    					  @Valid @RequestParam(value="file", required=false) MultipartFile fileBook) {	
    	
    	if (fileImage.getSize() != 0) {
        	BooksImageValidator imageValidator = new BooksImageValidator();
            imageValidator.validate(fileImage, bindingResult);
            
        	byte[] fileContent = null;
        	try {
        		InputStream inputStream = fileImage.getInputStream();
        		fileContent = IOUtils.toByteArray(inputStream);
        	} catch (IOException ex) {
        		ex.printStackTrace();
        	}
        	book.setBooksImage(fileContent);
        } else {
        	book.setBooksImage(bookService.getBookById(book.getIdBooks()).getBooksImage());
        }
        
        if (fileBook.getSize() != 0) {
        	BooksFileValidator fileValidator = new BooksFileValidator();
            fileValidator.validate(fileBook, bindingResult);
            
            FilesOfBook fileOfBook = bookService.getBookById(book.getIdBooks()).getBooksFile();
        	if (fileOfBook == null) {
        		fileOfBook = new FilesOfBook(book, fileBook);
        		book.setBooksFile(fileOfBook);
        	} else {
        		fileOfBook.setFile(fileBook);
        	}
        }
        
        if (bindingResult.hasErrors()) {
        	uiModel.addAttribute("message", new Message("error", messageSource.getMessage("message_save_fail", new Object[]{}, locale)));        	
            uiModel.addAttribute("book", book);
            uiModel.addAttribute("listAuthors", authorService.getAllAuthors());
            uiModel.addAttribute("listPublishers", publisherService.getAllPublishers());
            uiModel.addAttribute("listTags", tagService.getAllTags());
            return "books/update";
        }
        
        uiModel.asMap().clear();
        redirectAttributes.addFlashAttribute("message", new Message("success", messageSource.getMessage("message_save_success", new Object[]{}, locale)));        
        
        bookService.save(book);
        return "redirect:/books/" + UrlUtil.encodeUrlPathSegment(book.getIdBooks().toString(), httpServletRequest);
    }	
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
    public String updateForm(@ModelAttribute("pageParams") PageParams pageParams,
    						 @PathVariable("id") Integer id, Model uiModel, Locale locale) {
	    pageParams.setHeaderText("label_book_update", messageSource, locale);

        uiModel.addAttribute("book", bookService.getBookById(id));
        uiModel.addAttribute("listAuthors", authorService.getAllAuthors());
        uiModel.addAttribute("listPublishers", publisherService.getAllPublishers());
        uiModel.addAttribute("listTags", tagService.getAllTags());

        return "books/update";
	}
	
    @PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(params = "form", method = RequestMethod.POST)
    public String create(@ModelAttribute("book") @Valid Books book,
    		BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest,
    		RedirectAttributes redirectAttributes, Locale locale,
    		@Valid @RequestParam(value="image", required=false) MultipartFile fileImage,
    		@Valid @RequestParam(value="file", required=false) MultipartFile fileBook) {	
        
        if (fileImage != null) {
        	BooksImageValidator imageValidator = new BooksImageValidator();
            imageValidator.validate(fileImage, bindingResult);
            
        	byte[] fileContent = null;
        	try {
        		InputStream inputStream = fileImage.getInputStream();
        		fileContent = IOUtils.toByteArray(inputStream);
        	} catch (IOException ex) {
        		ex.printStackTrace();
        	}
        	book.setBooksImage(fileContent);
        }
        
        if (fileBook.getSize() != 0) {
        	BooksFileValidator fileValidator = new BooksFileValidator();
            fileValidator.validate(fileBook, bindingResult);
            
            FilesOfBook fileOfBook = bookService.getBookById(book.getIdBooks()).getBooksFile();
        	if (fileOfBook == null) {
        		fileOfBook = new FilesOfBook(book, fileBook);
        		book.setBooksFile(fileOfBook);
        	} else {
        		fileOfBook.setFile(fileBook);
        	}
        }
        
        if (bindingResult.hasErrors()) {
			uiModel.addAttribute("message", new Message("error", messageSource.getMessage("message_save_fail", new Object[]{}, locale)));
            uiModel.addAttribute("book", book);
            uiModel.addAttribute("listAuthors", authorService.getAllAuthors());
            uiModel.addAttribute("listPublishers", publisherService.getAllPublishers());
            uiModel.addAttribute("listTags", tagService.getAllTags());
            return "books/create";
        }
        uiModel.asMap().clear();
        redirectAttributes.addFlashAttribute("message", new Message("success", messageSource.getMessage("message_save_success", new Object[]{}, locale)));

        bookService.save(book);
        return "redirect:/books/" + UrlUtil.encodeUrlPathSegment(book.getIdBooks().toString(), httpServletRequest);
    }	

    @PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(params = "form", method = RequestMethod.GET)
    public String createForm(@ModelAttribute("pageParams") PageParams pageParams, Model uiModel, Locale locale) {
		Books book = new Books();
	    pageParams.setHeaderText("label_book_new", messageSource, locale);

        uiModel.addAttribute("book", book);
        uiModel.addAttribute("listAuthors", authorService.getAllAuthors());
        uiModel.addAttribute("listPublishers", publisherService.getAllPublishers());
        uiModel.addAttribute("listTags", tagService.getAllTags());

        return "books/create";
    }
	
    @RequestMapping(value="/cover/{id}", method=RequestMethod.GET)
	@ResponseBody
	public byte[] downloadImage(@PathVariable("id") Integer id) {
		Books book = bookService.getBookById(id);
		
		return book.getBooksImage();
	}
	
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value="/download/{id}", method=RequestMethod.GET)
	@ResponseBody
	public String downloadFile(@PathVariable("id") Integer id, HttpServletResponse httpServletResponse) {
		FilesOfBook fileOfBook = bookService.getBookById(id).getBooksFile();

        try {
        	httpServletResponse.setHeader("Content-Disposition", "attachment;filename=\"" +fileOfBook.getFilesName()+ "\"");
	        OutputStream out = httpServletResponse.getOutputStream();
	        IOUtils.write(fileOfBook.getFile(), out);
	        out.close();
        } catch (IOException ex) {
        	ex.printStackTrace();
        }
        return null;
	}
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/{id}", params = "delete", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Integer id) {
		bookService.delete(bookService.getBookById(id));
        return "redirect:/books";
	}
}