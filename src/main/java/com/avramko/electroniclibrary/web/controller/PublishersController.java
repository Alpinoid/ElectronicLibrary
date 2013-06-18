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
import com.avramko.electroniclibrary.web.util.UrlUtil;


@RequestMapping("/publishers")
@Controller
public class PublishersController {
    
	@Autowired
	MessageSource messageSource;
	
	@Autowired
    private PublishersService publisherService;
	
	@Autowired
    private BooksService bookService;

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(method=RequestMethod.GET)
    public String list(Model uiModel) {
    	List<Publishers> publishers = publisherService.getAllPublishers();
    	uiModel.addAttribute("publishers", publishers);
    	
    	return "publishers/list";
    }
    
    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    public String view(@PathVariable("id") Integer id, Model uiModel) {
    	Publishers publisher = publisherService.getPublisherById(id);
    	List<Books> books = bookService.getBooksByPublisher(publisher);
    	
    	uiModel.addAttribute("publisher", publisher);
    	uiModel.addAttribute("listBooks", books);
    	
    	return "publishers/view";
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.POST)
    public String update(@ModelAttribute("publisher") @Valid Publishers publisher, BindingResult bindingResult, Model uiModel, 
    		HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale) {
		
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
    public String updateForm(@PathVariable("id") Integer id, Model uiModel) {
        uiModel.addAttribute("publisher", publisherService.getPublisherById(id));
        return "publishers/update";
	}
	
    @PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(params = "form", method = RequestMethod.POST)
    public String create(@ModelAttribute("publisher") @Valid Publishers publisher, BindingResult bindingResult, Model uiModel, 
    		HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale) {

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
    public String createForm(Model uiModel) {
		Publishers publisher = new Publishers();
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