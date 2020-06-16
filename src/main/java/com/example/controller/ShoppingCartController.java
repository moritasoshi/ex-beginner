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
		
		List<Item> itemList = new ArrayList<>();
		itemList.add(item1);
		itemList.add(item2);
		itemList.add(item3);
		application.setAttribute("itemList", itemList);
		
		// sessionスコープ内の商品一覧の合計金額をrequestスコープに格納
		
		Integer totalPrice = 0;
		model.addAttribute("totalPrice", totalPrice);
		
		return "item-and-cart";
	}

	@RequestMapping("/in-cart")
	public String inCart(String input, Model model) {
		return "";
	}

}
