package com.avramko.electroniclibrary.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.avramko.electroniclibrary.security.LibraryUser;
import com.avramko.electroniclibrary.web.form.Message;
import com.avramko.electroniclibrary.web.validator.UserValidator;

@RequestMapping("/security")
@Controller
public class SecurityController {
	
	static final Logger logger = LoggerFactory.getLogger(SecurityController.class);

	@Autowired
	private MessageSource messageSource;

	@Autowired
    private JdbcUserDetailsManager jdbcUserDetailsManager;
	
	private void doAutoLogin(String username, String password, HttpServletRequest httpServletRequest) {
	    try {
	    	UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
	        SecurityContextHolder.getContext().setAuthentication(token);
	        logger.info("Autologin success fo user: {}", username);
	    } catch (Exception e) {
	        SecurityContextHolder.getContext().setAuthentication(null);
	        logger.info("Autologin fail fo user: {}", username);
	    }
	}
		
	@RequestMapping("/loginfail")
	public String loginFail(Model uiModel, Locale locale) {
		uiModel.addAttribute("message", new Message("error", messageSource.getMessage("message_login_fail", new Object[]{}, locale)));
		logger.info("Login user fail!");
		return "/";
	}
	
	@RequestMapping(params="registration", method=RequestMethod.POST)
	public String registration(@ModelAttribute("user") LibraryUser user, BindingResult bindingResult, HttpServletRequest httpServletRequest, Model uiModel, Locale locale) {
		
		UserValidator userValidator = new UserValidator();
		userValidator.validate(user, bindingResult);	
		if (jdbcUserDetailsManager.userExists(user.getUsername())) {
			bindingResult.rejectValue("username", "validation_user_is_exist");
		}

        if (bindingResult.hasErrors()) {
			uiModel.addAttribute("message", new Message("error", messageSource.getMessage("message_save_fail", new Object[]{}, locale)));
            uiModel.addAttribute("LibraryUser", user);
            return "security/registration";
        }

        List<GrantedAuthority> authorityList = new ArrayList<GrantedAuthority>();
    	authorityList.add(new SimpleGrantedAuthority("ROLE_USER"));
        User newUser = new User(user.getUsername(), user.getPassword(), authorityList);
        uiModel.asMap().clear();
        
        jdbcUserDetailsManager.createUser(newUser);
        logger.info("Registred new user: {}", newUser);
        doAutoLogin(newUser.getUsername(), newUser.getPassword(), httpServletRequest);
		
		return "redirect:/";
	}
	
	@RequestMapping(params="registration", method = RequestMethod.GET)
    public String registrationForm(Model uiModel) {
		LibraryUser user = new LibraryUser();
        uiModel.addAttribute("user", user);
        return "security/registration";
    }
}
