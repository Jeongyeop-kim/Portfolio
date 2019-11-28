package com.exam.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.exam.domain.FattachVO;
import com.exam.domain.FboardVO;
import com.exam.domain.FreplyVO;
import com.exam.domain.MemberVO;
import com.exam.service.CommunityAttachService;
import com.exam.service.CommunityService;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;

@Controller
@RequestMapping("/community/*")
@Log4j
public class CommunityController {

	@Autowired
	private CommunityService communityService;
	
	@Autowired
	private CommunityAttachService communityAttachService;
	
	@GetMapping("/free")
	public String free(@RequestParam(defaultValue = "1") int pageNum,
			@RequestParam(defaultValue = "") String search,
			@RequestParam(defaultValue = "subject") String searchType, Model model) {
		int pageSize = 15;
		int startRow = (pageNum - 1) * pageSize + 1;
		int count = communityService.getBoardCount(search);
		List<FboardVO> fBoardList = communityService.getBoards(startRow, pageSize, search);
		int pageCount = count / pageSize + ((count % pageSize == 0) ? 0 : 1);
		int pageBlock = 10;
		int startPage = ((pageNum - 1) / pageBlock) * pageBlock + 1;
		int endPage = startPage + pageBlock - 1;
		if (endPage > pageCount) {
			endPage = pageCount;
		}
		switch (searchType) {
		case "subject":
			count = communityService.getBoardCount(search);
			fBoardList = communityService.getBoards(startRow, pageSize, search);
			break;
		case "username":
			count = communityService.getUsernameOfBoardCount(search);
			fBoardList = communityService.getUsernameOfBoards(startRow, pageSize, search);
			break;
		case "content":
			count = communityService.getContentOfBoardCount(search);
			fBoardList = communityService.getContentOfBoards(startRow, pageSize, search);
			break;
		}
		Map<String, Integer> freePageInfoMap = new HashMap<String, Integer>();
		freePageInfoMap.put("count", count);
		freePageInfoMap.put("pageCount", pageCount);
		freePageInfoMap.put("pageBlock", pageBlock);
		freePageInfoMap.put("startPage", startPage);
		freePageInfoMap.put("endPage", endPage);
		
		model.addAttribute("fBoardList", fBoardList);
		model.addAttribute("freePageInfoMap", freePageInfoMap);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("search", search);
		model.addAttribute("searchType", searchType);
		model.addAttribute("freplyDao", communityService);
		return "community/freeBoard";
	} // free Get
	
	
	@GetMapping("/freeWrite")
	public String freeWrite(HttpSession session) {
		MemberVO loginMember = (MemberVO) session.getAttribute("loginMember");
		if (loginMember == null) {
			return "redirect:/community/free";
		}
		return "community/freeWrite";
	} // freeWrite Get
	
	
	@PostMapping("/freeWrite")
	public String freeWrite(MultipartFile[] files, FboardVO boardVO, HttpServletRequest request) throws Exception {
		int num = communityService.nextBoardNum();
		boardVO.setNum(num);
		boardVO.setIp(request.getRemoteAddr());
		boardVO.setReadcount(0);
		boardVO.setReRef(num);
		boardVO.setReLev(0);
		boardVO.setReSeq(0);
		boardVO.setGood(0);
		boardVO.setGooduser("/");
		ServletContext application = request.getServletContext();
		String realPath = application.getRealPath("/resources/upload");
		log.info("realPath: " + realPath);
		File uploadPath = new File(realPath, getFolder());
		log.info("uploadPath: " + uploadPath);
		if (!uploadPath.exists()) {
			uploadPath.mkdirs();
		}
		List<FattachVO> attachList = new ArrayList<FattachVO>();
		for (MultipartFile multipartFile : files) {
			log.info("파일명: " + multipartFile.getOriginalFilename());
			log.info("파일크기: " + multipartFile.getSize());
			if (multipartFile.isEmpty()) {
				continue;
			}
			String uploadFileName = multipartFile.getOriginalFilename();
			UUID uuid = UUID.randomUUID();
			uploadFileName = uuid.toString() + "_" + uploadFileName;
			log.info("최종 업로드 파일명: " + uploadFileName);
			File saveFile = new File(uploadPath, uploadFileName);
			multipartFile.transferTo(saveFile);
			FattachVO attachVO = new FattachVO();
			attachVO.setBno(boardVO.getNum());
			attachVO.setUuid(uuid.toString());
			attachVO.setUploadpath(getFolder());
			attachVO.setFilename(multipartFile.getOriginalFilename());
			if (isImageType(saveFile)) {
				File thumbnailFile = new File(uploadPath, "s_" + uploadFileName);
				try (FileOutputStream fos = new FileOutputStream(thumbnailFile)) {
					Thumbnailator.createThumbnail(multipartFile.getInputStream(), fos, 100, 100);
				}
				attachVO.setFiletype("I");
			} else {
				attachVO.setFiletype("O");
			}
			attachList.add(attachVO);
		}
		communityService.insertBoardAndAttaches(boardVO, attachList);
		return "redirect:/community/free";
	}
	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		String str = sdf.format(date);
		return str;
	}
	private boolean isImageType(File file) throws IOException {
		boolean isImageType = false;
		String contentType = Files.probeContentType(file.toPath());
		log.info("contentType: " + contentType);
		if (contentType != null) {
			isImageType = contentType.startsWith("image");
		} else {
			isImageType = false;
		}
		return isImageType;
	} // freeWrite Post


	@GetMapping("/freeContent")
	public String freeContent(@RequestParam(defaultValue = "1") int pageNum,
			@RequestParam(defaultValue = "1") int replyPageNum,
			int num, Model model) {
		FboardVO fboardVO = communityService.getBoard(num);
		communityService.updateReadCount(num);
		List<FattachVO> attachList = communityAttachService.getAttaches(num);
		int pageSize = 10;
		int startRow = (replyPageNum - 1) * pageSize + 1; 
		int count = communityService.getReplyCount(num);
		List<FreplyVO> freplyList = communityService.getReplys(startRow, pageSize);
		int pageCount = count / pageSize + ((count % pageSize == 0) ? 0 : 1);
		int pageBlock = 5;
		int startPage = ((replyPageNum - 1) / pageBlock) * pageBlock + 1;
		int endPage = startPage + pageBlock - 1;
		if (endPage > pageCount) {
			endPage = pageCount;
		}
		Map<String, Integer> replyPageInfoMap = new HashMap<String, Integer>();
		replyPageInfoMap.put("count", count);
		replyPageInfoMap.put("pageCount", pageCount);
		replyPageInfoMap.put("pageBlock", pageBlock);
		replyPageInfoMap.put("startPage", startPage);
		replyPageInfoMap.put("endPage", endPage);

		model.addAttribute("fboardVO", fboardVO);
		model.addAttribute("attachList", attachList);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("freplyList", freplyList);
		model.addAttribute("replyPageNum", replyPageNum);
		model.addAttribute("replyPageInfoMap", replyPageInfoMap);
		return "community/freeContent";
	} // freeContent Get
	
	
	@GetMapping("/goodJson")
	@ResponseBody
	public Boolean goodJson(String username, int num) {
		boolean isUserRec = communityService.IsUserRecommendedBoard(num, username);
		if (isUserRec == true) {
			return isUserRec;
		} else {
			communityService.updateGoodCount(username, num);
			return isUserRec;
		}
	}
	
	
	@GetMapping("/freeUpdate")
	public String freeUpdate(@RequestParam(defaultValue = "1") int pageNum, 
			int num, HttpSession session, Model model) {
		MemberVO loginMember = (MemberVO) session.getAttribute("loginMember");
		if (loginMember == null) {
			return "redirect:/community/free";
		}
		FboardVO fboardVO = communityService.getBoard(num);
		List<FattachVO> attachList = communityAttachService.getAttaches(num);
		model.addAttribute("num", num);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("fboardVO", fboardVO);
		model.addAttribute("attachList", attachList);
		return "community/freeUpdate";
	} // freeUpdate Get
	
	
	@PostMapping("/freeUpdate")
	public ResponseEntity<String> freeUpdate(MultipartFile[] newFiles, FboardVO boardVO, int pageNum, int num, 
			HttpServletRequest request, MultipartHttpServletRequest multi) throws Exception {
		ServletContext application = request.getServletContext();
		String realPath = application.getRealPath("/resources/upload");
		log.info("realPath: " + realPath);
		File uploadPath = new File(realPath, getFolder());
		log.info("uploadPath: " + uploadPath);
		if (!uploadPath.exists()) {
			uploadPath.mkdirs();
		}
		List<FattachVO> attachList = new ArrayList<FattachVO>();
		for (MultipartFile multipartFile : newFiles) {
			log.info("파일명: " + multipartFile.getOriginalFilename());
			log.info("파일크기: " + multipartFile.getSize());
			if (multipartFile.isEmpty()) {
				continue;
			}
			String uploadFileName = multipartFile.getOriginalFilename();
			UUID uuid = UUID.randomUUID();
			uploadFileName = uuid.toString() + "_" + uploadFileName;
			log.info("최종 업로드 파일명: " + uploadFileName);
			File saveFile = new File(uploadPath, uploadFileName);
			multipartFile.transferTo(saveFile);
			FattachVO attachVO = new FattachVO();
			attachVO.setBno(boardVO.getNum());
			attachVO.setUuid(uuid.toString());
			attachVO.setUploadpath(getFolder());
			attachVO.setFilename(multipartFile.getOriginalFilename());
			if (isImageType(saveFile)) {
				File thumbnailFile = new File(uploadPath, "s_" + uploadFileName);
				try (FileOutputStream fos = new FileOutputStream(thumbnailFile)) {
					Thumbnailator.createThumbnail(multipartFile.getInputStream(), fos, 100, 100);
				}
				attachVO.setFiletype("I");
			} else {
				attachVO.setFiletype("O");
			}
			attachList.add(attachVO);
		} // for
		communityAttachService.insertAttaches(attachList);
		// 파일 삭제
		String[] delFiles = multi.getParameterValues("delFiles");
		if (delFiles != null) {
			for (String str : delFiles) {
				String[] strArr = str.split("_");
				String uuid = strArr[0];
				String delFilename = strArr[1];
				log.info("uuid : " + uuid);
				log.info("delFilename : " + delFilename);
				FattachVO attached = communityAttachService.getAttachByUuid(uuid);
				realPath = application.getRealPath("/resources/upload/"+attached.getUploadpath()+"");
				log.info(realPath);
				File delFile = new File(realPath, uuid+"_"+delFilename);
				File thumbnailFile = new File(realPath, "s_"+uuid+"_"+delFilename);
				if (delFile.exists()) {
					delFile.delete();
					thumbnailFile.delete();
				}
				communityAttachService.deleteAttachByUuid(uuid);
			} // for
		} // if
		communityService.updateBoard(boardVO);
		String message = "게시글이 수정되었습니다.";
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "text/html; charset=UTF-8"); 
		StringBuilder sb = new StringBuilder();
		sb.append("<script>");
		sb.append("alert('"+message+"');");
		sb.append("location.href = '/community/freeContent?num="+boardVO.getNum()+"&pageNum="+pageNum+"';");
		sb.append("</script>");
		return new ResponseEntity<String>(sb.toString(), headers, HttpStatus.OK);
	} // freeUpdate Post
	
	
	@RequestMapping(value="/freeDelete" , method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<String> freeDelete(@RequestParam(defaultValue = "1") int pageNum, int num, HttpServletRequest request) {
		List<FattachVO> attachList = communityAttachService.getAttaches(num);
		for (FattachVO attachVO : attachList) {
			ServletContext application = request.getServletContext();
			String realPath = application.getRealPath("/resources/upload/"+attachVO.getUploadpath()+"");
			File file = new File(realPath, attachVO.getUuid()+"_"+attachVO.getFilename());
			File thumbnailFile = new File(realPath, "s_"+attachVO.getUuid()+"_"+attachVO.getFilename());
			if (file.exists()) {
				file.delete();
				thumbnailFile.delete();
			}
		}
		communityService.deleteBoard(num);
		communityAttachService.deleteAttachByBno(num);
		String message = "게시글이 삭제되었습니다.";
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "text/html; charset=UTF-8"); 
		StringBuilder sb = new StringBuilder();
		sb.append("<script>");
		sb.append("alert('"+message+"');");
		sb.append("location.href = '/community/free?pageNum="+pageNum+"';");
		sb.append("</script>");
		return new ResponseEntity<String>(sb.toString(), headers, HttpStatus.OK);
	} // freeDelete Post
	
	
	@PostMapping("/freeReply")
	public ResponseEntity<String> freeReply(FreplyVO freplyVO, @RequestParam(defaultValue = "1") int pageNum, int num, HttpSession session, HttpServletRequest request, Model model) {
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
		int replyNum = communityService.nextReplyNum();
		freplyVO.setNum(replyNum);
		freplyVO.setIp(request.getRemoteAddr());
		freplyVO.setReRef(replyNum);
		freplyVO.setReLev(0);
		freplyVO.setReSeq(0);
		freplyVO.setParentnum(num);
		communityService.insertReply(freplyVO);
		model.addAttribute("num", num);
		model.addAttribute("pageNum", pageNum);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "text/html; charset=UTF-8"); 
		StringBuilder sb = new StringBuilder();
		sb.append("<script>");
		sb.append("location.href = '/community/freeContent?num="+num+"&pageNum="+pageNum+"';");
		sb.append("</script>");
		return new ResponseEntity<String>(sb.toString(), headers, HttpStatus.OK);
	} // freeReply Post
	
	
	@RequestMapping(value = "/freeReplyUpdateJson", method = {RequestMethod.POST}, produces = "application/json; charset=utf8")
	@ResponseBody
	public String freeReplyUpdateJson(@RequestParam(defaultValue = "1") int pageNum, 
			int num, @RequestParam(defaultValue = "1") int replyNum, String uContent, HttpSession session, Model model) {
		MemberVO loginMember = (MemberVO) session.getAttribute("loginMember");
		if (loginMember == null) {
			return "redirect:/community/freeContent?num="+num+"&pageNum="+pageNum+"";
		}
		communityService.updateReply(uContent, replyNum);
		FreplyVO freplyVO = communityService.getReply(replyNum);
		String updateCompleted = freplyVO.getContent();
		return updateCompleted;
	} // freeReplyUpdateJson Post
	
	
	@PostMapping("/freeReplyDeleteJson")
	@ResponseBody
	public String freeReplyDeleteJson(@RequestParam(defaultValue = "1") int pageNum, 
			int num, @RequestParam(defaultValue = "1") int replyNum, HttpSession session) {
		MemberVO loginMember = (MemberVO) session.getAttribute("loginMember");
		if (loginMember == null) {
			return "redirect:/community/freeContent?num="+num+"&pageNum="+pageNum+"";
		}
		communityService.deleteReply(replyNum);
		return "community/freeContent?num="+num+"&pageNum="+pageNum+"";
	} // freeReplyDeleteJson Post
	
	
	@GetMapping("/freeReWrite")
	public String freeReWrite(int reRef, int reLev, int reSeq, HttpSession session, Model model) {
		MemberVO loginMember = (MemberVO) session.getAttribute("loginMember");
		if (loginMember == null) {
			return "redirect:/community/free";
		}
		model.addAttribute("reRef", reRef);
		model.addAttribute("reLev", reLev);
		model.addAttribute("reSeq", reSeq);
		return "community/freeReWrite";
	} // freeReWrite Get
	
	
	@PostMapping("/freeReWrite")
	public String freeReWrite(MultipartFile[] files, FboardVO boardVO, int reRef, int reLev, int reSeq, HttpServletRequest request) throws Exception {
		int num = communityService.nextBoardNum();
		boardVO.setNum(num);
		boardVO.setIp(request.getRemoteAddr());
		boardVO.setReadcount(0);
		boardVO.setGood(0);
		boardVO.setGooduser("/");
		ServletContext application = request.getServletContext();
		String realPath = application.getRealPath("/resources/upload");
		log.info("realPath: " + realPath);
		File uploadPath = new File(realPath, getFolder());
		log.info("uploadPath: " + uploadPath);
		if (!uploadPath.exists()) {
			uploadPath.mkdirs();
		}
		List<FattachVO> attachList = new ArrayList<FattachVO>();
		for (MultipartFile multipartFile : files) {
			log.info("파일명: " + multipartFile.getOriginalFilename());
			log.info("파일크기: " + multipartFile.getSize());
			if (multipartFile.isEmpty()) {
				continue;
			}
			String uploadFileName = multipartFile.getOriginalFilename();
			UUID uuid = UUID.randomUUID();
			uploadFileName = uuid.toString() + "_" + uploadFileName;
			log.info("최종 업로드 파일명: " + uploadFileName);
			File saveFile = new File(uploadPath, uploadFileName);
			multipartFile.transferTo(saveFile);
			FattachVO FattachVO = new FattachVO();
			FattachVO.setBno(boardVO.getNum());
			FattachVO.setUuid(uuid.toString());
			FattachVO.setUploadpath(getFolder());
			FattachVO.setFilename(multipartFile.getOriginalFilename());
			if (isImageType(saveFile)) {
				File thumbnailFile = new File(uploadPath, "s_" + uploadFileName);
				try (FileOutputStream fos = new FileOutputStream(thumbnailFile)) {
					Thumbnailator.createThumbnail(multipartFile.getInputStream(), fos, 100, 100);
				}
				FattachVO.setFiletype("I");
			} else {
				FattachVO.setFiletype("O");
			}
			attachList.add(FattachVO);
		}
		communityService.reInsertBoardAndAttaches(reRef, reLev, reSeq, boardVO, attachList);
		return "redirect:/community/free";
	} // freeReWrite Post
}
