package com.library.controller.publisher;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.library.domain.publisher.model.MPublisher;
import com.library.domain.publisher.service.PublisherService;

@Controller
@RequestMapping("/publisher/list")
public class PublisherListController {
	
	@Autowired
	private PublisherService publisherService;
	
	@GetMapping("")
	public String getPublisherList(Model model) {
		
		List<MPublisher> publisherList = publisherService.getPublisherList();
		
		model.addAttribute("publisherList", publisherList);
		
		return "publisher/list";
	}

}
