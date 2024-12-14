package com.library.validator;

import org.springframework.beans.factory.annotation.Autowired;

import com.library.domain.series.service.SeriesService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NoExistingSeriesValidator implements ConstraintValidator<NoExistingSeries, String> {
	
	@Autowired
	private SeriesService seriesService;
	
	public void initialize(NoExistingSeries constraintAnnotation) {}
	
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		String seriesName = seriesService.checkSeriesName(value);
		
		if (seriesName == null) {
			return true;
		}
		return false;
		
	}

}
