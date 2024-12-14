package com.library.form;

import org.hibernate.validator.constraints.Length;

import com.library.validator.NoExistingSeries;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SeriesForm {
	
	Integer id;
	Integer userId;
	
	@NotBlank
	@Length(max = 50)
	@NoExistingSeries
	String seriesName;
}
