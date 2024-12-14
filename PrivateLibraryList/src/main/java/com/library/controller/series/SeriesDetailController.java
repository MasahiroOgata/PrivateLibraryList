package com.library.controller.series;

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
import com.library.domain.series.model.MSeries;
import com.library.domain.series.service.SeriesService;
import com.library.form.SeriesForm;

@Controller
@RequestMapping("/series/detail")
public class SeriesDetailController {
	
	@Autowired
	private SeriesService seriesService;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("{id}")
	public String showSeriesDetail(Model model, @ModelAttribute SeriesForm form) {
		
		MSeries series = seriesService.getOneSeries(form.getId());
		
		if (form.getUserId() == null) {
			form = modelMapper.map(series, SeriesForm.class);
		}
		
		List<MBook> seriesBookList = bookService.getSeriesBookList(form.getId());
		
		model.addAttribute("seriesForm",form);
		model.addAttribute("series", series);
		model.addAttribute("seriesBookList", seriesBookList);
		
		return "series/detail";
	}
	
	@PostMapping(value = "{id}", params = "edit")
	public String editSeriesData(Model model, @ModelAttribute @Validated SeriesForm form, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return showSeriesDetail(model, form);
		}
		
		MSeries series = modelMapper.map(form, MSeries.class);
		seriesService.editOneSeries(series);
		
		return "redirect:/series/detail/" + form.getId();
	}
	
	@PostMapping(value = "{id}", params = "delete")
	public String deleteSeriesData(Model model, SeriesForm form) {
		
		seriesService.deleteOneSeries(form.getId());
		
		return "redirect:/series/list";
	}

}
