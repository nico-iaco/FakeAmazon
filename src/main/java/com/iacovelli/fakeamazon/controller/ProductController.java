package com.iacovelli.fakeamazon.controller;

import com.iacovelli.fakeamazon.model.form.Category;
import com.iacovelli.fakeamazon.model.form.ProductForm;
import com.iacovelli.fakeamazon.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ProductController {

	@Autowired
	private ProductService service;

	@GetMapping("/products")
	public String showForm(Model model) {
		model.addAttribute("productForm", new ProductForm());
		List<Category> categories = Arrays.asList(Category.values());
		model.addAttribute("categories", categories);
		return "product";
	}

	@PostMapping("/products")
	public String addProduct(@Valid @ModelAttribute ProductForm productForm, BindingResult bindingResult) {
		if (bindingResult.hasFieldErrors()) {
			return "product";
		}
		service.saveProduct(productForm);
		return "index";
	}

}
