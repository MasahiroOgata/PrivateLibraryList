package com.library.controller.book;

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
import com.library.form.BookEditForm;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/book/edit")
@Slf4j
public class BookEditController {
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private PublisherService publisherService;
	
	@Autowired
	private SeriesService seriesService;
		
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("{id}")
	public String editBookData(Model model, @ModelAttribute BookEditForm form) {
		
		log.info(form.toString());
		
		if (form.getUserId() == null) {
			MBook book = bookService.getOneBook(form.getId());
			modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
			form = modelMapper.map(book, BookEditForm.class);
			form.setPublisherName(book.getPublisher().getPublisherName());
		}
		
		List<MPublisher> publisherList = publisherService.getPublisherList();
		List<MSeries> seriesList = seriesService.getSeriesList();
		
		model.addAttribute("bookEditForm", form);
		model.addAttribute("publisherList", publisherList);
		model.addAttribute("seriesList", seriesList);
		
		log.info(form.toString());
				
		return "book/edit";
	}
	
	@PostMapping("confirm/{id}")
	public String confirmEditBook(Model model, @ModelAttribute @Validated BookEditForm form, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return editBookData(model,form);
		}
		log.info(form.toString());
		
		if(form.getSeriesId() != null) {
			form.setSeriesName(seriesService.getOneSeries(form.getSeriesId()).getSeriesName());
		}
		
		log.info(form.toString());
		
		model.addAttribute("BookEditForm", form);
		
		MBook book = bookService.getOneBook(form.getId());
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		BookEditForm previousEdition = modelMapper.map(book, BookEditForm.class);
		previousEdition.setPublisherName(book.getPublisher().getPublisherName());
		if (book.getSeries() != null) {
			previousEdition.setSeriesName(book.getSeries().getSeriesName());
		}
		log.info(previousEdition.toString());
		
		model.addAttribute("previousEdition", previousEdition);
		
		
		
		return "book/edit/confirm";
	}
	
	@PostMapping(value="{id}", params="back")
	public String backToEditBook(Model model, @ModelAttribute BookEditForm form, BindingResult bindingResult) {
		return editBookData(model,form);
	}
	
	@PostMapping(value="{id}", params="confirm")
	public String editBookData(Model model, @ModelAttribute @Validated BookEditForm form, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return editBookData(model,form);
		}
		
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		MBook book = modelMapper.map(form, MBook.class);
		
		if (publisherService.isRegisteredName(form.getPublisherName())) {
			book.setPublisherId(publisherService.fetchPublisherIdByName(form.getPublisherName()));
			bookService.editOneBook(book);
		} else {
			MPublisher publisher = new MPublisher();
			publisher.setPublisherName(form.getPublisherName());
			bookService.editOneBookAndAddOnePublisher(book, publisher);
		}
		
		return "redirect:/book/detail/" + form.getId();
	}

}
