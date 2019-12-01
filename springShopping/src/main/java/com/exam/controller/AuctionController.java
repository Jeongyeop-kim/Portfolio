package com.exam.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.exam.domain.BuyVO;
import com.exam.domain.MemberVO;
import com.exam.domain.SellVO;
import com.exam.service.AuctionService;
import com.exam.service.MemberService;

@Controller
@RequestMapping("/auction/*")
public class AuctionController {
	
	@Autowired
	private AuctionService auctionService;
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/auction")
	public String auction(@RequestParam(defaultValue = "1") int aPageNum,
			@RequestParam(defaultValue = "") String aSearch, Model model) {
		int pageSize = 5;
		int startRow = (aPageNum - 1) * pageSize + 1;
		List<SellVO> aBoardList = auctionService.getBoards(startRow, pageSize, aSearch);
		int count = auctionService.getBoardCount(aSearch);
		int pageCount = count / pageSize + ((count % pageSize == 0) ? 0 : 1);
		int pageBlock = 10;
		int startPage = ((aPageNum - 1) / pageBlock) * pageBlock + 1;
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
		
		model.addAttribute("aBoardList", aBoardList);
		model.addAttribute("pageInfoMap", pageInfoMap);
		model.addAttribute("aPageNum", aPageNum);
		model.addAttribute("aSearch", aSearch);
		return "auction/auction";
	} // auction Get
	
	
	@GetMapping("/auctionContent")
	public String auctionContent(int num, @RequestParam(defaultValue = "1") int aPageNum,
			@RequestParam(defaultValue = "0") int price, HttpSession session, Model model) {
		MemberVO loginMember = (MemberVO) session.getAttribute("loginMember");
		if (loginMember == null) {
			return "redirect:/auction/auction";
		}
		SellVO sellVO = auctionService.getBoard(num);
		model.addAttribute("sellVO", sellVO);
		model.addAttribute("aPageNum", aPageNum);
		model.addAttribute("price", price);
		return "auction/auctionContent";
	} // auctionContent Get
	
	
	@PostMapping("/auctionProcess")
	public ResponseEntity<String> auctionProcess(int num, @RequestParam(defaultValue = "1") int aPageNum, 
			@RequestParam(defaultValue = "0") int price, String username, BuyVO buyVO, HttpSession session, Model model) {
		MemberVO loginMember = (MemberVO) session.getAttribute("loginMember");
		if (loginMember == null) {
			String message = "로그인이 필요한 서비스입니다.";
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "text/html; charset=UTF-8"); 
			StringBuilder sb = new StringBuilder();
			sb.append("<script>");
			sb.append("alert('"+message+"');");
			sb.append("location.href = '/member/login';");
			sb.append("</script>");
			return new ResponseEntity<String>(sb.toString(), headers, HttpStatus.OK);
		}
		int count = auctionService.getBuyCount(num);
		int maxPrice = auctionService.getMaxPrice(num);
		model.addAttribute("aPageNum", aPageNum);
		model.addAttribute("num", num);
		if (count == 0) {
			auctionService.insertBuy(buyVO);
			if (price == maxPrice) {
				HttpHeaders headers = new HttpHeaders();
				headers.add("Content-Type", "text/html; charset=UTF-8"); 
				StringBuilder sb = new StringBuilder();
				sb.append("<script>");
				sb.append("location.href = '/auction/auctionPay?username="+username+"&price="+price+"';");
				sb.append("</script>");
				return new ResponseEntity<String>(sb.toString(), headers, HttpStatus.OK);
			}
		} else {
			if (price > maxPrice) {
				auctionService.updateBuy(buyVO);
			} else if (price == maxPrice) {
				HttpHeaders headers = new HttpHeaders();
				headers.add("Content-Type", "text/html; charset=UTF-8"); 
				StringBuilder sb = new StringBuilder();
				sb.append("<script>");
				sb.append("location.href = '/auction/auctionPay?username="+username+"&price="+price+"';");
				sb.append("</script>");
				return new ResponseEntity<String>(sb.toString(), headers, HttpStatus.OK);
			}
		}
		String message = "입찰이 성공적으로 완료되었습니다.";
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "text/html; charset=UTF-8"); 
		StringBuilder sb = new StringBuilder();
		sb.append("<script>");
		sb.append("alert('"+message+"');");
		sb.append("location.href = '/auction/auctionContent?num="+num+"&aPageNum="+aPageNum+"';");
		sb.append("</script>");
		return new ResponseEntity<String>(sb.toString(), headers, HttpStatus.OK);
	} // auctionProcess Post
	
	
	@GetMapping("/auctionPay")
	public String auctionPay(String username, @RequestParam(defaultValue = "0") int price, Model model) {
		MemberVO buyerVO = memberService.getMember(username);
		model.addAttribute("price", price);
		model.addAttribute("buyerVO", buyerVO);
		return "auction/auctionPay";
	}  // auctionPay Get
}
