package com.exam.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exam.domain.BuyVO;
import com.exam.domain.SellVO;
import com.exam.mapper.AuctionMapper;

@Service
@Transactional
public class AuctionService {

	@Autowired
	private AuctionMapper auctionMapper;
	
	// auction/auction
	public int getBoardCount(String aSearch) {
		return auctionMapper.getBoardCount(aSearch);
	}
	public List<SellVO> getBoards(int startRow, int pageSize, String aSearch) {
		int endRow = startRow + pageSize - 1;
		List<SellVO> list = auctionMapper.getBoards(startRow, endRow, aSearch);
		return list;
	}
	
	
	// auction/auctionContent
	public SellVO getBoard(int num) {
		SellVO sellVO = auctionMapper.getBoard(num);
		return sellVO;
	}
	
	
	// auction/auctionProcess
	public int getBuyCount(int num) {
		return auctionMapper.getBuyCount(num);
	}
	public int getMaxPrice(int num) {
		return auctionMapper.getMaxPrice(num);
	}
	public void insertBuy(BuyVO buyVO) {
		auctionMapper.insertBuy(buyVO);
	}
	public void updateBuy(BuyVO buyVO) {
		auctionMapper.updateBuy(buyVO);
	}
}
