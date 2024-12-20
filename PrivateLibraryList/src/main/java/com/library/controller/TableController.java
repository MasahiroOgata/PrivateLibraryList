package com.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.library.domain.book.model.MBook;
import com.library.domain.book.service.BookService;

@Controller
public class TableController {
	
	@Autowired
	private BookService bookService;
	
	@GetMapping("table")
	public String getTable(Model model) {
		
		List<MBook> bookList = bookService.getBookList("");
		
		model.addAttribute("bookList", bookList);
		
		
		return "table";
	}

}
