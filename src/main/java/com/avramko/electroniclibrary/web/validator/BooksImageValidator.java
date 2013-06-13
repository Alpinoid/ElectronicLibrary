package com.avramko.electroniclibrary.web.validator;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;

@Component
public class BooksImageValidator implements Validator{
	
	static final int maxH = 800;    
	static final int maxW = 600;

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.isAssignableFrom(MultipartFile.class);
	}

	@Override
	public void validate(Object target, Errors errors) {

		MultipartFile file = (MultipartFile) target;
		
		if (!file.getContentType().contains("image")) {
			errors.rejectValue("booksImage", "validation_file_not_image");
		} else {
			try {
				InputStream inputStream = file.getInputStream();
				BufferedImage bufImage = ImageIO.read(inputStream);
				if(bufImage.getHeight()>maxH || bufImage.getWidth()>maxW) {
					errors.rejectValue("booksImage", "validation_image_bad_size");
				}			
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

	}

}
