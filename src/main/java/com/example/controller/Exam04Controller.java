package com.example.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.User;
import com.example.form.UserForm;

@Controller
@RequestMapping("/ex04")
public class Exam04Controller {

	@ModelAttribute
	public UserForm setUp() {
		return new UserForm();
	}

	@RequestMapping("")
	public String index() {
		return "exam04";
	}

	@RequestMapping("/receive")
	public String receive(@Validated UserForm userForm, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return index();
		}
		User user = new User();
		// userFormオブジェクトからuserオブジェクトにプロパティ値をコピー
		BeanUtils.copyProperties(userForm, user);

		model.addAttribute("user", user);

		return "exam04-result";
	}

}
