package com.avramko.electroniclibrary.web.validator;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

@Component
public class BooksFileValidator implements Validator{
	
	static final int MAX_SIZE = 20971520; //20 Mb    
	static final List<String> CONTENT_TYPE_RESOLVER = Arrays.asList(
			"text/plain",								//TXT
			"application/rtf",							//RTF
			"application/msword",						//DOC
			"application/vnd.oasis.opendocument.text",	//ODT
			"application/pdf"							//PDF
			);


	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.isAssignableFrom(MultipartFile.class);
	}

	@Override
	public void validate(Object target, Errors errors) {

		MultipartFile file = (MultipartFile) target;
		
		if (file.getSize()>MAX_SIZE) {
			errors.rejectValue("booksFile", "validation_file_big_size");
		}
		if (!CONTENT_TYPE_RESOLVER.contains(file.getContentType())) {
			errors.rejectValue("booksFile", "validation_file_bad_type");
		}

	}

}
