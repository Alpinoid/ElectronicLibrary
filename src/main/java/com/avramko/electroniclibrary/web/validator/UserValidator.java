package com.avramko.electroniclibrary.web.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.avramko.electroniclibrary.security.LibraryUser;

@Component
public class UserValidator implements Validator{
	
	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.isAssignableFrom(LibraryUser.class);
	}

	@Override
	public void validate(Object target, Errors errors) {

		LibraryUser user = (LibraryUser) target;
		
		if (user.getUsername() == "") {
			errors.rejectValue("username", "validation_name_notEmpty");
		}
		if (user.getPassword() == "") {
			errors.rejectValue("password", "validation_password_notEmpty");
		}

	}

}
