package com.example.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Item;

@Controller
@RequestMapping("/ex06")
public class ShoppingCartController {

	@Autowired
	private ServletContext application;
	@Autowired
	private HttpSession session;

	@RequestMapping("")
	public String index(Model model) {
		// applicationスコープに商品一覧を格納
		Item item1 = new Item("手帳ノート", 1000);
		Item item2 = new Item("文房具セット", 1500);
		Item item3 = new Item("ファイル", 2000);

		List<Item> itemList = new ArrayList<>();
		itemList.add(0, item1);
		itemList.add(1, item2);
		itemList.add(2, item3);

		application.setAttribute("itemList", itemList);

		// カート内の合計金額をrequestスコープに格納
		Integer totalPrice = 0;
		List<Item> itemCart = (List<Item>) session.getAttribute("itemCart");
		if (!Objects.isNull(itemCart)) {
			for (Item item : itemCart) {
				totalPrice += item.getPrice();
			}
		}
		model.addAttribute("totalPrice", totalPrice);
		return "item-and-cart";
	}

	@RequestMapping("/in-cart")
	public String inCart(Integer index, Model model) {
		// カートに追加したい商品をアプリケーションスコープから取得
		List<Item> itemList = (List<Item>) application.getAttribute("itemList");
		Item addedItem = itemList.get(index);

		// 取得した商品をカートに詰める
		List<Item> itemCart = (List<Item>) session.getAttribute("itemCart");
		if (Objects.isNull(itemCart)) {
			itemCart = new ArrayList<Item>();
		}
		itemCart.add(addedItem);

		// 対象商品を追加したリストをセッションスコープに戻す
		session.setAttribute("itemCart", itemCart);
		return index(model);
	}

	@RequestMapping("/delete")
	public String delete(Integer index, Model model) {
		// セッションスコープからカートのリストを取得
		List<Item> itemCart = (List<Item>) session.getAttribute("itemCart");

		// リストから対象商品を削除
		itemCart.remove((int) index);

		// 対象商品を削除したリストをセッションスコープに戻す
		session.setAttribute("itemCart", itemCart);
		return index(model);
	}

}
