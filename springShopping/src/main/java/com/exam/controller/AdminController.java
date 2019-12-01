package com.exam.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exam.domain.BoardVO;
import com.exam.domain.FboardVO;
import com.exam.domain.MemberVO;
import com.exam.service.AdminService;
import com.exam.service.CommunityService;
import com.exam.service.MemberService;
import com.exam.service.TradeService;

@Controller
@RequestMapping("/admin/*")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private TradeService tradeService;
	
	@Autowired
	private CommunityService communityService;
	
	@GetMapping("/adminPage")
	public String adminPage(HttpSession session) {
		return "admin/adminPage";
	} // adminPage Get
	
	
	@GetMapping("/members")
	public String members(@RequestParam(defaultValue = "1") int mPageNum,
			@RequestParam(defaultValue = "") String mSearch,
			HttpSession session, Model model) {
		int pageSize = 30;
		int startRow = (mPageNum - 1) * pageSize + 1;
		int count = memberService.getMemberCount(mSearch);
		List<MemberVO> mBoardList = memberService.getMembers(mSearch, startRow, pageSize);
		int pageCount = count / pageSize + ((count % pageSize == 0) ? 0 : 1);
		int pageBlock = 10;
		int startPage = ((mPageNum - 1) / pageBlock) * pageBlock + 1;
		int endPage = startPage + pageBlock - 1;
		if (endPage > pageCount) {
			endPage = pageCount;
		}
		List<Map<String, Object>> list = adminService.getAddressOfMember();
		JSONArray jsonArray = new JSONArray();
		JSONArray titleArray = new JSONArray();
		titleArray.add("지역별");
		titleArray.add("지역별 회원수");
		jsonArray.add(titleArray);	
		for (Map<String, Object> map : list) {
			JSONArray rowArray = new JSONArray();
			rowArray.add(map.get("address"));
			rowArray.add(map.get("cnt"));
			jsonArray.add(rowArray);
		}
		Map<String, Integer> membersPageInfoMap = new HashMap<String, Integer>();
		membersPageInfoMap.put("count", count);
		membersPageInfoMap.put("pageCount", pageCount);
		membersPageInfoMap.put("pageBlock", pageBlock);
		membersPageInfoMap.put("startPage", startPage);
		membersPageInfoMap.put("endPage", endPage);
		
		model.addAttribute("mBoardList", mBoardList);
		model.addAttribute("membersPageInfoMap", membersPageInfoMap);
		model.addAttribute("jsonArray", jsonArray);
		model.addAttribute("mSearch", mSearch);
		model.addAttribute("mPageNum", mPageNum);
		return "admin/members";
	} // members Get
	
	
	@RequestMapping(value = "/updateMembers", method = {RequestMethod.POST}, produces = "application/json; charset=utf8")
	@ResponseBody
	public String updateMembers(@RequestParam(defaultValue = "1") int mPageNum,
			@RequestParam("idValues") String[] idValues, @RequestParam("passwdValues") String[] passwdValues) {
		for (int i = 0; i < idValues.length; i++) {
			memberService.updatePasswdOfMember(passwdValues[i], idValues[i]);
		}
		return "admin/members?mPageNumm="+mPageNum;
	} // updateMembers Post
	
	
	@PostMapping("/deleteMembers")
	public ResponseEntity<String> deleteMembers(@RequestParam(defaultValue = "1") int mPageNum, String[] check) {
		if (check != null) {
			for (int i = 0; i < check.length; i++) {
				memberService.deleteMember(check[i]);
			}	
		}
		String message = "강제탈퇴가 성공적으로 완료되었습니다.";
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "text/html; charset=UTF-8"); 
		StringBuilder sb = new StringBuilder();
		sb.append("<script>");
		sb.append("alert('"+message+"');");
		sb.append("location.href = '/admin/members?mPageNum="+mPageNum+"';");
		sb.append("</script>");
		return new ResponseEntity<String>(sb.toString(), headers, HttpStatus.OK);
	} // deleteMembers Post
	
	
	@GetMapping("/boards")
	public String boards(@RequestParam(defaultValue = "1") int tPageNum,
			@RequestParam(defaultValue = "") String tSearch,
			@RequestParam(defaultValue = "1") int fPageNum,
			@RequestParam(defaultValue = "") String fSearch,
			HttpSession session, Model model) {
		// 중고매매 게시판
		int tPageSize = 15;
		int tStartRow = (tPageNum - 1) * tPageSize + 1;
		int tCount = tradeService.getBoardCount(tSearch);
		List<BoardVO> tBoardList = adminService.getAdminTboards(tStartRow, tPageSize, tSearch);
		int tPageCount = tCount / tPageSize + ((tCount % tPageSize == 0) ? 0 : 1);
		int tPageBlock = 10;
		int tStartPage = ((tPageNum - 1) / tPageBlock) * tPageBlock + 1;
		int tEndPage = tStartPage + tPageBlock - 1;
		if (tEndPage > tPageCount) {
			tEndPage = tPageCount;
		}
		Map<String, Integer> tPageInfoMap = new HashMap<String, Integer>();
		tPageInfoMap.put("tCount", tCount);
		tPageInfoMap.put("tPageCount", tPageCount);
		tPageInfoMap.put("tPageBlock", tPageBlock);
		tPageInfoMap.put("tStartPage", tStartPage);
		tPageInfoMap.put("tEndPage", tEndPage);
		
		// 자유 게시판
		int fPageSize = 15;
		int fStartRow = (fPageNum - 1) * fPageSize + 1;
		int fCount = communityService.getBoardCount(fSearch);
		List<FboardVO> fBoardList = adminService.getAdminFboards(fStartRow, fPageSize, fSearch);
		int fPageCount = fCount / fPageSize + ((fCount % fPageSize == 0) ? 0 : 1);
		int fPageBlock = 10;
		int fStartPage = ((fPageNum - 1) / fPageBlock) * fPageBlock + 1;
		int fEndPage = fStartPage + fPageBlock - 1;
		if (fEndPage > fPageCount) {
			fEndPage = fPageCount;
		}
		Map<String, Integer> fPageInfoMap = new HashMap<String, Integer>();
		fPageInfoMap.put("fCount", fCount);
		fPageInfoMap.put("fPageCount", fPageCount);
		fPageInfoMap.put("fPageBlock", fPageBlock);
		fPageInfoMap.put("fStartPage", fStartPage);
		fPageInfoMap.put("fEndPage", fEndPage);
		List<Map<String, Object>> list = adminService.getTotalCountOfBoards();
		JSONArray jsonArray = new JSONArray();
		JSONArray titleArray = new JSONArray();
		titleArray.add("테이블별");
		titleArray.add("작성글 개수");
		jsonArray.add(titleArray);
		for (Map<String, Object> map : list) {
			JSONArray rowArray = new JSONArray();
			rowArray.add(map.get("tableName"));
			rowArray.add(map.get("numrow"));
			jsonArray.add(rowArray);
		}
		model.addAttribute("tBoardList", tBoardList);
		model.addAttribute("fBoardList", fBoardList);
		model.addAttribute("tPageInfoMap", tPageInfoMap);
		model.addAttribute("fPageInfoMap", fPageInfoMap);
		model.addAttribute("jsonArray", jsonArray);
		model.addAttribute("tSearch", tSearch);
		model.addAttribute("fSearch", fSearch);
		model.addAttribute("tPageNum", tPageNum);
		model.addAttribute("fPageNum", fPageNum);
		return "admin/boards";
	} // boards Get
	
	
	@PostMapping("/deleteBoards")
	public ResponseEntity<String> deleteBoards(String[] tCheck, String[] fCheck) {
		if (tCheck != null) {
			for (int i = 0; i < tCheck.length; i++) {
				tradeService.deleteBoard(Integer.parseInt(tCheck[i]));
			}	
		}
		if (fCheck != null) {
			for (int i = 0; i < fCheck.length; i++) {
				communityService.deleteBoard(Integer.parseInt(fCheck[i]));
			}	
		}
		String message = "게시물 삭제가 성공적으로 완료되었습니다.";
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "text/html; charset=UTF-8"); 
		StringBuilder sb = new StringBuilder();
		sb.append("<script>");
		sb.append("alert('"+message+"');");
		sb.append("location.href = '/admin/boards';");
		sb.append("</script>");
		return new ResponseEntity<String>(sb.toString(), headers, HttpStatus.OK);
	} // deleteBoards Post
}
