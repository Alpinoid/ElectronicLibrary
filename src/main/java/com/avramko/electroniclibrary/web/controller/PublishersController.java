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
import com.avramko.electroniclibrary.domain.Publishers;
import com.avramko.electroniclibrary.service.BooksService;
import com.avramko.electroniclibrary.service.PublishersService;
import com.avramko.electroniclibrary.web.form.Message;
import com.avramko.electroniclibrary.web.form.PageParams;
import com.avramko.electroniclibrary.web.util.UrlUtil;


@RequestMapping("/publishers")
@Controller
public class PublishersController {
	
	@ModelAttribute("pageParams")
	public PageParams setPageParams(HttpServletRequest httpServletRequest, Locale locale) {
		PageParams pageParams = new PageParams();
		pageParams.setMenuText("action_home_publisher", messageSource, locale);
		pageParams.setMenuUrl("/publishers", httpServletRequest);
		return pageParams;
	}
    
	/**
	 * @uml.property  name="messageSource"
	 * @uml.associationEnd  readOnly="true"
	 */
	@Autowired
	MessageSource messageSource;
	
	/**
	 * @uml.property  name="publisherService"
	 * @uml.associationEnd  readOnly="true"
	 */
	@Autowired
    private PublishersService publisherService;
	
	/**
	 * @uml.property  name="bookService"
	 * @uml.associationEnd  readOnly="true"
	 */
	@Autowired
    private BooksService bookService;

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(method=RequestMethod.GET)
    public String list(@ModelAttribute("pageParams") PageParams pageParams, Model uiModel, Locale locale) {
    	List<Publishers> publishers = publisherService.getAllPublishers();
    	pageParams.setHeaderText("label_publisher_list", messageSource, locale);

    	uiModel.addAttribute("publishers", publishers);
    	
    	return "publishers/list";
    }
    
    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    public String view(@ModelAttribute("pageParams") PageParams pageParams,
    				   @PathVariable("id") Integer id, Model uiModel, Locale locale) {
    	Publishers publisher = publisherService.getPublisherById(id);
    	List<Books> books = bookService.getBooksByPublisher(publisher);
    	pageParams.setHeaderText("label_publisher_info", messageSource, locale);
    	
    	uiModel.addAttribute("publisher", publisher);
    	uiModel.addAttribute("listBooks", books);
    	
    	return "publishers/view";
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.POST)
    public String update(@ModelAttribute("publisher") @Valid Publishers publisher,
    					 BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest,
    					 RedirectAttributes redirectAttributes, Locale locale) {
		
        if (bindingResult.hasErrors()) {
        	uiModel.addAttribute("message", new Message("error", messageSource.getMessage("message_save_fail", new Object[]{}, locale)));        	
            uiModel.addAttribute("publisher", publisher);
            return "publishers/update";
        }
        uiModel.asMap().clear();
        redirectAttributes.addFlashAttribute("message", new Message("success", messageSource.getMessage("message_save_success", new Object[]{}, locale)));   
        
        publisherService.save(publisher);
        return "redirect:/publishers/" + UrlUtil.encodeUrlPathSegment(publisher.getIdPublishers().toString(), httpServletRequest);
    }	

    @PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
    public String updateForm(@ModelAttribute("pageParams") PageParams pageParams,
    		                 @PathVariable("id") Integer id, Model uiModel, Locale locale) {
    	pageParams.setHeaderText("label_publisher_update", messageSource, locale);

        uiModel.addAttribute("publisher", publisherService.getPublisherById(id));

        return "publishers/update";
	}
	
    @PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(params = "form", method = RequestMethod.POST)
    public String create(@ModelAttribute("publisher") @Valid Publishers publisher,
    					 BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest,
    					 RedirectAttributes redirectAttributes, Locale locale) {

        if (bindingResult.hasErrors()) {
			uiModel.addAttribute("message", new Message("error", messageSource.getMessage("message_save_fail", new Object[]{}, locale)));
            uiModel.addAttribute("publisher", publisher);
            return "publishers/create";
        }
        uiModel.asMap().clear();
        redirectAttributes.addFlashAttribute("message", new Message("success", messageSource.getMessage("message_save_success", new Object[]{}, locale)));     
        
        publisherService.save(publisher);
        return "redirect:/publishers/" + UrlUtil.encodeUrlPathSegment(publisher.getIdPublishers().toString(), httpServletRequest);
    }	

    @PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(params = "form", method = RequestMethod.GET)
    public String createForm(@ModelAttribute("pageParams") PageParams pageParams, Model uiModel, Locale locale) {
		Publishers publisher = new Publishers();
		pageParams.setHeaderText("label_publisher_new", messageSource, locale);

        uiModel.addAttribute("publisher", publisher);

        return "publishers/create";
    }
	
    @PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/{id}", params = "delete", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Integer id) {
		publisherService.delete(publisherService.getPublisherById(id));
        return "redirect:/publishers";
	}
    
}