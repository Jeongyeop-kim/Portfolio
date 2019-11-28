package com.exam.mapper;

import java.util.*;

import org.apache.ibatis.annotations.Param;

import com.exam.domain.BoardVO;
import com.exam.domain.TreplyVO;

public interface TradeMapper {

	// trade/trade
	public int getBoardCount(@Param("search") String search);
	public int getUsernameOfBoardCount(@Param("search") String search);
	public int getContentOfBoardCount(@Param("search") String search);
	public List<BoardVO> getBoards(@Param("startRow") int startRow, @Param("endRow") int endRow, @Param("search") String search);
	public List<BoardVO> getUsernameOfBoards(@Param("startRow") int startRow, @Param("endRow") int endRow, @Param("search") String search);
	public List<BoardVO> getContentOfBoards(@Param("startRow") int startRow, @Param("endRow") int endRow, @Param("search") String search);
	public int writtenReplyCount(@Param("num") int num);
	
	
	// trade/tradeWrite
	public int nextBoardNum();
	public void insertBoard(BoardVO boardVO);
	
	
	// trade/tradeContent
	public BoardVO getBoard(@Param("num") int num);
	public void updateReadCount(@Param("num") int num);
	public int getReplyCount(@Param("num") int num);
	public List<TreplyVO> getReplys(@Param("startRow") int startRow, @Param("endRow") int endRow);
	
	
	// trade/tradeUpdate
	public void updateBoard(BoardVO boardVO);
	
	
	// trade/tradeDelete
	public void deleteBoard(@Param("num") int num);
	
	
	// trade/tradeReply
	public int nextReplyNum();
	public void insertReply(TreplyVO treplyVO);
	
	
	// trade/tradeReplyUpdateJson
	public void updateReply(@Param("content") String content, @Param("num") int num);
	public TreplyVO getReply(@Param("num") int num);
	
	
	// trade/tradeReplyDeleteJson
	public void deleteReply(@Param("num") int num);
	
	
	// trade/tradeReWrite
	public int updateReInsertGroupSequence(@Param("reRef") int reRef, @Param("reSeq") int reSeq);
}
