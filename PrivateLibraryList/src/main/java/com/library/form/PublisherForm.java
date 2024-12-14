package com.library.form;

import org.hibernate.validator.constraints.Length;

import com.library.validator.NoExistingPublisher;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PublisherForm {
	Integer id;
	Integer userId;
	
	@NotBlank
	@Length(max = 50)
	@NoExistingPublisher
	String publisherName;
}
