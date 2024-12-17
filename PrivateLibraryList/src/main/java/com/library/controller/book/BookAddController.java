package com.library.controller.book;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
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
import com.library.form.BookAddForm;

@Controller
@RequestMapping("/book/add")
public class BookAddController {
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private PublisherService publisherService;
	
	@Autowired
	private SeriesService seriesService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("")
	public String addBook(Model model, @ModelAttribute BookAddForm form) {
		
		List<MPublisher> publisherList = publisherService.getPublisherList();
		List<MSeries> seriesList = seriesService.getSeriesList();
		
		model.addAttribute("publisherList", publisherList);
		model.addAttribute("seriesList", seriesList);
		
		if (form.getPurchasedDate() == null) {
			form.setPurchasedDate(new Date());
		}
		
		return "book/add";
	}
	
	@PostMapping("")
	public String addBook(Model model,@ModelAttribute @Validated BookAddForm form, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return addBook(model, form);
		}
		
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		MBook book = modelMapper.map(form, MBook.class);
		
		/* 
		 * ユーザーが入力した出版社名がDBに存在するか確認
		 * 既存であれば出版社IDをMBookにセットして登録
		 * なければ出版社新規登録と書籍新規登録を同時に実行 
		 */
		if (publisherService.isRegisteredName(form.getPublisherName())) {
			book.setPublisherId(publisherService.fetchPublisherIdByName(form.getPublisherName()));
			bookService.addOneBook(book);
		} else {
			MPublisher publisher = new MPublisher();
			publisher.setPublisherName(form.getPublisherName());
			bookService.addOneBookAndOnePublisher(book, publisher);
		}
		
		return "redirect:/book/list";
	}

}
