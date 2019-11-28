package com.exam.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exam.domain.TotalVO;
import com.exam.mapper.CategoryMapper;

@Service
@Transactional
public class CategoryService {
	
	@Autowired
	private CategoryMapper categoryMapper;
	
	// 관심항목
	public int getInterestBoardCount(String gSearch) {
		return categoryMapper.getInterestBoardCount(gSearch);
	}
	public List<TotalVO> getInterestBoards(int num) {
		return categoryMapper.getInterestBoards(num);
	}
	
	
	// 가전
	public int getAppBoardCount(String gSearch) {
		return categoryMapper.getAppBoardCount(gSearch);
	}
	public List<TotalVO> getAppBoards(int startRow, int pageSize, String gSearch, String subc) {
		int endRow = startRow + pageSize - 1;
		List<TotalVO> list = categoryMapper.getAppBoards(startRow, endRow, gSearch, subc);
		return list;
	}
	public List<TotalVO> getAppReadCountBoards(int startRow, int pageSize, String gSearch, String subc) {
		int endRow = startRow + pageSize - 1;
		List<TotalVO> list = categoryMapper.getAppReadCountBoards(startRow, endRow, gSearch, subc);
		return list;
	}
	public List<TotalVO> getAppLowPriceBoards(int startRow, int pageSize, String gSearch, String subc) {
		int endRow = startRow + pageSize - 1;
		List<TotalVO> list = categoryMapper.getAppLowPriceBoards(startRow, endRow, gSearch, subc);
		return list;
	}
	public List<TotalVO> getAppHighPriceBoards(int startRow, int pageSize, String gSearch, String subc) {
		int endRow = startRow + pageSize - 1;
		List<TotalVO> list = categoryMapper.getAppHighPriceBoards(startRow, endRow, gSearch, subc);
		return list;
	}
	public List<TotalVO> getAppDateBoards(int startRow, int pageSize, String gSearch, String subc) {
		int endRow = startRow + pageSize - 1;
		List<TotalVO> list = categoryMapper.getAppDateBoards(startRow, endRow, gSearch, subc);
		return list;
	}
	
	
	// 컴퓨터
	public int getComBoardCount(String gSearch) {
		return categoryMapper.getComBoardCount(gSearch);
	}
	public List<TotalVO> getComBoards(int startRow, int pageSize, String gSearch, String subc) {
		int endRow = startRow + pageSize - 1;
		List<TotalVO> list = categoryMapper.getComBoards(startRow, endRow, gSearch, subc);
		return list;
	}
	public List<TotalVO> getComReadCountBoards(int startRow, int pageSize, String gSearch, String subc) {
		int endRow = startRow + pageSize - 1;
		List<TotalVO> list = categoryMapper.getComReadCountBoards(startRow, endRow, gSearch, subc);
		return list;
	}
	public List<TotalVO> getComLowPriceBoards(int startRow, int pageSize, String gSearch, String subc) {
		int endRow = startRow + pageSize - 1;
		List<TotalVO> list = categoryMapper.getComLowPriceBoards(startRow, endRow, gSearch, subc);
		return list;
	}
	public List<TotalVO> getComHighPriceBoards(int startRow, int pageSize, String gSearch, String subc) {
		int endRow = startRow + pageSize - 1;
		List<TotalVO> list = categoryMapper.getComHighPriceBoards(startRow, endRow, gSearch, subc);
		return list;
	}
	public List<TotalVO> getComDateBoards(int startRow, int pageSize, String gSearch, String subc) {
		int endRow = startRow + pageSize - 1;
		List<TotalVO> list = categoryMapper.getComDateBoards(startRow, endRow, gSearch, subc);
		return list;
	}


