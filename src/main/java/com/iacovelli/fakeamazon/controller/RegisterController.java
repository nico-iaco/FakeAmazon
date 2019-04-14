package com.iacovelli.fakeamazon.controller;

import com.iacovelli.fakeamazon.exception.UserAlreadyRegisteredException;
import com.iacovelli.fakeamazon.model.form.UserForm;
import com.iacovelli.fakeamazon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class RegisterController {

	@Autowired
	private UserService service;

	@GetMapping("/register")
	public String showRegisterForm(Model model) {
		model.addAttribute("userForm", new UserForm());
		return "register";
	}

	@PostMapping("/register")
	public String register(@Valid @ModelAttribute UserForm form, BindingResult bindingResult, HttpServletRequest request) {
		if (bindingResult.hasFieldErrors()) {
			return "register";
		}
		try {
			service.register(form.getUsername(), form.getPassword());
			return "index";
		} catch (UserAlreadyRegisteredException ex) {
			request.setAttribute("exception", ex.getMessage());
			return "register";
		}
	}

}
