package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Item;
import com.example.domain.Member;

@Controller
@RequestMapping("/ex06")
public class ShoppingCartController {

	@Autowired
	private ServletContext application;
	private HttpSession session;

	@RequestMapping("")
	public String index(Model model) {
		// applicationスコープに商品一覧を格納
		Item item1 = new Item();
		item1.setName("手帳ノート");
		item1.setPrice(1000);
		Item item2 = new Item();
		item2.setName("文房具セット");
		item2.setPrice(1500);
		Item item3 = new Item();
		item3.setName("ファイル");
		item3.setPrice(2000);

		application.setAttribute("item1", item1);
		application.setAttribute("item2", item2);
		application.setAttribute("item3", item3);

		// sessionスコープ内の商品一覧の合計金額をrequestスコープに格納

		Integer totalPrice = 0;
		model.addAttribute("totalPrice", totalPrice);

		return "item-and-cart";
	}

	@RequestMapping("/in-cart")
	public String inCart(Integer index, Model model) {
		switch (index) {
		case 1:
			session.setAttribute("item1", application.getAttribute("item1"));
			return index(model);
		case 2:
			session.setAttribute("item2", application.getAttribute("item2"));
			return index(model);
		case 3:
			session.setAttribute("item3", application.getAttribute("item3"));
			return index(model);
		}
		return index(model);
	}

}
