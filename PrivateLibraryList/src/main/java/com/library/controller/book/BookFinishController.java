package com.library.controller.book;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.library.domain.book.model.MBook;
import com.library.domain.book.service.BookService;

@Controller
@RequestMapping("/book/finish/")
public class BookFinishController {
	
	@Autowired
	private BookService bookService;
	
	@GetMapping("{id}")
	public String finishBook(@PathVariable("id") int id) {
		
		MBook book = bookService.getOneBook(id);
		
		if (book.getFinishedDate() == null) {
			book.setFinishedDate(new Date());
		} else {
			book.setFinishedDate(null);
		}
		
		bookService.editOneBook(book);
		
		return "redirect:/book/list";
	}

}
