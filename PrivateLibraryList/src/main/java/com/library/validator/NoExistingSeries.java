package com.library.validator;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Retention(RUNTIME)
@Target(FIELD)
@Constraint(validatedBy = NoExistingSeriesValidator.class)
public @interface NoExistingSeries {
	
	String message() default "";
	
	Class<?>[] groups() default {};
	 
    Class<? extends Payload>[] payload() default {};
    
    @Documented
    @Retention(RUNTIME)
    @Target(FIELD)
    public @interface List {
    	NoExistingSeries[] value();
    }

}
