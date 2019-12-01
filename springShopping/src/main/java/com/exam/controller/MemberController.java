package com.exam.controller;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.exam.domain.CityVO;
import com.exam.domain.MemberVO;
import com.exam.service.CityService;
import com.exam.service.EmailService;
import com.exam.service.MemberService;

@Controller
@RequestMapping("/member/*")
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private CityService cityService;
	
	@GetMapping("/join")
	public String join() {
		return "member/join";
	} // join Get
	
	
	@GetMapping("/joinIdDupCheck")
	public String joinIdDupCheck(String id, Model model) {
		boolean isIdDup = memberService.isIdDuplicated(id);
		model.addAttribute("isIdDup", isIdDup);
		model.addAttribute("userid", id);
		return "member/joinIdDupCheck";
	} // joinIdDupCheck
	
	
	@GetMapping("/joinIdDupCheckJson")
	@ResponseBody
	public Boolean joinIdDupCheckJson(@RequestParam("id") String id) {
		boolean isIdDup = memberService.isIdDuplicated(id);
		return isIdDup;
	} // joinIdDupCheckJson
	
	
	@GetMapping("/joinAddressCheck")
	public String joinAddressCheck(@RequestParam(defaultValue = "1") int addrPageNum, @RequestParam(defaultValue = "", required = false) String inputAdd, Model model) {
		int pageSize = 15;
		int startRow = (addrPageNum - 1) * pageSize + 1;
		List<CityVO> addrBoardList = cityService.getBoards(startRow, pageSize, inputAdd);
		int count = cityService.getBoardCount(inputAdd);
		int pageCount = count / pageSize + ((count % pageSize == 0) ? 0 : 1);
		int pageBlock = 10;
		int startPage = ((addrPageNum - 1) / pageBlock) * pageBlock + 1;
		int endPage = startPage + pageBlock - 1;
		if (endPage > pageCount) {
			endPage = pageCount;
		}
		Map<String, Integer> addrPageInfoMap = new HashMap<String, Integer>();
		addrPageInfoMap.put("count", count);
		addrPageInfoMap.put("pageCount", pageCount);
		addrPageInfoMap.put("pageBlock", pageBlock);
		addrPageInfoMap.put("startPage", startPage);
		addrPageInfoMap.put("endPage", endPage);
		
		model.addAttribute("addrBoardList", addrBoardList);
		model.addAttribute("addrPageInfoMap", addrPageInfoMap);
		model.addAttribute("addrPageNum", addrPageNum);
		model.addAttribute("inputAdd", inputAdd);
		return "member/joinAddCheck";
	} // joinAddressCheck
	
	
	@PostMapping("/join")
	public ResponseEntity<String> join(MemberVO memberVO) {
		memberVO.setRegDate(new Timestamp(System.currentTimeMillis()));
		memberService.insertMember(memberVO);
		String message = "회원가입이 성공적으로 완료 되었습니다.";
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "text/html; charset=UTF-8"); 
		StringBuilder sb = new StringBuilder();
		sb.append("<script>");
		sb.append("alert('"+message+"');");
		sb.append("location.href = '/member/login';");
		sb.append("</script>");
		return new ResponseEntity<String>(sb.toString(), headers, HttpStatus.OK); 
	} // join Post

	
	@GetMapping("/login")
	public String login() {
		return "member/login";
	} // login Get
	
	
	@PostMapping("/loginProcess")
	public ResponseEntity<String> login(@RequestParam("id") String id, @RequestParam("passwd") String passwd,
			String rememberMe, HttpSession session, HttpServletResponse response) {
		int check = memberService.userCheck(id, passwd);
		if (check != 1) {
			String message = "아이디가 존재하지 않거나 비밀번호가 일치하지 않습니다.";
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "text/html; charset=UTF-8"); 
			StringBuilder sb = new StringBuilder();
			sb.append("<script>");
			sb.append("alert('"+message+"');");
			sb.append("history.back();");
			sb.append("</script>");
			return new ResponseEntity<String>(sb.toString(), headers, HttpStatus.OK);
		}
		MemberVO loginMember = memberService.getMember(id);
		session.setAttribute("loginMember", loginMember);
		if (rememberMe != null && rememberMe.equals("true")) {
			Cookie cookie = new Cookie("id", id);
			cookie.setMaxAge(60 * 60);
			cookie.setPath("/");
			response.addCookie(cookie);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", "/");
		return new ResponseEntity<String>(headers, HttpStatus.FOUND);
	} // loginProcess Post
	
	
	@GetMapping("/logout")
	public String logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		session.invalidate();
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("id")) {
					cookie.setMaxAge(0);
					cookie.setPath("/");
					response.addCookie(cookie);
				}
			}
		}
		return "redirect:/";
	} // logout
	
	
	@GetMapping("/update")
	public String update(HttpSession session) {
		MemberVO loginMember = (MemberVO) session.getAttribute("loginMember");
		if (loginMember == null) {
			return "redirect:/";
		}
		return "member/update";
	} // update Get

	
	@PostMapping("/update")
	public ResponseEntity<String> update(MemberVO memberVO, HttpSession session) {
		MemberVO loginMember = (MemberVO) session.getAttribute("loginMember");
		String id = loginMember.getId();
		String passwd = memberVO.getPasswd();
		int check = memberService.userCheck(id, passwd);
		if (check != 1) {
			String message = "비밀번호가 일치하지 않습니다.";
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "text/html; charset=UTF-8"); 
			StringBuilder sb = new StringBuilder();
			sb.append("<script>");
			sb.append("alert('"+message+"');");
			sb.append("history.back();");
			sb.append("</script>");
			return new ResponseEntity<String>(sb.toString(), headers, HttpStatus.OK);
		}	
		memberService.updateMember(memberVO);
		MemberVO updatedMember = memberService.getMember(id);
		session.setAttribute("loginMember", updatedMember);
		String message = "회원정보가 성공적으로 수정되었습니다.";
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "text/html; charset=UTF-8"); 
		StringBuilder sb = new StringBuilder();
		sb.append("<script>");
		sb.append("alert('"+message+"');");
		sb.append("location.href = '/';");
		sb.append("</script>");
		return new ResponseEntity<String>(sb.toString(), headers, HttpStatus.OK);
	} // update Post
	
	
	@GetMapping("/updatePasswd")
	public String updatePasswd(HttpSession session) {
		MemberVO loginMember = (MemberVO) session.getAttribute("loginMember");
		if (loginMember == null) {
			return "redirect:/";
		}
		return "member/updatePasswd";
	} // updatePasswd Get
	
	
	@GetMapping("/updatePasswdDupCheckJson")
	@ResponseBody
	public Boolean updatePasswdDupCheckJson(@RequestParam("id") String id, @RequestParam("newpasswd") String newpasswd) {
		boolean isPasswdDup = memberService.isPasswdDuplicated(id, newpasswd);
		return isPasswdDup;
	} // joinIdDupCheckJson Get
	
	
	@PostMapping("/updatePasswd")
	public ResponseEntity<String> updatePasswd(@RequestParam("id") String id, @RequestParam("newpasswd") String newpasswd, HttpSession session) {
		memberService.updatePasswdOfMember(newpasswd, id);
		MemberVO updatedMember = memberService.getMember(id);
		session.setAttribute("loginMember", updatedMember);
		String message = "비밀번호가 성공적으로 변경되었습니다.";
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "text/html; charset=UTF-8"); 
		StringBuilder sb = new StringBuilder();
		sb.append("<script>");
		sb.append("alert('"+message+"');");
		sb.append("location.href = '/';");
		sb.append("</script>");
		return new ResponseEntity<String>(sb.toString(), headers, HttpStatus.OK);
	} // updatePasswd Post
	
	
	@GetMapping("/delete")
	public String delete(HttpSession session) {
		MemberVO loginMember = (MemberVO) session.getAttribute("loginMember");
		if (loginMember == null) {
			return "redirect:/";
		}
		return "member/delete";
	} // delete Get
	
	
	@PostMapping("/delete")
	public ResponseEntity<String> delete(@RequestParam("passwd") String passwd, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		MemberVO loginMember = (MemberVO) session.getAttribute("loginMember");
		int check = memberService.userCheck(loginMember.getId(), passwd);
		if (check == 1) {
			memberService.deleteMember(loginMember.getId());
			session.invalidate();
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("id")) {
						cookie.setMaxAge(0);
						cookie.setPath("/");
						response.addCookie(cookie);
					}
				}
			} // cookie
			String message = "회원 탈퇴가 성공적으로 완료되었습니다.";
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "text/html; charset=UTF-8"); 
			StringBuilder sb = new StringBuilder();
			sb.append("<script>");
			sb.append("alert('"+message+"');");
			sb.append("location.href = '/';");
			sb.append("</script>");
			return new ResponseEntity<String>(sb.toString(), headers, HttpStatus.OK);
		} else {
			String message = "비밀번호가 일치하지 않습니다.";
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "text/html; charset=UTF-8"); 
			StringBuilder sb = new StringBuilder();
			sb.append("<script>");
			sb.append("alert('"+message+"');");
			sb.append("history.back();");
			sb.append("</script>");
			return new ResponseEntity<String>(sb.toString(), headers, HttpStatus.OK);
		}
	} // delete Post
	
	
	@GetMapping("/missingAcc")
	public String missingAcc() {
		return "member/missingAcc";
	} // memberMissingAcc
	
	
	@GetMapping("/memberFindId")
	public String memberFindId() {
		return "member/findId";
	} // memberFindId Get
	
	
	@PostMapping("/memberFindId")
	public ResponseEntity<String> memberFindId(@RequestParam("name") String name, @RequestParam("email") String email) {
		int check = memberService.findId(name, email);
		if (check == 1) {
			MemberVO memberVO = memberService.getMemberByNameAndEmail(name, email);
			emailService.sendId(memberVO.getEmail(), memberVO.getName(), "회원님의 아이디는 " + memberVO.getId() + "입니다.");
			String message = "회원님의 이메일 주소로 회원님의 아이디가 전송되었습니다.";
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "text/html; charset=UTF-8"); 
			StringBuilder sb = new StringBuilder();
			sb.append("<script>");
			sb.append("alert('"+message+"');");
			sb.append("close();");
			sb.append("</script>");
			return new ResponseEntity<String>(sb.toString(), headers, HttpStatus.OK);
		} else {
			String message = "회원정보가 일치하지 않습니다.";
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "text/html; charset=UTF-8"); 
			StringBuilder sb = new StringBuilder();
			sb.append("<script>");
			sb.append("alert('"+message+"');");
			sb.append("history.back();");
			sb.append("</script>");
			return new ResponseEntity<String>(sb.toString(), headers, HttpStatus.OK);
		}
	} // memberFindId Post
	
	
	@GetMapping("/memberFindPasswd")
	public String memberFindPasswd() {
		return "member/findPasswd";
	} // memberFindPasswd Get

	
	@PostMapping("/memberFindPasswd")
	public ResponseEntity<String> memberFindPasswd(@RequestParam("id") String id, @RequestParam("email") String email) {
		int check = memberService.findPasswd(id, email);
		if (check == 1) {
			MemberVO memberVO = memberService.getMemberByIdAndEmail(id, email);
			StringBuffer temp = new StringBuffer();
		    Random ran = new Random();
		    for (int i = 0; i < 8; i++) {
		        int ranNum = ran.nextInt(3);
		        switch (ranNum) {
		        case 0:
		            // a-z
		            temp.append((char) ((int) (ran.nextInt(26)) + 97));
		            break;
		        case 1:
		            // A-Z
		            temp.append((char) ((int) (ran.nextInt(26)) + 65));
		            break;
		        case 2:
		            // 0-9
		            temp.append((ran.nextInt(10)));
		            break;
		        }
		    }
		    String temporaryPasswd = temp.toString();
			emailService.sendPasswd(memberVO.getEmail(), memberVO.getName(), "임시 비밀번호는 " + temporaryPasswd + "입니다.");
			String message = "회원님의 이메일 주소로 임시 비밀번호가 전송되었습니다. \n로그인 후 비밀번호를 변경하여 주세요.";
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "text/html; charset=UTF-8"); 
			StringBuilder sb = new StringBuilder();
			sb.append("<script>");
			sb.append("alert('"+message+"');");
			sb.append("close();");
			sb.append("</script>");
			memberService.updatePasswdOfMemberByEmail(temporaryPasswd, id, email);
			return new ResponseEntity<String>(sb.toString(), headers, HttpStatus.OK);
		} else {
			String message = "회원정보가 일치하지 않습니다.";
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "text/html; charset=UTF-8"); 
			StringBuilder sb = new StringBuilder();
			sb.append("<script>");
			sb.append("alert('"+message+"');");
			sb.append("history.back();");
			sb.append("</script>");
			return new ResponseEntity<String>(sb.toString(), headers, HttpStatus.OK);
		}
	} // memberFindPasswd Post
}
