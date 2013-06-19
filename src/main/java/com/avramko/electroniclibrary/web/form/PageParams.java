package com.avramko.electroniclibrary.web.form;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.MessageSource;

public class PageParams {
	
	/**
	 * @uml.property  name="menuText"
	 */
	private String menuText;
	/**
	 * @uml.property  name="menuUrl"
	 */
	private String menuUrl;
	/**
	 * @uml.property  name="headerText"
	 */
	private String headerText;
	
	public PageParams() {
	}

	/**
	 * @return
	 * @uml.property  name="menuText"
	 */
	public String getMenuText() {
		return menuText;
	}

	/**
	 * @param menuText
	 * @param messageSource
	 * @param locale
	 * @uml.property  name="menuText"
	 */
	public void setMenuText(String menuText, MessageSource messageSource, Locale locale) {
		this.menuText = messageSource.getMessage(menuText, new Object[]{}, locale);
	}

	/**
	 * @return
	 * @uml.property  name="menuUrl"
	 */
	public String getMenuUrl() {
		return menuUrl;
	}

	/**
	 * @param menuUrl
	 * @param httpServletRequest
	 * @uml.property  name="menuUrl"
	 */
	public void setMenuUrl(String menuUrl, HttpServletRequest httpServletRequest) {
		this.menuUrl = httpServletRequest.getContextPath()+menuUrl;
	}

	/**
	 * @return
	 * @uml.property  name="headerText"
	 */
	public String getHeaderText() {
		return headerText;
	}

	/**
	 * @param headerText
	 * @param messageSource
	 * @param locale
	 * @uml.property  name="headerText"
	 */
	public void setHeaderText(String headerText, MessageSource messageSource, Locale locale) {
		if (headerText == "") {
			this.headerText = messageSource.getMessage("application_name", new Object[]{}, locale);
		} else {
			this.headerText = messageSource.getMessage("application_name", new Object[]{}, locale) + " - " +
							  messageSource.getMessage(headerText, new Object[]{}, locale);
		}
	}

}
