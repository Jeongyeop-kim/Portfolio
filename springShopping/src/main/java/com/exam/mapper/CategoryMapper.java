package com.exam.mapper;

import java.util.*;

import org.apache.ibatis.annotations.Param;

import com.exam.domain.TotalVO;

public interface CategoryMapper {

	// 관심항목
	public int getInterestBoardCount(String gSearch);
	public List<TotalVO> getInterestBoards(int num);
	
	
	// 가전
	public int getAppBoardCount(String gSearch);
	public List<TotalVO> getAppBoards(@Param("startRow") int startRow, @Param("endRow") int endRow, @Param("gSearch") String gSearch, @Param("subc") String subc);
	public List<TotalVO> getAppReadCountBoards(@Param("startRow") int startRow, @Param("endRow") int endRow, @Param("gSearch") String gSearch, @Param("subc") String subc);
	public List<TotalVO> getAppLowPriceBoards(@Param("startRow") int startRow, @Param("endRow") int endRow, @Param("gSearch") String gSearch, @Param("subc") String subc);
	public List<TotalVO> getAppHighPriceBoards(@Param("startRow") int startRow, @Param("endRow") int endRow, @Param("gSearch") String gSearch, @Param("subc") String subc);
	public List<TotalVO> getAppDateBoards(@Param("startRow") int startRow, @Param("endRow") int endRow, @Param("gSearch") String gSearch, @Param("subc") String subc);
	
	
	// 컴퓨터
	public int getComBoardCount(String gSearch);
	public List<TotalVO> getComBoards(@Param("startRow") int startRow, @Param("endRow") int endRow, @Param("gSearch") String gSearch, @Param("subc") String subc);
	public List<TotalVO> getComReadCountBoards(@Param("startRow") int startRow, @Param("endRow") int endRow, @Param("gSearch") String gSearch, @Param("subc") String subc);
	public List<TotalVO> getComLowPriceBoards(@Param("startRow") int startRow, @Param("endRow") int endRow, @Param("gSearch") String gSearch, @Param("subc") String subc);
	public List<TotalVO> getComHighPriceBoards(@Param("startRow") int startRow, @Param("endRow") int endRow, @Param("gSearch") String gSearch, @Param("subc") String subc);
	public List<TotalVO> getComDateBoards(@Param("startRow") int startRow, @Param("endRow") int endRow, @Param("gSearch") String gSearch, @Param("subc") String subc);
	
	
	// 패션
	public int getFasBoardCount(String gSearch);
	public List<TotalVO> getFasBoards(@Param("startRow") int startRow, @Param("endRow") int endRow, @Param("gSearch") String gSearch, @Param("subc") String subc);
	public List<TotalVO> getFasReadCountBoards(@Param("startRow") int startRow, @Param("endRow") int endRow, @Param("gSearch") String gSearch, @Param("subc") String subc);
	public List<TotalVO> getFasLowPriceBoards(@Param("startRow") int startRow, @Param("endRow") int endRow, @Param("gSearch") String gSearch, @Param("subc") String subc);
	public List<TotalVO> getFasHighPriceBoards(@Param("startRow") int startRow, @Param("endRow") int endRow, @Param("gSearch") String gSearch, @Param("subc") String subc);
	public List<TotalVO> getFasDateBoards(@Param("startRow") int startRow, @Param("endRow") int endRow, @Param("gSearch") String gSearch, @Param("subc") String subc);
	
	
	// 잡화
	public int getGenBoardCount(String gSearch);
	public List<TotalVO> getGenBoards(@Param("startRow") int startRow, @Param("endRow") int endRow, @Param("gSearch") String gSearch, @Param("subc") String subc);
	public List<TotalVO> getGenReadCountBoards(@Param("startRow") int startRow, @Param("endRow") int endRow, @Param("gSearch") String gSearch, @Param("subc") String subc);
	public List<TotalVO> getGenLowPriceBoards(@Param("startRow") int startRow, @Param("endRow") int endRow, @Param("gSearch") String gSearch, @Param("subc") String subc);
	public List<TotalVO> getGenHighPriceBoards(@Param("startRow") int startRow, @Param("endRow") int endRow, @Param("gSearch") String gSearch, @Param("subc") String subc);
	public List<TotalVO> getGenDateBoards(@Param("startRow") int startRow, @Param("endRow") int endRow, @Param("gSearch") String gSearch, @Param("subc") String subc);
	
	
	// 스포츠
	public int getSpoBoardCount(String gSearch);
	public List<TotalVO> getSpoBoards(@Param("startRow") int startRow, @Param("endRow") int endRow, @Param("gSearch") String gSearch, @Param("subc") String subc);
	public List<TotalVO> getSpoReadCountBoards(@Param("startRow") int startRow, @Param("endRow") int endRow, @Param("gSearch") String gSearch, @Param("subc") String subc);
	public List<TotalVO> getSpoLowPriceBoards(@Param("startRow") int startRow, @Param("endRow") int endRow, @Param("gSearch") String gSearch, @Param("subc") String subc);
	public List<TotalVO> getSpoHighPriceBoards(@Param("startRow") int startRow, @Param("endRow") int endRow, @Param("gSearch") String gSearch, @Param("subc") String subc);
	public List<TotalVO> getSpoDateBoards(@Param("startRow") int startRow, @Param("endRow") int endRow, @Param("gSearch") String gSearch, @Param("subc") String subc);
	
	
	// 가구
	public int getFurBoardCount(String gSearch);
	public List<TotalVO> getFurBoards(@Param("startRow") int startRow, @Param("endRow") int endRow, @Param("gSearch") String gSearch, @Param("subc") String subc);
	public List<TotalVO> getFurReadCountBoards(@Param("startRow") int startRow, @Param("endRow") int endRow, @Param("gSearch") String gSearch, @Param("subc") String subc);
	public List<TotalVO> getFurLowPriceBoards(@Param("startRow") int startRow, @Param("endRow") int endRow, @Param("gSearch") String gSearch, @Param("subc") String subc);
	public List<TotalVO> getFurHighPriceBoards(@Param("startRow") int startRow, @Param("endRow") int endRow, @Param("gSearch") String gSearch, @Param("subc") String subc);
	public List<TotalVO> getFurDateBoards(@Param("startRow") int startRow, @Param("endRow") int endRow, @Param("gSearch") String gSearch, @Param("subc") String subc);
	
	
	// 아동
	public int getKidBoardCount(String gSearch);
	public List<TotalVO> getKidBoards(@Param("startRow") int startRow, @Param("endRow") int endRow, @Param("gSearch") String gSearch, @Param("subc") String subc);
	public List<TotalVO> getKidReadCountBoards(@Param("startRow") int startRow, @Param("endRow") int endRow, @Param("gSearch") String gSearch, @Param("subc") String subc);
	public List<TotalVO> getKidLowPriceBoards(@Param("startRow") int startRow, @Param("endRow") int endRow, @Param("gSearch") String gSearch, @Param("subc") String subc);
	public List<TotalVO> getKidHighPriceBoards(@Param("startRow") int startRow, @Param("endRow") int endRow, @Param("gSearch") String gSearch, @Param("subc") String subc);
	public List<TotalVO> getKidDateBoards(@Param("startRow") int startRow, @Param("endRow") int endRow, @Param("gSearch") String gSearch, @Param("subc") String subc);
	
	
	// 도서
	public int getBkBoardCount(String gSearch);
	public List<TotalVO> getBkBoards(@Param("startRow") int startRow, @Param("endRow") int endRow, @Param("gSearch") String gSearch, @Param("subc") String subc);
	public List<TotalVO> getBkReadCountBoards(@Param("startRow") int startRow, @Param("endRow") int endRow, @Param("gSearch") String gSearch, @Param("subc") String subc);
	public List<TotalVO> getBkLowPriceBoards(@Param("startRow") int startRow, @Param("endRow") int endRow, @Param("gSearch") String gSearch, @Param("subc") String subc);
	public List<TotalVO> getBkHighPriceBoards(@Param("startRow") int startRow, @Param("endRow") int endRow, @Param("gSearch") String gSearch, @Param("subc") String subc);
	public List<TotalVO> getBkDateBoards(@Param("startRow") int startRow, @Param("endRow") int endRow, @Param("gSearch") String gSearch, @Param("subc") String subc);
	
	
	// 검색 결과
	public int getResultBoardCount(String gSearch);
	public List<TotalVO> getResultBoards(@Param("startRow") int startRow, @Param("endRow") int endRow, @Param("gSearch") String gSearch, @Param("subc") String subc);
	public List<TotalVO> getResultReadCountBoards(@Param("startRow") int startRow, @Param("endRow") int endRow, @Param("gSearch") String gSearch, @Param("subc") String subc);
	public List<TotalVO> getResultLowPriceBoards(@Param("startRow") int startRow, @Param("endRow") int endRow, @Param("gSearch") String gSearch, @Param("subc") String subc);
	public List<TotalVO> getResultHighPriceBoards(@Param("startRow") int startRow, @Param("endRow") int endRow, @Param("gSearch") String gSearch, @Param("subc") String subc);
	public List<TotalVO> getResultDateBoards(@Param("startRow") int startRow, @Param("endRow") int endRow, @Param("gSearch") String gSearch, @Param("subc") String subc);
	
	
	// 상품 정보
	public void updateReadCount(@Param("num") int num, @Param("majorc") String majorc, @Param("subc") String subc);
	public TotalVO getInfoBoard(@Param("num") int num, @Param("majorc") String majorc, @Param("subc") String subc);
	public TotalVO getInfoMinimumPrice(@Param("name") String name, @Param("majorc") String majorc, @Param("subc") String subc);
	public List<TotalVO> getInfoLowPriceBoards(@Param("name") String name, @Param("majorc") String majorc, @Param("subc") String subc);
	
	
	// 베스트
	public int getBestPageCount();
	public List<TotalVO> getBestPageBoards();
}
