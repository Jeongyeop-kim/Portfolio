package com.exam.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exam.domain.AttachVO;
import com.exam.domain.BoardVO;
import com.exam.domain.TreplyVO;
import com.exam.mapper.TradeAttachMapper;
import com.exam.mapper.TradeMapper;

@Service
@Transactional
public class TradeService {

	@Autowired
	private TradeMapper tradeMapper;
	
	@Autowired
	private TradeAttachMapper tradeAttachMapper;
	
	// trade/trade
	public int getBoardCount(String search) {
		return tradeMapper.getBoardCount(search);
	}
	public int getUsernameOfBoardCount(String search) {
		return tradeMapper.getUsernameOfBoardCount(search);
	}
	public int getContentOfBoardCount(String search) {
		return tradeMapper.getContentOfBoardCount(search);
	}
	public List<BoardVO> getBoards(int startRow, int pageSize, String search) {
		int endRow = startRow + pageSize - 1;
		List<BoardVO> list = tradeMapper.getBoards(startRow, endRow, search);
		return list;
	}
	public List<BoardVO> getUsernameOfBoards(int startRow, int pageSize, String search) {
		int endRow = startRow + pageSize - 1;
		List<BoardVO> list = tradeMapper.getBoards(startRow, endRow, search);
		return list;
	}
	public List<BoardVO> getContentOfBoards(int startRow, int pageSize, String search) {
		int endRow = startRow + pageSize - 1;
		List<BoardVO> list = tradeMapper.getBoards(startRow, endRow, search);
		return list;
	}
	public int writtenReplyCount(int num) {
		return tradeMapper.writtenReplyCount(num);
	}
	
	
	// trade/tradeWrite
	public int nextBoardNum() {
		return tradeMapper.nextBoardNum() + 1;
	}
	public void insertBoardAndAttaches(BoardVO boardVO, List<AttachVO> attachList) {
		tradeMapper.insertBoard(boardVO);
		if (attachList.size() > 0) {
			for (AttachVO attachVO : attachList) {
				tradeAttachMapper.insertAttach(attachVO);
			}
		}
	}
	
	
	// trade/tradeContent
	public BoardVO getBoard(int num) {
		BoardVO boardVO = tradeMapper.getBoard(num);
		return boardVO;
	}
	public void updateReadCount(int num) {
		tradeMapper.updateReadCount(num);
	}
	public int getReplyCount(int num) {
		return tradeMapper.getReplyCount(num);
	}
	public List<TreplyVO> getReplys(int startRow, int pageSize) {
		int endRow = startRow + pageSize - 1;
		return tradeMapper.getReplys(startRow, endRow);
	}
	
	
	// trade/tradeUpdate
	public void updateBoard(BoardVO boardVO) {
		tradeMapper.updateBoard(boardVO);
	}
	
	
	// trade/tradeDelete
	public void deleteBoard(int num) {
		tradeMapper.deleteBoard(num);
	}
	
	
	// trade/tradeReply
	public int nextReplyNum() {
		return tradeMapper.nextReplyNum() + 1;
	}
	public void insertReply(TreplyVO treplyVO) {
		tradeMapper.insertReply(treplyVO);
	}
	
	
	// trade/tradeReplyUpdateJson
	public void updateReply(String content, int num) {
		tradeMapper.updateReply(content, num);
	}
	public TreplyVO getReply(int num) {
		TreplyVO treplyVO = tradeMapper.getReply(num);
		return treplyVO;
	}
	
	
	// trade/tradeReplyDeleteJson
	public void deleteReply(int num) {
		tradeMapper.deleteReply(num);
	}
	
	
	// trade/tradeReWrite
	public void reInsertBoardAndAttaches(int reRef, int reLev, int reSeq, BoardVO boardVO, List<AttachVO> attachList) {
		tradeMapper.updateReInsertGroupSequence(reRef, reSeq);
		boardVO.setReLev(reLev + 1);
		boardVO.setReSeq(reSeq + 1);
		tradeMapper.insertBoard(boardVO);
		if (attachList.size() > 0) {
			for (AttachVO attachVO : attachList) {
				tradeAttachMapper.insertAttach(attachVO);
			}
		}
	}
}
