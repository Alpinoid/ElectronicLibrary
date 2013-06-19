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

import com.avramko.electroniclibrary.domain.Books;
import com.avramko.electroniclibrary.domain.Tags;
import com.avramko.electroniclibrary.service.BooksService;
import com.avramko.electroniclibrary.service.TagsService;
import com.avramko.electroniclibrary.web.form.Message;
import com.avramko.electroniclibrary.web.form.PageParams;
import com.avramko.electroniclibrary.web.util.UrlUtil;


@RequestMapping("/tags")
@Controller
public class TagsController {
	
	@ModelAttribute("pageParams")
	public PageParams setPageParams(HttpServletRequest httpServletRequest, Locale locale) {
		PageParams pageParams = new PageParams();
		pageParams.setMenuText("action_home_tag", messageSource, locale);
		pageParams.setMenuUrl("/tags", httpServletRequest);
		return pageParams;
	}
    
	/**
	 * @uml.property  name="messageSource"
	 * @uml.associationEnd  readOnly="true"
	 */
	@Autowired
	MessageSource messageSource;
	
	/**
	 * @uml.property  name="tagService"
	 * @uml.associationEnd  readOnly="true"
	 */
	@Autowired
    private TagsService tagService;
	
	/**
	 * @uml.property  name="bookService"
	 * @uml.associationEnd  readOnly="true"
	 */
	@Autowired
    private BooksService bookService;

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(method=RequestMethod.GET)
    public String list(@ModelAttribute("pageParams") PageParams pageParams, Model uiModel, Locale locale) {
    	List<Tags> tags = tagService.getAllTags();
    	pageParams.setHeaderText("label_tag_list", messageSource, locale);

    	uiModel.addAttribute("tags", tags);
    	
    	return "tags/list";
    }
    
    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public String view(@ModelAttribute("pageParams") PageParams pageParams,
    				   @PathVariable("id") Integer id, Model uiModel, Locale locale) {
    	Tags tag = tagService.getTagById(id);
    	List<Books> books = bookService.getBooksByTag(tag);
    	pageParams.setHeaderText("label_tag_info", messageSource, locale);

    	uiModel.addAttribute("tag", tag);
    	uiModel.addAttribute("listBooks", books);
    	
    	return "tags/view";
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.POST)
    public String update(@ModelAttribute("tag") @Valid Tags tag,
    					 BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest,
    					 RedirectAttributes redirectAttributes, Locale locale) {
		
        if (bindingResult.hasErrors()) {
        	uiModel.addAttribute("message", new Message("error", messageSource.getMessage("message_save_fail", new Object[]{}, locale)));        	
            uiModel.addAttribute("tag", tag);
            return "tags/update";
        }
        uiModel.asMap().clear();
        redirectAttributes.addFlashAttribute("message", new Message("success", messageSource.getMessage("message_save_success", new Object[]{}, locale)));   
        
        tagService.save(tag);
        return "redirect:/tags/" + UrlUtil.encodeUrlPathSegment(tag.getIdTags().toString(), httpServletRequest);
    }	

    @PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
    public String updateForm(@ModelAttribute("pageParams") PageParams pageParams,
    						 @PathVariable("id") Integer id, Model uiModel, Locale locale) {
    	pageParams.setHeaderText("label_tag_update", messageSource, locale);

        uiModel.addAttribute("tag", tagService.getTagById(id));

        return "tags/update";
	}
	
    @PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(params = "form", method = RequestMethod.POST)
    public String create(@ModelAttribute("tag") @Valid Tags tag,
    					 BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest,
    					 RedirectAttributes redirectAttributes, Locale locale) {

        if (bindingResult.hasErrors()) {
			uiModel.addAttribute("message", new Message("error", messageSource.getMessage("message_save_fail", new Object[]{}, locale)));
            uiModel.addAttribute("tag", tag);
            return "tags/create";
        }
        uiModel.asMap().clear();
        redirectAttributes.addFlashAttribute("message", new Message("success", messageSource.getMessage("message_save_success", new Object[]{}, locale)));     
        
        tagService.save(tag);
        return "redirect:/tags/" + UrlUtil.encodeUrlPathSegment(tag.getIdTags().toString(), httpServletRequest);
    }	

    @PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(params = "form", method = RequestMethod.GET)
    public String createForm(@ModelAttribute("pageParams") PageParams pageParams, Model uiModel, Locale locale) {
		Tags tag = new Tags();
		pageParams.setHeaderText("label_tag_new", messageSource, locale);

        uiModel.addAttribute("tag", tag);

        return "tags/create";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/{id}", params = "delete", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Integer id) {
        tagService.delete(tagService.getTagById(id));
        return "redirect:/tags";
	}
    
}