	// 패션
	public int getFasBoardCount(String gSearch) {
		return categoryMapper.getFasBoardCount(gSearch);
	}
	public List<TotalVO> getFasBoards(int startRow, int pageSize, String gSearch, String subc) {
		int endRow = startRow + pageSize - 1;
		List<TotalVO> list = categoryMapper.getFasBoards(startRow, endRow, gSearch, subc);
		return list;
	}
	public List<TotalVO> getFasReadCountBoards(int startRow, int pageSize, String gSearch, String subc) {
		int endRow = startRow + pageSize - 1;
		List<TotalVO> list = categoryMapper.getFasReadCountBoards(startRow, endRow, gSearch, subc);
		return list;
	}
	public List<TotalVO> getFasLowPriceBoards(int startRow, int pageSize, String gSearch, String subc) {
		int endRow = startRow + pageSize - 1;
		List<TotalVO> list = categoryMapper.getFasLowPriceBoards(startRow, endRow, gSearch, subc);
		return list;
	}
	public List<TotalVO> getFasHighPriceBoards(int startRow, int pageSize, String gSearch, String subc) {
		int endRow = startRow + pageSize - 1;
		List<TotalVO> list = categoryMapper.getFasHighPriceBoards(startRow, endRow, gSearch, subc);
		return list;
	}
	public List<TotalVO> getFasDateBoards(int startRow, int pageSize, String gSearch, String subc) {
		int endRow = startRow + pageSize - 1;
		List<TotalVO> list = categoryMapper.getFasDateBoards(startRow, endRow, gSearch, subc);
		return list;
	}
	
	
	// 잡화
	public int getGenBoardCount(String gSearch) {
		return categoryMapper.getGenBoardCount(gSearch);
	}
	public List<TotalVO> getGenBoards(int startRow, int pageSize, String gSearch, String subc) {
		int endRow = startRow + pageSize - 1;
		List<TotalVO> list = categoryMapper.getGenBoards(startRow, endRow, gSearch, subc);
		return list;
	}
	public List<TotalVO> getGenReadCountBoards(int startRow, int pageSize, String gSearch, String subc) {
		int endRow = startRow + pageSize - 1;
		List<TotalVO> list = categoryMapper.getGenReadCountBoards(startRow, endRow, gSearch, subc);
		return list;
	}
	public List<TotalVO> getGenLowPriceBoards(int startRow, int pageSize, String gSearch, String subc) {
		int endRow = startRow + pageSize - 1;
		List<TotalVO> list = categoryMapper.getGenLowPriceBoards(startRow, endRow, gSearch, subc);
		return list;
	}
	public List<TotalVO> getGenHighPriceBoards(int startRow, int pageSize, String gSearch, String subc) {
		int endRow = startRow + pageSize - 1;
		List<TotalVO> list = categoryMapper.getGenHighPriceBoards(startRow, endRow, gSearch, subc);
		return list;
	}
	public List<TotalVO> getGenDateBoards(int startRow, int pageSize, String gSearch, String subc) {
		int endRow = startRow + pageSize - 1;
		List<TotalVO> list = categoryMapper.getGenDateBoards(startRow, endRow, gSearch, subc);
		return list;
	}
	
	
	// 스포츠
	public int getSpoBoardCount(String gSearch) {
		return categoryMapper.getSpoBoardCount(gSearch);
	}
	public List<TotalVO> getSpoBoards(int startRow, int pageSize, String gSearch, String subc) {
		int endRow = startRow + pageSize - 1;
		List<TotalVO> list = categoryMapper.getSpoBoards(startRow, endRow, gSearch, subc);
		return list;
	}
	public List<TotalVO> getSpoReadCountBoards(int startRow, int pageSize, String gSearch, String subc) {
		int endRow = startRow + pageSize - 1;
		List<TotalVO> list = categoryMapper.getSpoReadCountBoards(startRow, endRow, gSearch, subc);
		return list;
	}
	public List<TotalVO> getSpoLowPriceBoards(int startRow, int pageSize, String gSearch, String subc) {
		int endRow = startRow + pageSize - 1;
		List<TotalVO> list = categoryMapper.getSpoLowPriceBoards(startRow, endRow, gSearch, subc);
		return list;
	}
	public List<TotalVO> getSpoHighPriceBoards(int startRow, int pageSize, String gSearch, String subc) {
		int endRow = startRow + pageSize - 1;
		List<TotalVO> list = categoryMapper.getSpoHighPriceBoards(startRow, endRow, gSearch, subc);
		return list;
	}
	public List<TotalVO> getSpoDateBoards(int startRow, int pageSize, String gSearch, String subc) {
		int endRow = startRow + pageSize - 1;
		List<TotalVO> list = categoryMapper.getSpoDateBoards(startRow, endRow, gSearch, subc);
		return list;
	}
	
	
	// 가구
	public int getFurBoardCount(String gSearch) {
		return categoryMapper.getFurBoardCount(gSearch);
	}
	public List<TotalVO> getFurBoards(int startRow, int pageSize, String gSearch, String subc) {
		int endRow = startRow + pageSize - 1;
		List<TotalVO> list = categoryMapper.getFurBoards(startRow, endRow, gSearch, subc);
		return list;
	}
	public List<TotalVO> getFurReadCountBoards(int startRow, int pageSize, String gSearch, String subc) {
		int endRow = startRow + pageSize - 1;
		List<TotalVO> list = categoryMapper.getFurReadCountBoards(startRow, endRow, gSearch, subc);
		return list;
	}
	public List<TotalVO> getFurLowPriceBoards(int startRow, int pageSize, String gSearch, String subc) {
		int endRow = startRow + pageSize - 1;
		List<TotalVO> list = categoryMapper.getFurLowPriceBoards(startRow, endRow, gSearch, subc);
		return list;
	}
	public List<TotalVO> getFurHighPriceBoards(int startRow, int pageSize, String gSearch, String subc) {
		int endRow = startRow + pageSize - 1;
		List<TotalVO> list = categoryMapper.getFurHighPriceBoards(startRow, endRow, gSearch, subc);
		return list;
	}
	public List<TotalVO> getFurDateBoards(int startRow, int pageSize, String gSearch, String subc) {
		int endRow = startRow + pageSize - 1;
		List<TotalVO> list = categoryMapper.getFurDateBoards(startRow, endRow, gSearch, subc);
		return list;
	}
	
	
	// 아동
	public int getKidBoardCount(String gSearch) {
		return categoryMapper.getKidBoardCount(gSearch);
	}
	public List<TotalVO> getKidBoards(int startRow, int pageSize, String gSearch, String subc) {
		int endRow = startRow + pageSize - 1;
		List<TotalVO> list = categoryMapper.getKidBoards(startRow, endRow, gSearch, subc);
		return list;
	}
	public List<TotalVO> getKidReadCountBoards(int startRow, int pageSize, String gSearch, String subc) {
		int endRow = startRow + pageSize - 1;
		List<TotalVO> list = categoryMapper.getKidReadCountBoards(startRow, endRow, gSearch, subc);
		return list;
	}
	public List<TotalVO> getKidLowPriceBoards(int startRow, int pageSize, String gSearch, String subc) {
		int endRow = startRow + pageSize - 1;
		List<TotalVO> list = categoryMapper.getFurLowPriceBoards(startRow, endRow, gSearch, subc);
		return list;
	}
	public List<TotalVO> getKidHighPriceBoards(int startRow, int pageSize, String gSearch, String subc) {
		int endRow = startRow + pageSize - 1;
		List<TotalVO> list = categoryMapper.getKidHighPriceBoards(startRow, endRow, gSearch, subc);
		return list;
	}
	public List<TotalVO> getKidDateBoards(int startRow, int pageSize, String gSearch, String subc) {
		int endRow = startRow + pageSize - 1;
		List<TotalVO> list = categoryMapper.getKidDateBoards(startRow, endRow, gSearch, subc);
		return list;
	}
	
	
	// 도서
	public int getBkBoardCount(String gSearch) {
		return categoryMapper.getBkBoardCount(gSearch);
	}
	public List<TotalVO> getBkBoards(int startRow, int pageSize, String gSearch, String subc) {
		int endRow = startRow + pageSize - 1;
		List<TotalVO> list = categoryMapper.getBkBoards(startRow, endRow, gSearch, subc);
		return list;
	}
	public List<TotalVO> getBkReadCountBoards(int startRow, int pageSize, String gSearch, String subc) {
		int endRow = startRow + pageSize - 1;
		List<TotalVO> list = categoryMapper.getBkReadCountBoards(startRow, endRow, gSearch, subc);
		return list;
	}
	public List<TotalVO> getBkLowPriceBoards(int startRow, int pageSize, String gSearch, String subc) {
		int endRow = startRow + pageSize - 1;
		List<TotalVO> list = categoryMapper.getBkLowPriceBoards(startRow, endRow, gSearch, subc);
		return list;
	}
	public List<TotalVO> getBkHighPriceBoards(int startRow, int pageSize, String gSearch, String subc) {
		int endRow = startRow + pageSize - 1;
		List<TotalVO> list = categoryMapper.getBkHighPriceBoards(startRow, endRow, gSearch, subc);
		return list;
	}
	public List<TotalVO> getBkDateBoards(int startRow, int pageSize, String gSearch, String subc) {
		int endRow = startRow + pageSize - 1;
		List<TotalVO> list = categoryMapper.getBkDateBoards(startRow, endRow, gSearch, subc);
		return list;
	}
	
	
	// 검색 결과
	public int getResultBoardCount(String gSearch) {
		return categoryMapper.getResultBoardCount(gSearch);
	}
	public List<TotalVO> getResultBoards(int startRow, int pageSize, String gSearch, String subc) {
		int endRow = startRow + pageSize - 1;
		List<TotalVO> list = categoryMapper.getResultBoards(startRow, endRow, gSearch, subc);
		return list;
	}
	public List<TotalVO> getResultReadCountBoards(int startRow, int pageSize, String gSearch, String subc) {
		int endRow = startRow + pageSize - 1;
		List<TotalVO> list = categoryMapper.getResultReadCountBoards(startRow, endRow, gSearch, subc);
		return list;
	}
	public List<TotalVO> getResultLowPriceBoards(int startRow, int pageSize, String gSearch, String subc) {
		int endRow = startRow + pageSize - 1;
		List<TotalVO> list = categoryMapper.getResultLowPriceBoards(startRow, endRow, gSearch, subc);
		return list;
	}
	public List<TotalVO> getResultHighPriceBoards(int startRow, int pageSize, String gSearch, String subc) {
		int endRow = startRow + pageSize - 1;
		List<TotalVO> list = categoryMapper.getResultHighPriceBoards(startRow, endRow, gSearch, subc);
		return list;
	}
	public List<TotalVO> getResultDateBoards(int startRow, int pageSize, String gSearch, String subc) {
		int endRow = startRow + pageSize - 1;
		List<TotalVO> list = categoryMapper.getResultDateBoards(startRow, endRow, gSearch, subc);
		return list;
	}
	
	
	// 상품 정보
	public void updateReadCount(int num, String majorc, String subc) {
		categoryMapper.updateReadCount(num, majorc, subc);
	}
	public TotalVO getInfoBoard(int num, String majorc, String subc) {
		TotalVO totalVO = categoryMapper.getInfoBoard(num, majorc, subc);
		return totalVO;
	}
	public TotalVO getInfoMinimumPrice(String name, String majorc, String subc) {
		TotalVO totalVO = categoryMapper.getInfoMinimumPrice(name, majorc, subc);
		return totalVO;
	}
	public List<TotalVO> getInfoLowPriceBoards(String name, String majorc, String subc) {
		List<TotalVO> list = categoryMapper.getInfoLowPriceBoards(name, majorc, subc);
		return list;
	}
}
