package com.library.controller.book;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.library.domain.book.model.MBook;
import com.library.domain.book.service.BookService;

@Controller
@RequestMapping("/book/list")
public class BookListController {
	
	@Autowired
	private BookService bookService;
	
	@GetMapping("")
	public String getBookList(Model model, @RequestParam(required = false) String search) {
		
		List<MBook> bookList = bookService.getBookList(search);
		
		model.addAttribute("search", search);
		model.addAttribute("bookList", bookList);
		
		return "book/list";
	}

}
