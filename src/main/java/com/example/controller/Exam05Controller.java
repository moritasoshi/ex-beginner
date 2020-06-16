package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Member;
import com.example.repository.MemberRepository;

@Controller
@RequestMapping("/ex05")
public class Exam05Controller {

	@Autowired
	private MemberRepository repository;
	
	@RequestMapping("")
	public String index() {
		return "exam05";
	}

	@RequestMapping("/receive")
	public String receive(String input, Model model) {
		List<Member> memberList = repository.findByName(input);
		model.addAttribute("memberList", memberList);
		return "exam05-result";
	}

}
