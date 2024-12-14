package com.library.controller.publisher;

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
import com.library.form.PublisherForm;

@Controller
@RequestMapping("/publisher/detail")
public class PublisherDetailController {
	
	@Autowired
	private PublisherService publisherService;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("{id}")
	public String showPublisherDetail(Model model, @ModelAttribute PublisherForm form) {
		
		MPublisher publisher = publisherService.getOnePublisher(form.getId());
		
		if (form.getUserId() == null) {
			form = modelMapper.map(publisher, PublisherForm.class);
		}
		
		List<MBook> publisherBookList = bookService.getPublisherBookList(form.getId());
		
		model.addAttribute("publisherForm", form);
		model.addAttribute("publisher", publisher);
		model.addAttribute("publisherBookList", publisherBookList);
		
		return "publisher/detail";
	}
	
	@PostMapping(value = "{id}", params = "edit")
	public String editPublisherData(Model model, @ModelAttribute @Validated PublisherForm form, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors())  {
			return showPublisherDetail(model, form);
		}
		
		MPublisher publisher = modelMapper.map(form, MPublisher.class);
		publisherService.editOnePublisher(publisher);
		
		return "redirect:/publisher/detail/" + form.getId();
	}
	
	@PostMapping(value = "{id}", params = "delete")
	public String deletePublisherData(Model model, PublisherForm form) {
		
		publisherService.deleteOnePublisher(form.getId());
		
		return "redirect:/publisher/list";
	}

}
