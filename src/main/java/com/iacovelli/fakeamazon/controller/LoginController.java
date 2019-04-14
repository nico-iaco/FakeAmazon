package com.iacovelli.fakeamazon.controller;

import com.iacovelli.fakeamazon.model.form.UserForm;
import com.iacovelli.fakeamazon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

	@Autowired
	private UserService service;

	@GetMapping("/login")
	public String showForm(Model model) {
		model.addAttribute("userForm", new UserForm());
		return "login";
	}

	@PostMapping("/login")
	public String tryLogin(@ModelAttribute UserForm form, HttpServletRequest request) {
		if (service.login(form.getUsername(), form.getPassword())) {
			Long cartId = service.generateCartIfEmpty(form.getUsername(), form.getPassword());
			request.getSession().setAttribute("cartId", cartId);
			return "search";
		}
		request.setAttribute("exception", "Login incorretto");
		return "login";
	}

}
