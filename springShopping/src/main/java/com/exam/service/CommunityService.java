package com.exam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exam.domain.FattachVO;
import com.exam.domain.FboardVO;
import com.exam.domain.FreplyVO;
import com.exam.mapper.CommunityAttachMapper;
import com.exam.mapper.CommunityMapper;

@Service
@Transactional
public class CommunityService {

	@Autowired
	private CommunityMapper communityMapper;
	
	@Autowired
	private CommunityAttachMapper communityAttachMapper;
	
	// community/free
	public int getBoardCount(String search) {
		return communityMapper.getBoardCount(search);
	}
	public int getUsernameOfBoardCount(String search) {
		return communityMapper.getUsernameOfBoardCount(search);
	}
	public int getContentOfBoardCount(String search) {
		return communityMapper.getContentOfBoardCount(search);
	}
	public List<FboardVO> getBoards(int startRow, int pageSize, String search) {
		int endRow = startRow + pageSize - 1;
		List<FboardVO> list = communityMapper.getBoards(startRow, endRow, search);
		return list;
	}
	public List<FboardVO> getUsernameOfBoards(int startRow, int pageSize, String search) {
		int endRow = startRow + pageSize - 1;
		List<FboardVO> list = communityMapper.getBoards(startRow, endRow, search);
		return list;
	}
	public List<FboardVO> getContentOfBoards(int startRow, int pageSize, String search) {
		int endRow = startRow + pageSize - 1;
		List<FboardVO> list = communityMapper.getBoards(startRow, endRow, search);
		return list;
	}
	public int writtenReplyCount(int num) {
		return communityMapper.writtenReplyCount(num);
	}
	
	
	// community/freeWrite
	public int nextBoardNum() {
		return communityMapper.nextBoardNum() + 1;
	}
	public void insertBoardAndAttaches(FboardVO boardVO, List<FattachVO> attachList) {
		communityMapper.insertBoard(boardVO);
		if (attachList.size() > 0) {
			for (FattachVO attachVO : attachList) {
				communityAttachMapper.insertAttach(attachVO);
			}
		}
	}
	
	
	// community/freeContent
	public FboardVO getBoard(int num) {
		FboardVO boardVO = communityMapper.getBoard(num);
		return boardVO;
	}
	public void updateReadCount(int num) {
		communityMapper.updateReadCount(num);
	}
	public int getReplyCount(int num) {
		return communityMapper.getReplyCount(num);
	}
	public List<FreplyVO> getReplys(int startRow, int pageSize) {
		int endRow = startRow + pageSize - 1;
		return communityMapper.getReplys(startRow, endRow);
	}
	public boolean IsUserRecommendedBoard(int num, String username) {
		String gooduser = "/"+username+"/";
		boolean result = false;
		int count = communityMapper.IsUserRecommendedBoard(num, gooduser);
		if (count == 1) {
			result = true;
		} else {
			result = false;
		}
		return result;
	}
	public void updateGoodCount(String username, int num) {
		String gooduser = username+"/";
		communityMapper.updateGoodCount(gooduser, num);
	}
	
	
	// community/freeUpdate
	public void updateBoard(FboardVO boardVO) {
		communityMapper.updateBoard(boardVO);
	}
	
	
	// community/freeDelete
	public void deleteBoard(int num) {
		communityMapper.deleteBoard(num);
	}
	
	
	// community/freeReply
	public int nextReplyNum() {
		return communityMapper.nextReplyNum() + 1;
	}
	public void insertReply(FreplyVO freplyVO) {
		communityMapper.insertReply(freplyVO);
	}
	
	
	// community/freeReplyUpdateJson
	public void updateReply(String content, int num) {
		communityMapper.updateReply(content, num);
	}
	public FreplyVO getReply(int num) {
		FreplyVO freplyVO = communityMapper.getReply(num);
		return freplyVO;
	}
	
	
	// community/freeReplyDeleteJson
	public void deleteReply(int num) {
		communityMapper.deleteReply(num);
	}
	
	
	// community/freeReWrite
	public void reInsertBoardAndAttaches(int reRef, int reLev, int reSeq, FboardVO boardVO, List<FattachVO> attachList) {
		communityMapper.updateReInsertGroupSequence(reRef, reSeq);
		boardVO.setReLev(reLev + 1);
		boardVO.setReSeq(reSeq + 1);
		communityMapper.insertBoard(boardVO);
		if (attachList.size() > 0) {
			for (FattachVO attachVO : attachList) {
				communityAttachMapper.insertAttach(attachVO);
			}
		}
	}
	
	
	// community/best
	public int getCountOfBestSubject(String search) {
		return communityMapper.getCountOfBestSubject(search);
	}
	public int getCountOfBestUsername(String search) {
		return communityMapper.getCountOfBestUsername(search);
	}
	public int getCountOfBestContent(String search) {
		return communityMapper.getCountOfBestContent(search);
	}
	public List<FboardVO> getBestBoardsBySubject(int startRow, int pageSize, String search) {
		int endRow = startRow + pageSize - 1;
		List<FboardVO> list = communityMapper.getBestBoardsBySubject(startRow, endRow, search);
		return list;
	}
	public List<FboardVO> getBestBoardsByUsername(int startRow, int pageSize, String search) {
		int endRow = startRow + pageSize - 1;
		List<FboardVO> list = communityMapper.getBestBoardsByUsername(startRow, endRow, search);
		return list;
	}
	public List<FboardVO> getBestBoardsByContent(int startRow, int pageSize, String search) {
		int endRow = startRow + pageSize - 1;
		List<FboardVO> list = communityMapper.getBestBoardsByContent(startRow, endRow, search);
		return list;
	}
}
