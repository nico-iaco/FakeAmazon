package com.iacovelli.fakeamazon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SearchController {

	@GetMapping("/search")
	public String showTable() {
		return "search";
	}

}
