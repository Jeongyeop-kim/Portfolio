package com.exam.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.exam.domain.BoardVO;
import com.exam.domain.FboardVO;
import com.exam.domain.SellVO;
import com.exam.domain.TotalVO;
import com.exam.repository.APISearch;
import com.exam.service.CategoryService;
import com.exam.service.MainService;

@Controller
public class HomeController {
	
	@Autowired
	private MainService mainService;
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/")
	public String index(@RequestParam(defaultValue = "1") int pageNum,
			@RequestParam(defaultValue = "1") int gPageNum, 
			@RequestParam(defaultValue = "1") int aPageNum,
			HttpSession session, Model model) throws Exception {
		// 자유 게시판
		int fCount = mainService.getFboardCount();
		List<FboardVO> fBoardList = mainService.getMainFboards();
		// 중고매매 게시판
		int tCount = mainService.getTboardCount();
		List<BoardVO> tBoardList = mainService.getMainTboards();
		// 최근, 베스트 게시판
		int merCount = mainService.getMerchandiseCount();
		List<TotalVO> merBoardList = mainService.getMainRecentBoards();
		List<TotalVO> bestBoardList = mainService.getMainBestBoards();
		// 경매
		int aCount = mainService.getAuctionCount();
		List<SellVO> aBoardList = mainService.getMainAboards();
		// 뉴스
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
		ArrayList<String> interestList = (ArrayList) session.getAttribute("productlist");
		model.addAttribute("items", items);
		model.addAttribute("interestList", interestList);
		model.addAttribute("totalDao", categoryService);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("gPageNum", gPageNum);
		model.addAttribute("aPageNum", aPageNum);
		model.addAttribute("fCount", fCount);
		model.addAttribute("tCount", tCount);
		model.addAttribute("merCount", merCount);
		model.addAttribute("aCount", aCount);
		model.addAttribute("fBoardList", fBoardList);
		model.addAttribute("tBoardList", tBoardList);
		model.addAttribute("merBoardList", merBoardList);
		model.addAttribute("bestBoardList", bestBoardList);
		model.addAttribute("aBoardList", aBoardList);
		return "main/main";
	} // main Get
}
