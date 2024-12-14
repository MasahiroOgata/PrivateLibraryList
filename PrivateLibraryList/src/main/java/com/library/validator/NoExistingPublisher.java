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
@Constraint(validatedBy = NoExistingPublisherValidator.class)
public @interface NoExistingPublisher {
	
	String message() default "その名前の出版社はすでに存在します";
	
	Class<?>[] groups() default {};
	 
    Class<? extends Payload>[] payload() default {};
    
    @Documented
    @Retention(RUNTIME)
    @Target(FIELD)
    public @interface List {
    	NoExistingPublisher[] value();
    }

}
