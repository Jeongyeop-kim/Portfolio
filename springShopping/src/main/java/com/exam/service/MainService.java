package com.exam.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exam.domain.BoardVO;
import com.exam.domain.FboardVO;
import com.exam.domain.SellVO;
import com.exam.domain.TotalVO;
import com.exam.mapper.MainMapper;

@Service
@Transactional
public class MainService {

	@Autowired
	private MainMapper mainMapper;
	
	public int getFboardCount() {
		return mainMapper.getFboardCount();
	}
	public int getTboardCount() {
		return mainMapper.getTboardCount();
	}
	public int getMerchandiseCount() {
		return mainMapper.getMerchandiseCount();
	}
	public int getAuctionCount() {
		return mainMapper.getAuctionCount();
	}
	public List<FboardVO> getMainFboards() {
		List<FboardVO> list = mainMapper.getMainFboards();
		return list;
	}
	public List<BoardVO> getMainTboards() {
		List<BoardVO> list = mainMapper.getMainTboards();
		return list;
	}
	public List<TotalVO> getMainRecentBoards() {
		List<TotalVO> list = mainMapper.getMainRecentBoards();
		return list;
	}
	public List<TotalVO> getMainBestBoards() {
		List<TotalVO> list = mainMapper.getMainBestBoards();
		return list;
	}
	public List<SellVO> getMainAboards() {
		List<SellVO> list = mainMapper.getMainAboards();
		return list;
	}
}
