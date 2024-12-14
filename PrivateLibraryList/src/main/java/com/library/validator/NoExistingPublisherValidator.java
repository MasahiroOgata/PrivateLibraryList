package com.library.validator;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;

import com.library.domain.publisher.service.PublisherService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NoExistingPublisherValidator implements ConstraintValidator<NoExistingPublisher, String> {
	
	@Autowired
	private PublisherService publisherService;
	
	public void initialize(NoExistingPublisher constraintAnnotation) {}
	
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		Integer publisherId = publisherService.checkPublisherName(value);
		
		if (Objects.isNull(publisherId)) {
			return true;
		}
		
		return false;
	}

}
