package com.exam.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.*;

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

import com.exam.domain.AttachVO;
import com.exam.domain.BoardVO;
import com.exam.domain.MemberVO;
import com.exam.domain.TreplyVO;
import com.exam.service.TradeAttachService;
import com.exam.service.TradeService;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;

@Controller
@RequestMapping("/trade/*")
@Log4j
public class TradeController {

	@Autowired
	private TradeService tradeService;
	
	@Autowired
	private TradeAttachService tradeAttachService;
	
	@GetMapping("/trade")
	public String trade(@RequestParam(defaultValue = "1") int pageNum,
			@RequestParam(defaultValue = "") String search,
			@RequestParam(defaultValue = "subject") String searchType, Model model) {
		int pageSize = 15;
		int startRow = (pageNum - 1) * pageSize + 1;
		int count = tradeService.getBoardCount(search);
		List<BoardVO> boardList = tradeService.getBoards(startRow, pageSize, search);
		int pageCount = count / pageSize + ((count % pageSize == 0) ? 0 : 1);
		int pageBlock = 10;
		int startPage = ((pageNum - 1) / pageBlock) * pageBlock + 1;
		int endPage = startPage + pageBlock - 1;
		if (endPage > pageCount) {
			endPage = pageCount;
		}
		switch (searchType) {
		case "subject":
			count = tradeService.getBoardCount(search);
			boardList = tradeService.getBoards(startRow, pageSize, search);
			break;
		case "username":
			count = tradeService.getUsernameOfBoardCount(search);
			boardList = tradeService.getUsernameOfBoards(startRow, pageSize, search);
			break;
		case "content":
			count = tradeService.getContentOfBoardCount(search);
			boardList = tradeService.getContentOfBoards(startRow, pageSize, search);
			break;
		}
		Map<String, Integer> pageInfoMap = new HashMap<String, Integer>();
		pageInfoMap.put("count", count);
		pageInfoMap.put("pageCount", pageCount);
		pageInfoMap.put("pageBlock", pageBlock);
		pageInfoMap.put("startPage", startPage);
		pageInfoMap.put("endPage", endPage);
		
		model.addAttribute("boardList", boardList);
		model.addAttribute("pageInfoMap", pageInfoMap);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("search", search);
		model.addAttribute("searchType", searchType);
		model.addAttribute("treplyDao", tradeService);
		return "trade/trade";
	} // trade Get
	
	
	@GetMapping("/tradeWrite")
	public String tradeWrite(HttpSession session) {
		MemberVO loginMember = (MemberVO) session.getAttribute("loginMember");
		if (loginMember == null) {
			return "redirect:/trade/trade";
		}
		return "trade/tradeWrite";
	} // tradeWrite Get
	
	
	@PostMapping("/tradeWrite")
	public String tradeWrite(MultipartFile[] files, BoardVO boardVO, HttpServletRequest request) throws Exception {
		int num = tradeService.nextBoardNum();
		boardVO.setNum(num);
		boardVO.setIp(request.getRemoteAddr());
		boardVO.setReadcount(0);
		boardVO.setReRef(num);
		boardVO.setReLev(0);
		boardVO.setReSeq(0);
		ServletContext application = request.getServletContext();
		String realPath = application.getRealPath("/resources/upload");
		log.info("realPath: " + realPath);
		File uploadPath = new File(realPath, getFolder());
		log.info("uploadPath: " + uploadPath);
		if (!uploadPath.exists()) {
			uploadPath.mkdirs();
		}
		List<AttachVO> attachList = new ArrayList<AttachVO>();
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
			AttachVO attachVO = new AttachVO();
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
		tradeService.insertBoardAndAttaches(boardVO, attachList);
		return "redirect:/trade/trade";
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
	} // tradeWrite Post


