package com.library.domain.series.model;

import java.util.List;

import com.library.domain.book.model.MBook;

import lombok.Data;

@Data
public class MSeries {
	Integer id;
	Integer userId;
	String seriesName;
	List<MBook> seriesBooks;
}
