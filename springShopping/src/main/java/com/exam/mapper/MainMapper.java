package com.exam.mapper;

import java.util.*;

import com.exam.domain.BoardVO;
import com.exam.domain.FboardVO;
import com.exam.domain.SellVO;
import com.exam.domain.TotalVO;

public interface MainMapper {
	
	public int getFboardCount();
	public int getTboardCount();
	public int getMerchandiseCount();
	public int getAuctionCount();
	public List<FboardVO> getMainFboards();
	public List<BoardVO> getMainTboards();
	public List<TotalVO> getMainRecentBoards();
	public List<TotalVO> getMainBestBoards();
	public List<SellVO> getMainAboards();
}
