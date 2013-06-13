package com.avramko.electroniclibrary.web.form;

public class SearchBooks {
	
	private SearchFieldsOfBooks.Fields searchField;
	private String searchString;
	
	public SearchBooks() {
		this.searchField = SearchFieldsOfBooks.Fields.NAME;
		this.searchString = "";	
	}

	public SearchBooks(SearchFieldsOfBooks.Fields searchField, String searchString) {
		this.searchField = searchField;
		this.searchString = searchString;	
	}

	public SearchFieldsOfBooks.Fields getSearchField() {
		return searchField;
	}

	public void setSearchField(SearchFieldsOfBooks.Fields searchField) {
		this.searchField = searchField;
	}

	public String getSearchString() {
		return searchString;
	}

	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

}
