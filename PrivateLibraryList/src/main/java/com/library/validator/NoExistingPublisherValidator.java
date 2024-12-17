package com.library.validator;

import org.springframework.beans.factory.annotation.Autowired;

import com.library.domain.publisher.service.PublisherService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NoExistingPublisherValidator implements ConstraintValidator<NoExistingPublisher, String> {
	
	@Autowired
	private PublisherService publisherService;
	
	public void initialize(NoExistingPublisher constraintAnnotation) {}
	
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		if (publisherService.isRegistered(value)) {
			return false;
		}
		return true;
	}

}
