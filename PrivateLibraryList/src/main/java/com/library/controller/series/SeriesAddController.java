package com.library.controller.series;

import java.util.Date;
import java.util.List;
import java.util.Objects;

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

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/series/add")
@Slf4j
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
		
		log.info(form.toString());
		
		if (bindingResult.hasErrors()) {
			return addBookToSeries(model, form);
		}
		
		Integer publisherId = publisherService.checkPublisherName(form.getPublisherName());
		if (Objects.nonNull(publisherId)) {
			form.setPublisherId(publisherId);
		} else {
			MPublisher publisher = new MPublisher();
			publisher.setPublisherName(form.getPublisherName());
			publisherService.addOnePublisher(publisher);
			form.setPublisherId(publisherService.checkPublisherName(form.getPublisherName()));		
		}
		
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		MBook book = modelMapper.map(form, MBook.class);
		bookService.addOneBook(book);
		
		return "redirect:/series/detail/" + form.getSeriesId();
	}
	

}