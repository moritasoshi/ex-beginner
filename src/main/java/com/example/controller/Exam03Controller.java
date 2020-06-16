package com.example.controller;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ex03")
public class Exam03Controller {
	
	@Autowired
	private ServletContext application;
	
	@RequestMapping("")
	public String index() {
		return "exam03";
	}
	
	@RequestMapping("/receive")
	public String receive(Integer firstItem, Integer secondItem, Integer thirdItem) {
		Integer sum = firstItem + secondItem + thirdItem;
		Integer sumWithTax = (int) (sum * 1.1); //税率10％で計算
		application.setAttribute("sum", sum);
		application.setAttribute("sumWithTax", sumWithTax);
		return "exam03-result";
	}
	
}
