package com.library.controller.series;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.library.domain.book.model.MBook;
import com.library.domain.book.service.BookService;
import com.library.domain.publisher.model.MPublisher;
import com.library.domain.publisher.service.PublisherService;
import com.library.domain.series.model.MSeries;
import com.library.domain.series.service.SeriesService;
import com.library.form.BookAddToSeriesForm;

@Controller
@RequestMapping("/series/add")
public class SeriesAddController {
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private PublisherService publisherService;
	
	@Autowired
	private SeriesService seriesService;
		
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("{seriesId}")
	public String addBookToSeries(Model model, @ModelAttribute BookAddToSeriesForm form) {
		
		if (form.getUserId() == null) {
			MSeries series = seriesService.getOneSeries(form.getSeriesId());
			form.setSeriesName(series.getSeriesName());
			
			/*
			 * シリーズに所属する書籍の中で購入日が最新のものを検索
			 * 書籍が存在すれば著者名と出版社名をformにセット
			 */
			MBook book = bookService.getOneBookOfSeries(series.getId());
			if (book != null) {
				form.setAuthor(book.getAuthor());
				form.setPublisherName(book.getPublisher().getPublisherName());
				form.setUserId(book.getUserId());
			} else {
				form.setUserId(series.getUserId());
			}
		}
		
		List<MPublisher> publisherList = publisherService.getPublisherList();
		List<MSeries> seriesList = seriesService.getSeriesList();
		
		model.addAttribute("bookAddToSeriesForm", form);
		model.addAttribute("publisherList", publisherList);
		model.addAttribute("seriesList", seriesList);
		
		if (form.getPurchasedDate() == null) {
			form.setPurchasedDate(new Date());
		}
		
		return "series/add";
	}
	
	@PostMapping("{seriesId}")
	public String addBookToSeries(Model model, @ModelAttribute @Validated BookAddToSeriesForm form, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return addBookToSeries(model, form);
		}
		
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		MBook book = modelMapper.map(form, MBook.class);
		
		if (publisherService.isRegisteredName(form.getPublisherName())) {
			book.setPublisherId(publisherService.fetchPublisherIdByName(form.getPublisherName()));
			bookService.addOneBook(book);
		} else {
			MPublisher publisher = new MPublisher();
			publisher.setPublisherName(form.getPublisherName());
			bookService.addOneBookAndOnePublisher(book, publisher);
		}
		
		return "redirect:/series/detail/" + form.getSeriesId();
	}
	

}
