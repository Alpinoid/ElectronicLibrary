package com.avramko.electroniclibrary.web.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.avramko.electroniclibrary.web.form.PageParams;

@RequestMapping("/")
@Controller
public class IndexController {

	@Autowired
	MessageSource messageSource;
	
	@ModelAttribute("pageParams")
	public PageParams setPageParams(HttpServletRequest httpServletRequest, Locale locale) {
		PageParams pageParams = new PageParams();
		pageParams.setMenuText("action_home_book", messageSource, locale);
		pageParams.setMenuUrl("/books", httpServletRequest);
		return pageParams;
	}
	
	@RequestMapping(method=RequestMethod.GET)
    public String index(@ModelAttribute("pageParams") PageParams pageParams,
    					Model uiModel, HttpServletRequest request, Locale locale) {
		pageParams.setHeaderText("", messageSource, locale);
		
		if (request.getUserPrincipal() != null) {
			return "redirect:/books";
		} else {
			return "/";
		}
    }
    
}