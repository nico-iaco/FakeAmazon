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

	/**
	 * This method shows the login page
	 * @param model
	 * @return
	 */
	@GetMapping("/login")
	public String showForm(Model model) {
		model.addAttribute("userForm", new UserForm());
		return "login";
	}

	/**
	 * This method processes the login and if the login is successful will
	 * redirect to search page otherwise will show error inside the form
	 * @param form
	 * @param request
	 * @return
	 */
	@PostMapping("/login")
	public String tryLogin(@ModelAttribute UserForm form, HttpServletRequest request) {
		if (service.login(form.getUsername(), form.getPassword())) {
			Long cartId = service.generateCartIfEmpty(form.getUsername(), form.getPassword());
			request.getSession().setAttribute("cartId", cartId);
			return "redirect:/search";
		}
		request.setAttribute("exception", "Login incorretto");
		return "login";
	}

}
