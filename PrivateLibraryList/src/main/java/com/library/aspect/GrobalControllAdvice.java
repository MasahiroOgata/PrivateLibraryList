package com.library.aspect;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GrobalControllAdvice {
	
	@ExceptionHandler(NotFoundException.class)
	public String notFoundException(NotFoundException e, Model model) {
		
		model.addAttribute("error", "");
		
		model.addAttribute("message", e.getMessage());
		
		model.addAttribute("status", "404 Not Found");
		
		return "error/404";
	}
	
	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception e, Model model) {
		
		model.addAttribute("error", "");
		
		model.addAttribute("message", "Exceptionが発生しました");
		
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);
		
		return "error";
	}

}
