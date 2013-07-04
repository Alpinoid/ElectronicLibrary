package com.avramko.electroniclibrary.web.form;

public class SearchFieldsOfBooks {
	
	public static enum Fields {

		NAME("по имени"),
		DESCRIPTION("по описанию");

		private String field;
		
	    private Fields(String searchField){
	    	field = searchField;
	    }

		public String getField(){
	      return field;
	    }
	}

}
