package com.mindtree.cms.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.mindtree.cms.controller.BlogVO;

/**
 * @author Anurag Srivastava
 *
 */
public class BlogValidator implements Validator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> paramClass) {
		// TODO Auto-generated method stub
		return BlogVO.class.equals(paramClass);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 * org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object obj, Errors errors) {
		// TODO Auto-generated method stub
		BlogVO blogVO = (BlogVO) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title",
				"title.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description",
				"description.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "file",
				"file.required");
		if (blogVO.getTitle().length() < 3 || blogVO.getTitle().length() > 20) {
			errors.rejectValue("title", "customer.title.range.invalid");

		}
		if (blogVO.getTitle().length() < 3 || blogVO.getTitle().length() > 500) {
			errors.rejectValue("description",
					"customer.description.range.invalid");

		}
		if (blogVO.getFile() == null || blogVO.getFile().isEmpty()) {
			errors.rejectValue("file", "file.required");

		}

	}

}
