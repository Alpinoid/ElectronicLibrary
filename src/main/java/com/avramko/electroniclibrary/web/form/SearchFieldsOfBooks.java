package com.avramko.electroniclibrary.web.form;

public class SearchFieldsOfBooks {
	
	/**
	 * @author   Valentin Avramko
	 */
	public static enum Fields {
		/**
		 * @uml.property  name="nAME"
		 * @uml.associationEnd  
		 */
		NAME("по имени"),
		/**
		 * @uml.property  name="dESCRIPTION"
		 * @uml.associationEnd  
		 */
		DESCRIPTION("по описанию");
		
		/**
		 * @uml.property  name="field"
		 */
		private String field;
		
	    private Fields(String searchField){
	    	field = searchField;
	    }
		
		/**
		 * @return
		 * @uml.property  name="field"
		 */
		public String getField(){
	      return field;
	    }
	}

}
