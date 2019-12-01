package com.exam.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.exam.domain.FboardVO;
import com.exam.domain.FreplyVO;

public interface CommunityMapper {

	// community/free
	public int getBoardCount(@Param("search") String search);
	public int getUsernameOfBoardCount(@Param("search") String search);
	public int getContentOfBoardCount(@Param("search") String search);
	public List<FboardVO> getBoards(@Param("startRow") int startRow, @Param("endRow") int endRow, @Param("search") String search);
	public List<FboardVO> getUsernameOfBoards(@Param("startRow") int startRow, @Param("endRow") int endRow, @Param("search") String search);
	public List<FboardVO> getContentOfBoards(@Param("startRow") int startRow, @Param("endRow") int endRow, @Param("search") String search);
	public int writtenReplyCount(@Param("num") int num);
	
	
	// community/freeWrite
	public int nextBoardNum();
	public void insertBoard(FboardVO boardVO);
	
	
	// community/freeContent
	public FboardVO getBoard(@Param("num") int num);
	public void updateReadCount(@Param("num") int num);
	public int getReplyCount(@Param("num") int num);
	public List<FreplyVO> getReplys(@Param("startRow") int startRow, @Param("endRow") int endRow);
	public int IsUserRecommendedBoard(@Param("num") int num, @Param("gooduser") String gooduser);
	public void updateGoodCount(@Param("gooduser") String gooduser, @Param("num") int num);
	
	
	// community/freeUpdate
	public void updateBoard(FboardVO boardVO);
	
	
	// community/freeDelete
	public void deleteBoard(@Param("num") int num);
	
	
	// community/freeReply
	public int nextReplyNum();
	public void insertReply(FreplyVO freplyVO);
	
	
	// community/freeReplyUpdateJson
	public void updateReply(@Param("content") String content, @Param("num") int num);
	public FreplyVO getReply(@Param("num") int num);
	
	
	// community/freeReplyDeleteJson
	public void deleteReply(@Param("num") int num);
	
	
	// community/freeReWrite
	public int updateReInsertGroupSequence(@Param("reRef") int reRef, @Param("reSeq") int reSeq);
	
	
	// community/best
	public int getCountOfBestSubject(@Param("search") String search);
	public int getCountOfBestUsername(@Param("search") String search);
	public int getCountOfBestContent(@Param("search") String search);
	public List<FboardVO> getBestBoardsBySubject(@Param("startRow") int startRow, @Param("endRow") int endRow, @Param("search") String search);
	public List<FboardVO> getBestBoardsByUsername(@Param("startRow") int startRow, @Param("endRow") int endRow, @Param("search") String search);
	public List<FboardVO> getBestBoardsByContent(@Param("startRow") int startRow, @Param("endRow") int endRow, @Param("search") String search);
}
