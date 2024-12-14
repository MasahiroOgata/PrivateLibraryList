package com.library.controller.series;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.library.domain.book.service.BookService;
import com.library.domain.series.model.MSeries;
import com.library.domain.series.service.SeriesService;

@Controller
@RequestMapping("/series/list")
public class SeriesListController {
	
	@Autowired
	private SeriesService seriesService;
	
	@Autowired
	private BookService bookService;
	
	@GetMapping("")
	public String getSeriesList(Model model) {
		
		List<MSeries> seriesList = seriesService.getSeriesListPurchasedOrder();
		
		seriesList.forEach(series -> 
			series.setSeriesBooks(bookService.getSeriesBookList(series.getId()))
		);
		
		model.addAttribute("seriesList", seriesList);
		
		return "series/list";
	}
	
}
