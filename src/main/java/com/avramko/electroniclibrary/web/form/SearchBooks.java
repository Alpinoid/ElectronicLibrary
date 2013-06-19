package com.avramko.electroniclibrary.web.form;

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
	
	public SearchBooks() {
		this.searchField = SearchFieldsOfBooks.Fields.NAME;
		this.searchString = "";	
	}

	public SearchBooks(SearchFieldsOfBooks.Fields searchField, String searchString) {
		this.searchField = searchField;
		this.searchString = searchString;	
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

}
