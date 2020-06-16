package com.example.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ex02")
public class Exam02Controller {
	
	@Autowired
	private HttpSession session;
	
	@RequestMapping("")
	public String index() {
		return "exam02";
	}
	
	@RequestMapping("/receive")
	public String receive(Integer firstNum, Integer secondNum) {
		session.setAttribute("firstNum", firstNum);
		session.setAttribute("secondNum", secondNum);
		session.setAttribute("answer", firstNum + secondNum);
		return "exam02-result";
	}
	
	@RequestMapping("/to-next")
	public String toNext() {
		return "exam02-result2";
	}

}
