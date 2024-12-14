package com.library.form;

import java.util.Date;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BookAddToSeriesForm {
	
	Integer userId;
	
	@NotBlank
	@Length(max = 50)
	String bookTitle;
	
	@NotBlank
	@Length(max = 50)
	String author;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull
	Date purchasedDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date finishedDate;
	
	String seriesName;
	
	Integer seriesId;
	
	Integer volNum;
	
	@NotBlank
	String publisherName;
	
	Integer publisherId;
	
	@NotNull
	Integer media;	
}
