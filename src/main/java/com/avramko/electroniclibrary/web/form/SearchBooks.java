package com.avramko.electroniclibrary.web.form;

import com.avramko.electroniclibrary.domain.Tags;

public class SearchBooks {
	
	/**
	 * @uml.property  name="searchField"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private SearchFieldsOfBooks.Fields searchField;
	/**
	 * @uml.property  name="searchString"
	 */
	private String searchString;
	/**
	 * @uml.property  name="searchTag"
	 */	
	private Tags searchTag;
	
	public SearchBooks() {
		this.searchField = SearchFieldsOfBooks.Fields.NAME;
		this.searchString = "";	
		this.searchTag = null;	
	}

	public SearchBooks(SearchFieldsOfBooks.Fields searchField, String searchString) {
		this.searchField = searchField;
		this.searchString = searchString;	
		this.searchTag = null;	
	}

	/**
	 * @return
	 * @uml.property  name="searchField"
	 */
	public SearchFieldsOfBooks.Fields getSearchField() {
		return searchField;
	}

	/**
	 * @param searchField
	 * @uml.property  name="searchField"
	 */
	public void setSearchField(SearchFieldsOfBooks.Fields searchField) {
		this.searchField = searchField;
	}

	/**
	 * @return
	 * @uml.property  name="searchString"
	 */
	public String getSearchString() {
		return searchString;
	}

	/**
	 * @param searchString
	 * @uml.property  name="searchString"
	 */
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}
	
	/**
	 * @return
	 * @uml.property  name="searchTag"
	 */
	public Tags getSearchTag() {
		return searchTag;
	}
	
	/**
	 * @param searchTag
	 * @uml.property  name="searchTag"
	 */
	public void setSearchTag(Tags searchTag) {
		this.searchTag = searchTag;
	}

}
