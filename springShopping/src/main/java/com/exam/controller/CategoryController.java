package com.exam.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.exam.domain.TotalVO;
import com.exam.service.CategoryService;

@Controller
@RequestMapping("/category/*")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/interest")
	public String interest(@RequestParam(defaultValue = "", required = false) String gSearch, 
			@RequestParam(defaultValue = "1") int gPageNum,
			HttpSession session, Model model) {
		int pageSize = 20;
		int count = categoryService.getInterestBoardCount(gSearch);
		ArrayList<String> interestList = (ArrayList) session.getAttribute("productlist");
		int pageCount = count / pageSize + ((count % pageSize == 0) ? 0 : 1);
		int pageBlock = 10;
		int startPage = ((gPageNum - 1) / pageBlock) * pageBlock + 1;
		int endPage = startPage + pageBlock - 1;
		if (endPage > pageCount) {
			endPage = pageCount;
		}
		Map<String, Integer> pageInfoMap = new HashMap<String, Integer>();
		pageInfoMap.put("count", count);
		pageInfoMap.put("pageCount", pageCount);
		pageInfoMap.put("pageBlock", pageBlock);
		pageInfoMap.put("startPage", startPage);
		pageInfoMap.put("endPage", endPage);
		
		model.addAttribute("interestList", interestList);
		model.addAttribute("pageInfoMap", pageInfoMap);
		model.addAttribute("gPageNum", gPageNum);
		model.addAttribute("gSearch", gSearch);
		model.addAttribute("totalDao", categoryService);
		return "category/interest";
	} // interest Get
	
	
	@PostMapping("/interest")
	public ResponseEntity<String> interest(@RequestParam("check") String[] check, HttpSession session, HttpServletRequest request) {
		ArrayList<String> interestList = (ArrayList) session.getAttribute("productlist");
		if (check != null) {
			if (interestList == null) {
				interestList = new ArrayList<String>();
				session.setAttribute("productlist", interestList);
			}
			for (int i = 0; i < check.length; i++) {
				interestList.add(check[i]);
			}
		}
		String message = "관심항목에 추가되었습니다.";
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "text/html; charset=UTF-8"); 
		StringBuilder sb = new StringBuilder();
		String url = request.getHeader("referer");
		sb.append("<script>");
		sb.append("alert('"+message+"');");
		sb.append("location.href = '"+url+"'");
		sb.append("</script>");
		return new ResponseEntity<String>(sb.toString(), headers, HttpStatus.OK);
	} // interest Post
	
	
	@PostMapping("/interestDelete")
	public ResponseEntity<String> interestDelete(@RequestParam("check") String[] check, HttpSession session) {
		ArrayList<String> interestList = (ArrayList) session.getAttribute("productlist");
		if (interestList != null) {
			for (int i = 0; i < check.length; i++) {
				interestList.remove(check[i]);
			}  
		}
		session.setAttribute("productlist", interestList);
	    String message = "관심항목에서 제외되었습니다.";
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "text/html; charset=UTF-8"); 
		StringBuilder sb = new StringBuilder();
		sb.append("<script>");
		sb.append("alert('"+message+"');");
		sb.append("location.href ='/category/interest';");
		sb.append("</script>");
		return new ResponseEntity<String>(sb.toString(), headers, HttpStatus.OK);
	} // interestDelete Post
	
	
	@GetMapping("/appliances")
	public String appliances(@RequestParam(defaultValue = "", required = false) String gSearch, 
			@RequestParam(defaultValue = "1") int gPageNum,
			@RequestParam(defaultValue = "", required = false) String subc,
			@RequestParam(defaultValue = "", required = false) String sw,
			HttpSession session, Model model) {
		int pageSize = 20;
		int startRow = (gPageNum - 1) * pageSize + 1;
		int count = categoryService.getAppBoardCount(gSearch);
		List<TotalVO> appBoardList = categoryService.getAppBoards(startRow, pageSize, gSearch, subc);
		int pageCount = count / pageSize + ((count % pageSize == 0) ? 0 : 1);
		int pageBlock = 10;
		int startPage = ((gPageNum - 1) / pageBlock) * pageBlock + 1;
		int endPage = startPage + pageBlock - 1;
		if (endPage > pageCount) {
			endPage = pageCount;
		}
		switch (sw) {
		case "default":
			appBoardList = categoryService.getAppBoards(startRow, pageSize, gSearch, subc);
			break;
		case "readcount":
			appBoardList = categoryService.getAppReadCountBoards(startRow, pageSize, gSearch, subc);
			break;
		case "lowprice":
			appBoardList = categoryService.getAppLowPriceBoards(startRow, pageSize, gSearch, subc);
			break;
		case "highprice":
			appBoardList = categoryService.getAppHighPriceBoards(startRow, pageSize, gSearch, subc);
			break;
		case "date":
			appBoardList = categoryService.getAppDateBoards(startRow, pageSize, gSearch, subc);
			break;
		}
		Map<String, Integer> pageInfoMap = new HashMap<String, Integer>();
		pageInfoMap.put("count", count);
		pageInfoMap.put("pageCount", pageCount);
		pageInfoMap.put("pageBlock", pageBlock);
		pageInfoMap.put("startPage", startPage);
		pageInfoMap.put("endPage", endPage);
		
		ArrayList<String> interestList = (ArrayList) session.getAttribute("productlist");
		model.addAttribute("interestList", interestList);
		model.addAttribute("totalDao", categoryService);
		model.addAttribute("appBoardList", appBoardList);
		model.addAttribute("pageInfoMap", pageInfoMap);
		model.addAttribute("gPageNum", gPageNum);
		model.addAttribute("gSearch", gSearch);
		model.addAttribute("subc", subc);
		model.addAttribute("sw", sw);
		return "category/appliances";
	} // appliances Get
	
	
	@GetMapping("/computer")
	public String computer(@RequestParam(defaultValue = "", required = false) String gSearch, 
			@RequestParam(defaultValue = "1") int gPageNum,
			@RequestParam(defaultValue = "", required = false) String subc,
			@RequestParam(defaultValue = "", required = false) String sw,
			HttpSession session, Model model) {
		int pageSize = 20;
		int startRow = (gPageNum - 1) * pageSize + 1;
		int count = categoryService.getComBoardCount(gSearch);
		List<TotalVO> comBoardList = categoryService.getComBoards(startRow, pageSize, gSearch, subc);
		int pageCount = count / pageSize + ((count % pageSize == 0) ? 0 : 1);
		int pageBlock = 10;
		int startPage = ((gPageNum - 1) / pageBlock) * pageBlock + 1;
		int endPage = startPage + pageBlock - 1;
		if (endPage > pageCount) {
			endPage = pageCount;
		}
		switch (sw) {
		case "default":
			comBoardList = categoryService.getComBoards(startRow, pageSize, gSearch, subc);
			break;
		case "readcount":
			comBoardList = categoryService.getComReadCountBoards(startRow, pageSize, gSearch, subc);
			break;
		case "lowprice":
			comBoardList = categoryService.getComLowPriceBoards(startRow, pageSize, gSearch, subc);
			break;
		case "highprice":
			comBoardList = categoryService.getComHighPriceBoards(startRow, pageSize, gSearch, subc);
			break;
		case "date":
			comBoardList = categoryService.getComDateBoards(startRow, pageSize, gSearch, subc);
			break;
		}
		Map<String, Integer> pageInfoMap = new HashMap<String, Integer>();
		pageInfoMap.put("count", count);
		pageInfoMap.put("pageCount", pageCount);
		pageInfoMap.put("pageBlock", pageBlock);
		pageInfoMap.put("startPage", startPage);
		pageInfoMap.put("endPage", endPage);
		
		ArrayList<String> interestList = (ArrayList) session.getAttribute("productlist");
		model.addAttribute("interestList", interestList);
		model.addAttribute("totalDao", categoryService);
		model.addAttribute("comBoardList", comBoardList);
		model.addAttribute("pageInfoMap", pageInfoMap);
		model.addAttribute("gPageNum", gPageNum);
		model.addAttribute("gSearch", gSearch);
		model.addAttribute("subc", subc);
		model.addAttribute("sw", sw);
		return "category/computer";
	} // computer Get
	
	
	@GetMapping("/fashion")
	public String fashion(@RequestParam(defaultValue = "", required = false) String gSearch, 
			@RequestParam(defaultValue = "1") int gPageNum,
			@RequestParam(defaultValue = "", required = false) String subc,
			@RequestParam(defaultValue = "", required = false) String sw,
			HttpSession session, Model model) {
		int pageSize = 20;
		int startRow = (gPageNum - 1) * pageSize + 1;
		int count = categoryService.getFasBoardCount(gSearch);
		List<TotalVO> fasBoardList = categoryService.getFasBoards(startRow, pageSize, gSearch, subc);
		int pageCount = count / pageSize + ((count % pageSize == 0) ? 0 : 1);
		int pageBlock = 10;
		int startPage = ((gPageNum - 1) / pageBlock) * pageBlock + 1;
		int endPage = startPage + pageBlock - 1;
		if (endPage > pageCount) {
			endPage = pageCount;
		}
		switch (sw) {
		case "default":
			fasBoardList = categoryService.getFasBoards(startRow, pageSize, gSearch, subc);
			break;
		case "readcount":
			fasBoardList = categoryService.getFasReadCountBoards(startRow, pageSize, gSearch, subc);
			break;
		case "lowprice":
			fasBoardList = categoryService.getFasLowPriceBoards(startRow, pageSize, gSearch, subc);
			break;
		case "highprice":
			fasBoardList = categoryService.getFasHighPriceBoards(startRow, pageSize, gSearch, subc);
			break;
		case "date":
			fasBoardList = categoryService.getFasDateBoards(startRow, pageSize, gSearch, subc);
			break;
		}
		Map<String, Integer> pageInfoMap = new HashMap<String, Integer>();
		pageInfoMap.put("count", count);
		pageInfoMap.put("pageCount", pageCount);
		pageInfoMap.put("pageBlock", pageBlock);
		pageInfoMap.put("startPage", startPage);
		pageInfoMap.put("endPage", endPage);
		
		ArrayList<String> interestList = (ArrayList) session.getAttribute("productlist");
		model.addAttribute("interestList", interestList);
		model.addAttribute("totalDao", categoryService);
		model.addAttribute("fasBoardList", fasBoardList);
		model.addAttribute("pageInfoMap", pageInfoMap);
		model.addAttribute("gPageNum", gPageNum);
		model.addAttribute("gSearch", gSearch);
		model.addAttribute("subc", subc);
		model.addAttribute("sw", sw);
		return "category/fashion";
	} // fashion Get
	
	
	@GetMapping("/general")
	public String general(@RequestParam(defaultValue = "", required = false) String gSearch, 
			@RequestParam(defaultValue = "1") int gPageNum,
			@RequestParam(defaultValue = "", required = false) String subc,
			@RequestParam(defaultValue = "", required = false) String sw,
			HttpSession session, Model model) {
		int pageSize = 20;
		int startRow = (gPageNum - 1) * pageSize + 1;
		int count = categoryService.getGenBoardCount(gSearch);
		List<TotalVO> genBoardList = categoryService.getGenBoards(startRow, pageSize, gSearch, subc);
		int pageCount = count / pageSize + ((count % pageSize == 0) ? 0 : 1);
		int pageBlock = 10;
		int startPage = ((gPageNum - 1) / pageBlock) * pageBlock + 1;
		int endPage = startPage + pageBlock - 1;
		if (endPage > pageCount) {
			endPage = pageCount;
		}
		switch (sw) {
		case "default":
			genBoardList = categoryService.getGenBoards(startRow, pageSize, gSearch, subc);
			break;
		case "readcount":
			genBoardList = categoryService.getGenReadCountBoards(startRow, pageSize, gSearch, subc);
			break;
		case "lowprice":
			genBoardList = categoryService.getGenLowPriceBoards(startRow, pageSize, gSearch, subc);
			break;
		case "highprice":
			genBoardList = categoryService.getGenHighPriceBoards(startRow, pageSize, gSearch, subc);
			break;
		case "date":
			genBoardList = categoryService.getGenDateBoards(startRow, pageSize, gSearch, subc);
			break;
		}
		Map<String, Integer> pageInfoMap = new HashMap<String, Integer>();
		pageInfoMap.put("count", count);
		pageInfoMap.put("pageCount", pageCount);
		pageInfoMap.put("pageBlock", pageBlock);
		pageInfoMap.put("startPage", startPage);
		pageInfoMap.put("endPage", endPage);
		
		ArrayList<String> interestList = (ArrayList) session.getAttribute("productlist");
		model.addAttribute("interestList", interestList);
		model.addAttribute("totalDao", categoryService);
		model.addAttribute("genBoardList", genBoardList);
		model.addAttribute("pageInfoMap", pageInfoMap);
		model.addAttribute("gPageNum", gPageNum);
		model.addAttribute("gSearch", gSearch);
		model.addAttribute("subc", subc);
		model.addAttribute("sw", sw);
		return "category/general";
	} // general Get
	
	
	@GetMapping("/sports")
	public String sports(@RequestParam(defaultValue = "", required = false) String gSearch, 
			@RequestParam(defaultValue = "1") int gPageNum,
			@RequestParam(defaultValue = "", required = false) String subc,
			@RequestParam(defaultValue = "", required = false) String sw,
			HttpSession session, Model model) {
		int pageSize = 20;
		int startRow = (gPageNum - 1) * pageSize + 1;
		int count = categoryService.getSpoBoardCount(gSearch);
		List<TotalVO> spoBoardList = categoryService.getSpoBoards(startRow, pageSize, gSearch, subc);
		int pageCount = count / pageSize + ((count % pageSize == 0) ? 0 : 1);
		int pageBlock = 10;
		int startPage = ((gPageNum - 1) / pageBlock) * pageBlock + 1;
		int endPage = startPage + pageBlock - 1;
		if (endPage > pageCount) {
			endPage = pageCount;
		}
		switch (sw) {
		case "default":
			spoBoardList = categoryService.getSpoBoards(startRow, pageSize, gSearch, subc);
			break;
		case "readcount":
			spoBoardList = categoryService.getSpoReadCountBoards(startRow, pageSize, gSearch, subc);
			break;
		case "lowprice":
			spoBoardList = categoryService.getSpoLowPriceBoards(startRow, pageSize, gSearch, subc);
			break;
		case "highprice":
			spoBoardList = categoryService.getSpoHighPriceBoards(startRow, pageSize, gSearch, subc);
			break;
		case "date":
			spoBoardList = categoryService.getSpoDateBoards(startRow, pageSize, gSearch, subc);
			break;
		}
		Map<String, Integer> pageInfoMap = new HashMap<String, Integer>();
		pageInfoMap.put("count", count);
		pageInfoMap.put("pageCount", pageCount);
		pageInfoMap.put("pageBlock", pageBlock);
		pageInfoMap.put("startPage", startPage);
		pageInfoMap.put("endPage", endPage);
		
		ArrayList<String> interestList = (ArrayList) session.getAttribute("productlist");
		model.addAttribute("interestList", interestList);
		model.addAttribute("totalDao", categoryService);
		model.addAttribute("spoBoardList", spoBoardList);
		model.addAttribute("pageInfoMap", pageInfoMap);
		model.addAttribute("gPageNum", gPageNum);
		model.addAttribute("gSearch", gSearch);
		model.addAttribute("subc", subc);
		model.addAttribute("sw", sw);
		return "category/sports";
	} // sports Get
	
	
	@GetMapping("/furniture")
	public String furniture(@RequestParam(defaultValue = "", required = false) String gSearch, 
			@RequestParam(defaultValue = "1") int gPageNum,
			@RequestParam(defaultValue = "", required = false) String subc,
			@RequestParam(defaultValue = "", required = false) String sw,
			HttpSession session, Model model) {
		int pageSize = 20;
		int startRow = (gPageNum - 1) * pageSize + 1;
		int count = categoryService.getFurBoardCount(gSearch);
		List<TotalVO> furBoardList = categoryService.getFurBoards(startRow, pageSize, gSearch, subc);
		int pageCount = count / pageSize + ((count % pageSize == 0) ? 0 : 1);
		int pageBlock = 10;
		int startPage = ((gPageNum - 1) / pageBlock) * pageBlock + 1;
		int endPage = startPage + pageBlock - 1;
		if (endPage > pageCount) {
			endPage = pageCount;
		}
		switch (sw) {
		case "default":
			furBoardList = categoryService.getFurBoards(startRow, pageSize, gSearch, subc);
			break;
		case "readcount":
			furBoardList = categoryService.getFurReadCountBoards(startRow, pageSize, gSearch, subc);
			break;
		case "lowprice":
			furBoardList = categoryService.getFurLowPriceBoards(startRow, pageSize, gSearch, subc);
			break;
		case "highprice":
			furBoardList = categoryService.getFurHighPriceBoards(startRow, pageSize, gSearch, subc);
			break;
		case "date":
			furBoardList = categoryService.getFurDateBoards(startRow, pageSize, gSearch, subc);
			break;
		}
		Map<String, Integer> pageInfoMap = new HashMap<String, Integer>();
		pageInfoMap.put("count", count);
		pageInfoMap.put("pageCount", pageCount);
		pageInfoMap.put("pageBlock", pageBlock);
		pageInfoMap.put("startPage", startPage);
		pageInfoMap.put("endPage", endPage);
		
		ArrayList<String> interestList = (ArrayList) session.getAttribute("productlist");
		model.addAttribute("interestList", interestList);
		model.addAttribute("totalDao", categoryService);
		model.addAttribute("furBoardList", furBoardList);
		model.addAttribute("pageInfoMap", pageInfoMap);
		model.addAttribute("gPageNum", gPageNum);
		model.addAttribute("gSearch", gSearch);
		model.addAttribute("subc", subc);
		model.addAttribute("sw", sw);
		return "category/furniture";
	} // furniture Get
	

	@GetMapping("/kids")
	public String kids(@RequestParam(defaultValue = "", required = false) String gSearch, 
			@RequestParam(defaultValue = "1") int gPageNum,
			@RequestParam(defaultValue = "", required = false) String subc,
			@RequestParam(defaultValue = "", required = false) String sw,
			HttpSession session, Model model) {
		int pageSize = 20;
		int startRow = (gPageNum - 1) * pageSize + 1;
		int count = categoryService.getKidBoardCount(gSearch);
		List<TotalVO> kidBoardList = categoryService.getKidBoards(startRow, pageSize, gSearch, subc);
		int pageCount = count / pageSize + ((count % pageSize == 0) ? 0 : 1);
		int pageBlock = 10;
		int startPage = ((gPageNum - 1) / pageBlock) * pageBlock + 1;
		int endPage = startPage + pageBlock - 1;
		if (endPage > pageCount) {
			endPage = pageCount;
		}
		switch (sw) {
		case "default":
			kidBoardList = categoryService.getKidBoards(startRow, pageSize, gSearch, subc);
			break;
		case "readcount":
			kidBoardList = categoryService.getKidReadCountBoards(startRow, pageSize, gSearch, subc);
			break;
		case "lowprice":
			kidBoardList = categoryService.getKidLowPriceBoards(startRow, pageSize, gSearch, subc);
			break;
		case "highprice":
			kidBoardList = categoryService.getKidHighPriceBoards(startRow, pageSize, gSearch, subc);
			break;
		case "date":
			kidBoardList = categoryService.getKidDateBoards(startRow, pageSize, gSearch, subc);
			break;
		}
		Map<String, Integer> pageInfoMap = new HashMap<String, Integer>();
		pageInfoMap.put("count", count);
		pageInfoMap.put("pageCount", pageCount);
		pageInfoMap.put("pageBlock", pageBlock);
		pageInfoMap.put("startPage", startPage);
		pageInfoMap.put("endPage", endPage);
		
		ArrayList<String> interestList = (ArrayList) session.getAttribute("productlist");
		model.addAttribute("interestList", interestList);
		model.addAttribute("totalDao", categoryService);
		model.addAttribute("kidBoardList", kidBoardList);
		model.addAttribute("pageInfoMap", pageInfoMap);
		model.addAttribute("gPageNum", gPageNum);
		model.addAttribute("gSearch", gSearch);
		model.addAttribute("subc", subc);
		model.addAttribute("sw", sw);
		return "category/kids";
	} // kids Get
	
	
	@GetMapping("/book")
	public String book(@RequestParam(defaultValue = "", required = false) String gSearch, 
			@RequestParam(defaultValue = "1") int gPageNum,
			@RequestParam(defaultValue = "", required = false) String subc,
			@RequestParam(defaultValue = "", required = false) String sw,
			HttpSession session, Model model) {
		int pageSize = 20;
		int startRow = (gPageNum - 1) * pageSize + 1;
		int count = categoryService.getBkBoardCount(gSearch);
		List<TotalVO> bkBoardList = categoryService.getBkBoards(startRow, pageSize, gSearch, subc);
		int pageCount = count / pageSize + ((count % pageSize == 0) ? 0 : 1);
		int pageBlock = 10;
		int startPage = ((gPageNum - 1) / pageBlock) * pageBlock + 1;
		int endPage = startPage + pageBlock - 1;
		if (endPage > pageCount) {
			endPage = pageCount;
		}
		switch (sw) {
		case "default":
			bkBoardList = categoryService.getBkBoards(startRow, pageSize, gSearch, subc);
			break;
		case "readcount":
			bkBoardList = categoryService.getBkReadCountBoards(startRow, pageSize, gSearch, subc);
			break;
		case "lowprice":
			bkBoardList = categoryService.getBkLowPriceBoards(startRow, pageSize, gSearch, subc);
			break;
		case "highprice":
			bkBoardList = categoryService.getBkHighPriceBoards(startRow, pageSize, gSearch, subc);
			break;
		case "date":
			bkBoardList = categoryService.getBkDateBoards(startRow, pageSize, gSearch, subc);
			break;
		}
		Map<String, Integer> pageInfoMap = new HashMap<String, Integer>();
		pageInfoMap.put("count", count);
		pageInfoMap.put("pageCount", pageCount);
		pageInfoMap.put("pageBlock", pageBlock);
		pageInfoMap.put("startPage", startPage);
		pageInfoMap.put("endPage", endPage);
		
		ArrayList<String> interestList = (ArrayList) session.getAttribute("productlist");
		model.addAttribute("interestList", interestList);
		model.addAttribute("totalDao", categoryService);
		model.addAttribute("bkBoardList", bkBoardList);
		model.addAttribute("pageInfoMap", pageInfoMap);
		model.addAttribute("gPageNum", gPageNum);
		model.addAttribute("gSearch", gSearch);
		model.addAttribute("subc", subc);
		model.addAttribute("sw", sw);
		return "category/book";
	} // book Get
	
	
	@GetMapping("/searchResult")
	public String searchResult(@RequestParam(defaultValue = "", required = false) String gSearch, 
			@RequestParam(defaultValue = "1") int gPageNum,
			@RequestParam(defaultValue = "", required = false) String subc,
			@RequestParam(defaultValue = "", required = false) String sw,
			HttpSession session, Model model) {
		int pageSize = 20;
		int startRow = (gPageNum - 1) * pageSize + 1;
		int count = categoryService.getResultBoardCount(gSearch);
		List<TotalVO> totalBoardList = categoryService.getResultBoards(startRow, pageSize, gSearch, subc);
		int pageCount = count / pageSize + ((count % pageSize == 0) ? 0 : 1);
		int pageBlock = 10;
		int startPage = ((gPageNum - 1) / pageBlock) * pageBlock + 1;
		int endPage = startPage + pageBlock - 1;
		if (endPage > pageCount) {
			endPage = pageCount;
		}
		switch (sw) {
		case "default":
			totalBoardList = categoryService.getResultBoards(startRow, pageSize, gSearch, subc);
			break;
		case "readcount":
			totalBoardList = categoryService.getResultReadCountBoards(startRow, pageSize, gSearch, subc);
			break;
		case "lowprice":
			totalBoardList= categoryService.getResultLowPriceBoards(startRow, pageSize, gSearch, subc);
			break;
		case "highprice":
			totalBoardList = categoryService.getResultHighPriceBoards(startRow, pageSize, gSearch, subc);
			break;
		case "date":
			totalBoardList = categoryService.getResultDateBoards(startRow, pageSize, gSearch, subc);
			break;
		}
		Map<String, Integer> pageInfoMap = new HashMap<String, Integer>();
		pageInfoMap.put("count", count);
		pageInfoMap.put("pageCount", pageCount);
		pageInfoMap.put("pageBlock", pageBlock);
		pageInfoMap.put("startPage", startPage);
		pageInfoMap.put("endPage", endPage);
		
		ArrayList<String> interestList = (ArrayList) session.getAttribute("productlist");
		model.addAttribute("interestList", interestList);
		model.addAttribute("totalDao", categoryService);
		model.addAttribute("totalBoardList", totalBoardList);
		model.addAttribute("pageInfoMap", pageInfoMap);
		model.addAttribute("gPageNum", gPageNum);
		model.addAttribute("gSearch", gSearch);
		model.addAttribute("subc", subc);
		model.addAttribute("sw", sw);
		return "category/searchResult";
	} // searchResult Get
	
	
	@GetMapping("/merchandiseInfo")
	public String merchandiseInfo(@RequestParam(defaultValue = "1") int gPageNum,
			@RequestParam("num") int num, @RequestParam("name") String name,
			@RequestParam("majorc") String majorc, @RequestParam("subc") String subc,
			HttpSession session, Model model) {
		categoryService.updateReadCount(num, majorc, subc);
		TotalVO totalVO = categoryService.getInfoBoard(num, majorc, subc);
		List<TotalVO> totalBoardList = categoryService.getInfoLowPriceBoards(name, majorc, subc);
		TotalVO minimum = categoryService.getInfoMinimumPrice(name, majorc, subc);
		model.addAttribute("totalBoardList", totalBoardList);
		model.addAttribute("totalVO", totalVO);
		model.addAttribute("minimum", minimum);
		model.addAttribute("gPageNum", gPageNum);
		model.addAttribute("majorc", majorc);
		model.addAttribute("subc", subc);
		return "category/merchandiseInfo";
	} // merchandiseInfo Get
	
	
	@GetMapping("/best")
	public String best(@RequestParam(defaultValue = "1") int gPageNum,
			@RequestParam(defaultValue = "", required = false) String majorc,
			@RequestParam(defaultValue = "", required = false) String subc,
			Model model) {
		int pageSize = 8;
		int count = categoryService.getBestPageCount();
		List<TotalVO> bestBoardList = categoryService.getBestPageBoards();
		int pageCount = count / pageSize + ((count % pageSize == 0) ? 0 : 1);
		int pageBlock = 10;
		int startPage = ((gPageNum - 1) / pageBlock) * pageBlock + 1;
		int endPage = startPage + pageBlock - 1;
		if (endPage > pageCount) {
			endPage = pageCount;
		}
		Map<String, Integer> pageInfoMap = new HashMap<String, Integer>();
		pageInfoMap.put("count", count);
		pageInfoMap.put("pageCount", pageCount);
		pageInfoMap.put("pageBlock", pageBlock);
		pageInfoMap.put("startPage", startPage);
		pageInfoMap.put("endPage", endPage);
		
		model.addAttribute("bestBoardList", bestBoardList);
		model.addAttribute("pageInfoMap", pageInfoMap);
		model.addAttribute("gPageNum", gPageNum);
		model.addAttribute("majorc", majorc);
		model.addAttribute("subc", subc);
		return "best/bestMain";
	} // best Get
}
