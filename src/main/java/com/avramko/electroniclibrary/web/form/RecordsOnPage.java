package com.avramko.electroniclibrary.web.form;

public class RecordsOnPage{
	
	/**
	 * @author   Valentin Avramko
	 */
	public static enum show {
		/**
		 * @uml.property  name="bY_10"
		 * @uml.associationEnd  
		 */
		BY_10(10),
		/**
		 * @uml.property  name="bY_20"
		 * @uml.associationEnd  
		 */
		BY_20(20),
		/**
		 * @uml.property  name="bY_30"
		 * @uml.associationEnd  
		 */
		BY_30(30),
		/**
		 * @uml.property  name="bY_40"
		 * @uml.associationEnd  
		 */
		BY_40(40);
		
		/**
		 * @uml.property  name="numberOfRecords"
		 */
		private Integer numberOfRecords;
		
	    private show(Integer onPage){
	    	numberOfRecords = onPage;
	    }
		
		/**
		 * @return
		 * @uml.property  name="numberOfRecords"
		 */
		public Integer getNumberOfRecords(){
	      return numberOfRecords;
	    }
	}

}
