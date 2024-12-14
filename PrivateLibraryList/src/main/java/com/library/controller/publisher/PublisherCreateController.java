package com.library.controller.publisher;

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

import com.library.domain.publisher.model.MPublisher;
import com.library.domain.publisher.service.PublisherService;
import com.library.form.PublisherForm;

@Controller
@RequestMapping("/publisher/create")
public class PublisherCreateController {
	
	@Autowired
	private PublisherService publisherService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("")
	public String createPublisher(Model model, @ModelAttribute PublisherForm form) {
		
		return "publisher/create";
	}
	
	@PostMapping("")
	public String createPublisher(Model model, @ModelAttribute @Validated PublisherForm form, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return createPublisher(model, form);
		}
		
		MPublisher publisher = modelMapper.map(form, MPublisher.class);
		publisherService.addOnePublisher(publisher);
		
		return "redirect:/publisher/list";
	}

}
