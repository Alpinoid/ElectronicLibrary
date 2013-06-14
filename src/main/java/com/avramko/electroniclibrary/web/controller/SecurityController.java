package com.avramko.electroniclibrary.web.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.avramko.electroniclibrary.web.form.Message;

@RequestMapping("/security")
@Controller
public class SecurityController {
	
	@Autowired
	private MessageSource messageSource;	
	
	@RequestMapping("/loginfail")
	public String loginFail(Model uiModel, Locale locale) {

		uiModel.addAttribute("message", new Message("error", messageSource.getMessage("message_login_fail", new Object[]{}, locale))); 
		return "/";
	}
	
}
