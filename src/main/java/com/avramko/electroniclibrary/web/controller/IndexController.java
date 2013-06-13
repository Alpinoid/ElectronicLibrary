package com.avramko.electroniclibrary.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.avramko.electroniclibrary.web.form.RecordsOnPage;

@RequestMapping("/")
@Controller
public class IndexController {

    @RequestMapping(method=RequestMethod.GET)
    public String list(Model uiModel) {
    	
    	uiModel.addAttribute("pageSize", (Integer) RecordsOnPage.show.BY_5.getNumberOfRecords());
    	uiModel.addAttribute("paramSort", "ASC_booksName");
    	
    	return "/";
    }
    
}