package com.avramko.electroniclibrary.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/")
@Controller
public class IndexController {
	
	@Autowired
	MessageSource messageSource;
	
	@ModelAttribute("menuPrams")
	public List<String> getCurrenrURL(HttpServletRequest request, Locale locale) {
		List<String> params = new ArrayList<String>();
		params.add(request.getContextPath()+"/books");
		params.add(messageSource.getMessage("action_home_book", new Object[]{}, locale));
		return params;
	}
	
	@RequestMapping(method=RequestMethod.GET)
    public String index(Model uiModel, HttpServletRequest request, Locale locale) {
		uiModel.addAttribute("textHeader", messageSource.getMessage("application_name", new Object[]{}, locale));
		if (request.getUserPrincipal() != null) {
			return "redirect:/books";
		} else {
			return "/";
		}
    }
    
}