	@GetMapping("/tradeContent")
	public String tradeContent(@RequestParam(defaultValue = "1") int pageNum,
			@RequestParam(defaultValue = "1") int replyPageNum,
			int num, Model model) {
		BoardVO boardVO = tradeService.getBoard(num);
		tradeService.updateReadCount(num);
		List<AttachVO> attachList = tradeAttachService.getAttaches(num);
		int pageSize = 10;
		int startRow = (replyPageNum - 1) * pageSize + 1; 
		int count = tradeService.getReplyCount(num);
		List<TreplyVO> treplyList = tradeService.getReplys(startRow, pageSize);
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

		model.addAttribute("boardVO", boardVO);
		model.addAttribute("attachList", attachList);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("treplyList", treplyList);
		model.addAttribute("replyPageNum", replyPageNum);
		model.addAttribute("replyPageInfoMap", replyPageInfoMap);
		return "trade/tradeContent";
	} // tradeContent Get
	
	
	@GetMapping("/tradeUpdate")
	public String tradeUpdate(@RequestParam(defaultValue = "1") int pageNum, 
			int num, HttpSession session, Model model) {
		MemberVO loginMember = (MemberVO) session.getAttribute("loginMember");
		if (loginMember == null) {
			return "redirect:/trade/trade";
		}
		BoardVO boardVO = tradeService.getBoard(num);
		List<AttachVO> attachList = tradeAttachService.getAttaches(num);
		model.addAttribute("num", num);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("boardVO", boardVO);
		model.addAttribute("attachList", attachList);
		return "trade/tradeUpdate";
	} // tradeUpdate Get
	
	
	@PostMapping("/tradeUpdate")
	public ResponseEntity<String> tradeUpdate(MultipartFile[] newFiles, BoardVO boardVO, int pageNum, int num, 
			HttpServletRequest request, MultipartHttpServletRequest multi) throws Exception {
		ServletContext application = request.getServletContext();
		String realPath = application.getRealPath("/resources/upload");
		log.info("realPath: " + realPath);
		File uploadPath = new File(realPath, getFolder());
		log.info("uploadPath: " + uploadPath);
		if (!uploadPath.exists()) {
			uploadPath.mkdirs();
		}
		List<AttachVO> attachList = new ArrayList<AttachVO>();
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
			AttachVO attachVO = new AttachVO();
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
		tradeAttachService.insertAttaches(attachList);
		// 파일 삭제
		String[] delFiles = multi.getParameterValues("delFiles");
		if (delFiles != null) {
			for (String str : delFiles) {
				String[] strArr = str.split("_");
				String uuid = strArr[0];
				String delFilename = strArr[1];
				log.info("uuid : " + uuid);
				log.info("delFilename : " + delFilename);
				AttachVO attached = tradeAttachService.getAttachByUuid(uuid);
				realPath = application.getRealPath("/resources/upload/"+attached.getUploadpath()+"");
				log.info(realPath);
				File delFile = new File(realPath, uuid+"_"+delFilename);
				File thumbnailFile = new File(realPath, "s_"+uuid+"_"+delFilename);
				if (delFile.exists()) {
					delFile.delete();
					thumbnailFile.delete();
				}
				tradeAttachService.deleteAttachByUuid(uuid);
			} // for
		} // if
		tradeService.updateBoard(boardVO);
		String message = "게시글이 수정되었습니다.";
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "text/html; charset=UTF-8"); 
		StringBuilder sb = new StringBuilder();
		sb.append("<script>");
		sb.append("alert('"+message+"');");
		sb.append("location.href = '/trade/tradeContent?num="+boardVO.getNum()+"&pageNum="+pageNum+"';");
		sb.append("</script>");
		return new ResponseEntity<String>(sb.toString(), headers, HttpStatus.OK);
	} // tradeUpdate Post
	
	
	@RequestMapping(value="/tradeDelete" , method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<String> tradeDelete(@RequestParam(defaultValue = "1") int pageNum, int num, HttpServletRequest request) {
		List<AttachVO> attachList = tradeAttachService.getAttaches(num);
		for (AttachVO attachVO : attachList) {
			ServletContext application = request.getServletContext();
			String realPath = application.getRealPath("/resources/upload/"+attachVO.getUploadpath()+"");
			File file = new File(realPath, attachVO.getUuid()+"_"+attachVO.getFilename());
			File thumbnailFile = new File(realPath, "s_"+attachVO.getUuid()+"_"+attachVO.getFilename());
			if (file.exists()) {
				file.delete();
				thumbnailFile.delete();
			}
		}
		tradeService.deleteBoard(num);
		tradeAttachService.deleteAttachByBno(num);
		String message = "게시글이 삭제되었습니다.";
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "text/html; charset=UTF-8"); 
		StringBuilder sb = new StringBuilder();
		sb.append("<script>");
		sb.append("alert('"+message+"');");
		sb.append("location.href = '/trade/trade?pageNum="+pageNum+"';");
		sb.append("</script>");
		return new ResponseEntity<String>(sb.toString(), headers, HttpStatus.OK);
	} // tradeDelete Post
	
	
	@PostMapping("/tradeReply")
	public ResponseEntity<String> tradeReply(TreplyVO treplyVO, @RequestParam(defaultValue = "1") int pageNum, int num, HttpSession session, HttpServletRequest request, Model model) {
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
		int replyNum = tradeService.nextReplyNum();
		treplyVO.setNum(replyNum);
		treplyVO.setIp(request.getRemoteAddr());
		treplyVO.setReRef(replyNum);
		treplyVO.setReLev(0);
		treplyVO.setReSeq(0);
		treplyVO.setParentnum(num);
		tradeService.insertReply(treplyVO);
		model.addAttribute("num", num);
		model.addAttribute("pageNum", pageNum);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "text/html; charset=UTF-8"); 
		StringBuilder sb = new StringBuilder();
		sb.append("<script>");
		sb.append("location.href = '/trade/tradeContent?num="+num+"&pageNum="+pageNum+"';");
		sb.append("</script>");
		return new ResponseEntity<String>(sb.toString(), headers, HttpStatus.OK);
	} // tradeReply Post
	
	
	@RequestMapping(value = "/tradeReplyUpdateJson", method = {RequestMethod.POST}, produces = "application/json; charset=utf8")
	@ResponseBody
	public String tradeReplyUpdateJson(@RequestParam(defaultValue = "1") int pageNum, 
			int num, @RequestParam(defaultValue = "1") int replyNum, String uContent, HttpSession session, Model model) {
		MemberVO loginMember = (MemberVO) session.getAttribute("loginMember");
		if (loginMember == null) {
			return "redirect:/trade/tradeContent?num="+num+"&pageNum="+pageNum+"";
		}
		tradeService.updateReply(uContent, replyNum);
		TreplyVO treplyVO = tradeService.getReply(replyNum);
		String updateCompleted = treplyVO.getContent();
		return updateCompleted;
	} // tradeReplyUpdateJson Post
	
	
	@PostMapping("/tradeReplyDeleteJson")
	@ResponseBody
	public String tradeReplyDeleteJson(@RequestParam(defaultValue = "1") int pageNum, 
			int num, @RequestParam(defaultValue = "1") int replyNum, HttpSession session) {
		MemberVO loginMember = (MemberVO) session.getAttribute("loginMember");
		if (loginMember == null) {
			return "redirect:/trade/tradeContent?num="+num+"&pageNum="+pageNum+"";
		}
		tradeService.deleteReply(replyNum);
		return "trade/tradeContent?num="+num+"&pageNum="+pageNum+"";
	} // tradeReplyDeleteJson Post
	
	
	@GetMapping("/tradeReWrite")
	public String tradeReWrite(int reRef, int reLev, int reSeq, HttpSession session, Model model) {
		MemberVO loginMember = (MemberVO) session.getAttribute("loginMember");
		if (loginMember == null) {
			return "redirect:/trade/trade";
		}
		model.addAttribute("reRef", reRef);
		model.addAttribute("reLev", reLev);
		model.addAttribute("reSeq", reSeq);
		return "trade/tradeReWrite";
	} // tradeReWrite Get
	
	
	@PostMapping("/tradeReWrite")
	public String tradeReWrite(MultipartFile[] files, BoardVO boardVO, int reRef, int reLev, int reSeq, HttpServletRequest request) throws Exception {
		int num = tradeService.nextBoardNum();
		boardVO.setNum(num);
		boardVO.setIp(request.getRemoteAddr());
		boardVO.setReadcount(0);
		ServletContext application = request.getServletContext();
		String realPath = application.getRealPath("/resources/upload");
		log.info("realPath: " + realPath);
		File uploadPath = new File(realPath, getFolder());
		log.info("uploadPath: " + uploadPath);
		if (!uploadPath.exists()) {
			uploadPath.mkdirs();
		}
		List<AttachVO> attachList = new ArrayList<AttachVO>();
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
			AttachVO attachVO = new AttachVO();
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
		tradeService.reInsertBoardAndAttaches(reRef, reLev, reSeq, boardVO, attachList);
		return "redirect:/trade/trade";
	} // tradeReWrite Post
}
