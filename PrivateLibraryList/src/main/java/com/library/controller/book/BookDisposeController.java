package com.library.controller.book;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.library.domain.book.model.MBook;
import com.library.domain.book.service.BookService;
import com.library.form.BookDisposeForm;

@Controller
@RequestMapping("/book/dispose")
public class BookDisposeController {
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("{id}")
	public String disposeBook(Model model, @ModelAttribute BookDisposeForm form) {
		
		MBook book = bookService.getOneBook(form.getId());
		
		form.setIsDisposed(book.getIsDisposed());
		
		model.addAttribute("bookDisposeForm", form);
		model.addAttribute("book", book);
		
		return "book/dispose";
	}
	
	@PostMapping("{id}")
	public String disposeBook(@ModelAttribute BookDisposeForm form) {
		
		form.setIsDisposed(form.getIsDisposed() == 0 ? 1 : 0);
		
		MBook book = modelMapper.map(form, MBook.class);
		bookService.disposeOneBook(book);
		
		return "redirect:/book/list";
		
	}

}
