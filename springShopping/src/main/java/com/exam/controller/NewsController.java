package com.exam.controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.exam.repository.APISearch;
import com.exam.repository.APIShopping;

@Controller
@RequestMapping("/news/*")
public class NewsController {

	@GetMapping("/news")
	public String news(Model model) throws Exception {
		APISearch newsSearch = new APISearch();
		String json = newsSearch.json;
		JSONParser jsonParse = new JSONParser();
		JSONObject jsonObj = (JSONObject) jsonParse.parse(json);
		JSONArray items = (JSONArray) jsonObj.get("items");
		for (int i = 0; i < items.size(); i++) {
			JSONObject tmp = (JSONObject) items.get(i);
			String title = (String) tmp.get("title");
			String originallink = (String) tmp.get("originallink");
			String link = (String) tmp.get("link");
			String description = (String) tmp.get("description");
		}
		model.addAttribute("items", items);
		return "news/news";
	}
	
	
	@GetMapping("/shopping")
	public String shopping(@RequestParam(defaultValue = "", required = false) String sSearch, Model model) throws Exception {
		APIShopping apiShopping = new APIShopping(sSearch);
		String json = apiShopping.json;
		JSONParser jsonParse = new JSONParser();
		JSONObject jsonObj = (JSONObject) jsonParse.parse(json);
		JSONArray items = (JSONArray) jsonObj.get("items");
		if (items != null) {
			for (int i = 0; i < items.size(); i++) {
				JSONObject tmp = (JSONObject) items.get(i);
				String title = (String) tmp.get("title");
				String link = (String) tmp.get("link");
				String image = (String) tmp.get("image");
				String lprice = (String) tmp.get("lprice");
				String hprice = (String) tmp.get("hprice");
				String mallName = (String) tmp.get("mallName");
			}			
		}
		model.addAttribute("items", items);
		return "news/shopping";
	}
}
