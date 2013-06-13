package com.avramko.electroniclibrary.web.form;

public class RecordsOnPage{
	
	public static enum show {
		BY_5(5),
		BY_10(10),
		BY_15(15),
		BY_20(20);
		
		private Integer numberOfRecords;
		
	    private show(Integer onPage){
	    	numberOfRecords = onPage;
	    }
		
		public Integer getNumberOfRecords(){
	      return numberOfRecords;
	    }
	}

}
