package com.avramko.electroniclibrary.web.form;

import com.avramko.electroniclibrary.domain.Tags;

public class SearchBooks {
	
	private SearchFieldsOfBooks.Fields searchField;
	private String searchString;
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

	public Tags getSearchTag() {
		return searchTag;
	}

	public void setSearchTag(Tags searchTag) {
		this.searchTag = searchTag;
	}

}
