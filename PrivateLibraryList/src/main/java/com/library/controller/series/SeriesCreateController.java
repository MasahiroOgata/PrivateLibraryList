package com.library.controller.series;

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

import com.library.domain.series.model.MSeries;
import com.library.domain.series.service.SeriesService;
import com.library.form.SeriesForm;

@Controller
@RequestMapping("/series/create")
public class SeriesCreateController {
	
	@Autowired
	private SeriesService seriesService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("")
	public String createSeries(Model model, @ModelAttribute SeriesForm form) {
		
		return "series/create";
	}
	
	@PostMapping("")
	public String createSeries(Model model, @ModelAttribute @Validated SeriesForm form, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return createSeries(model, form);
		}
		
		MSeries series = modelMapper.map(form, MSeries.class);
		seriesService.addOneSeries(series);
		
		return "redirect:/series/list";
	}

}
