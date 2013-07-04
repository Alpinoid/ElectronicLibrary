package com.avramko.electroniclibrary.web.form;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.MessageSource;

public class PageParams {

	private String menuText;
	private String menuUrl;
	private String headerText;
	
	public PageParams() {
	}

	public String getMenuText() {
		return menuText;
	}

	public void setMenuText(String menuText, MessageSource messageSource, Locale locale) {
		this.menuText = messageSource.getMessage(menuText, new Object[]{}, locale);
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl, HttpServletRequest httpServletRequest) {
		this.menuUrl = httpServletRequest.getContextPath()+menuUrl;
	}

	public String getHeaderText() {
		return headerText;
	}

	public void setHeaderText(String headerText, MessageSource messageSource, Locale locale) {
		if (headerText == "") {
			this.headerText = messageSource.getMessage("application_name", new Object[]{}, locale);
		} else {
			this.headerText = messageSource.getMessage("application_name", new Object[]{}, locale) + " - " +
							  messageSource.getMessage(headerText, new Object[]{}, locale);
		}
	}

}
