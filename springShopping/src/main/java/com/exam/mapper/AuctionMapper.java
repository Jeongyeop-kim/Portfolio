package com.exam.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.exam.domain.BuyVO;
import com.exam.domain.SellVO;

public interface AuctionMapper {

	public int getBoardCount(@Param("search") String search);
	public List<SellVO> getBoards(@Param("startRow") int startRow, @Param("endRow") int endRow, @Param("search") String search);
	public SellVO getBoard(@Param("num") int num);
	public int getBuyCount(@Param("num") int num);
	public int getMaxPrice(@Param("num") int num);
	public void insertBuy(BuyVO buyVO);
	public void updateBuy(BuyVO buyVO);
}
