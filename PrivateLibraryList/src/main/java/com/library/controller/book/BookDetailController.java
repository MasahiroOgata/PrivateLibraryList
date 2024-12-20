package com.library.controller.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.library.domain.book.model.MBook;
import com.library.domain.book.service.BookService;

@Controller
@RequestMapping("/book/detail")
public class BookDetailController {
	
	@Autowired
	private BookService bookService;
	
	@GetMapping("{id}")
	public String showBookDetail(Model model, @PathVariable int id) {
		
		MBook book = bookService.getOneBook(id);
		
		model.addAttribute("book", book);
		model.addAttribute("backURL", "/book/list");
		
		return "book/detail";
	}
	
	@PostMapping("{id}")
	public String showBookDetail(Model model, @PathVariable int id, @RequestParam String backURL) {
		
		MBook book = bookService.getOneBook(id);
		
		model.addAttribute("book", book);
		model.addAttribute("backURL", backURL);
		
		return "book/detail";
	}

}
