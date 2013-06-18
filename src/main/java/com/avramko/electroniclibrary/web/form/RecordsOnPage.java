package com.avramko.electroniclibrary.web.form;

public class RecordsOnPage{
	
	public static enum show {
		BY_10(10),
		BY_20(20),
		BY_30(30),
		BY_40(40);
		
		private Integer numberOfRecords;
		
	    private show(Integer onPage){
	    	numberOfRecords = onPage;
	    }
		
		public Integer getNumberOfRecords(){
	      return numberOfRecords;
	    }
	}

}
