package com.exam.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exam.domain.CityVO;
import com.exam.mapper.CityMapper;

@Service
@Transactional
public class CityService {

	@Autowired
	private CityMapper cityMapper;
	
	// 주소 갯수
	public int getBoardCount(String search) {
		return cityMapper.getBoardCount(search);
	}
	
	// 주소 list
	public List<CityVO> getBoards(int startRow, int pageSize, String search) {
		int endRow = startRow + pageSize - 1;
		List<CityVO> list =  cityMapper.getBoards(startRow, endRow, search);
		return list;
	}
}
