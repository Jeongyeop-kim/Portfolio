package com.exam.mapper;

import java.util.*;

import org.apache.ibatis.annotations.Param;

import com.exam.domain.CityVO;

public interface CityMapper {
	
	public int getBoardCount(String search);
	public List<CityVO> getBoards(@Param("startRow") int startRow, @Param("endRow") int endRow, @Param("search") String search);
}
