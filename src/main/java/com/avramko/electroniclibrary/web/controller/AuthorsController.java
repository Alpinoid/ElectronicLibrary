package com.avramko.electroniclibrary.web.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.avramko.electroniclibrary.domain.Authors;
import com.avramko.electroniclibrary.domain.Books;
import com.avramko.electroniclibrary.service.AuthorsService;
import com.avramko.electroniclibrary.service.BooksService;
import com.avramko.electroniclibrary.web.form.Message;
import com.avramko.electroniclibrary.web.form.PageParams;
import com.avramko.electroniclibrary.web.util.UrlUtil;


@RequestMapping("/authors")
@Controller
public class AuthorsController {
	
	@ModelAttribute("pageParams")
	public PageParams setPageParams(HttpServletRequest httpServletRequest, Locale locale) {
		PageParams pageParams = new PageParams();
		pageParams.setMenuText("action_home_author", messageSource, locale);
		pageParams.setMenuUrl("/authors", httpServletRequest);
		return pageParams;
	}
    
	@Autowired
	MessageSource messageSource;
	
	@Autowired
    private AuthorsService authorService;
	
	@Autowired
    private BooksService bookService;

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(method=RequestMethod.GET)
    public String list(@ModelAttribute("pageParams") PageParams pageParams, Model uiModel, Locale locale) {
    	List<Authors> authors = authorService.getAllAuthors();
    	pageParams.setHeaderText("label_author_list", messageSource, locale);
    	
    	uiModel.addAttribute("authors", authors);
    	
    	return "authors/list";
    }
    
    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public String view(@ModelAttribute("pageParams") PageParams pageParams,
    				   @PathVariable("id") Integer id, Model uiModel, Locale locale) {
    	Authors author = authorService.getAuthorById(id);
    	List<Books> books = bookService.getBooksByAuthor(author);
    	pageParams.setHeaderText("label_author_info", messageSource, locale);
    	
    	uiModel.addAttribute("author", author);
    	uiModel.addAttribute("listBooks", books);
    	
    	return "authors/view";
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.POST)
    public String update(@ModelAttribute("author") @Valid Authors author,
    					 BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest,
    					 RedirectAttributes redirectAttributes, Locale locale) {
		
        if (bindingResult.hasErrors()) {
        	uiModel.addAttribute("message", new Message("error", messageSource.getMessage("message_save_fail", new Object[]{}, locale)));        	
            uiModel.addAttribute("author", author);
            return "authors/update";
        }
        uiModel.asMap().clear();
        redirectAttributes.addFlashAttribute("message", new Message("success", messageSource.getMessage("message_save_success", new Object[]{}, locale)));   
        
        authorService.save(author);
        return "redirect:/authors/" + UrlUtil.encodeUrlPathSegment(author.getIdAuthors().toString(), httpServletRequest);
    }	

    @PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
    public String updateForm(@ModelAttribute("pageParams") PageParams pageParams,
    						 @PathVariable("id") Integer id, Model uiModel, Locale locale) {
    	pageParams.setHeaderText("label_author_update", messageSource, locale);

        uiModel.addAttribute("author", authorService.getAuthorById(id));

        return "authors/update";
	}
	
    @PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(params = "form", method = RequestMethod.POST)
    public String create(@ModelAttribute("author") @Valid Authors author,
    					 BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest,
    					 RedirectAttributes redirectAttributes, Locale locale) {

        if (bindingResult.hasErrors()) {
			uiModel.addAttribute("message", new Message("error", messageSource.getMessage("message_save_fail", new Object[]{}, locale)));
            uiModel.addAttribute("author", author);
            return "authors/create";
        }
        uiModel.asMap().clear();
        redirectAttributes.addFlashAttribute("message", new Message("success", messageSource.getMessage("message_save_success", new Object[]{}, locale)));     
        
        authorService.save(author);
        return "redirect:/authors/" + UrlUtil.encodeUrlPathSegment(author.getIdAuthors().toString(), httpServletRequest);
    }	

    @PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(params = "form", method = RequestMethod.GET)
    public String createForm(@ModelAttribute("pageParams") PageParams pageParams, Model uiModel, Locale locale) {
		Authors author = new Authors();
		pageParams.setHeaderText("label_author_new", messageSource, locale);

        uiModel.addAttribute("author", author);

        return "authors/create";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/{id}", params = "delete", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Integer id) {
        authorService.delete(authorService.getAuthorById(id));
        return "redirect:/authors";
	}
    
}