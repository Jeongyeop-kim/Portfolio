package com.exam.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exam.domain.BoardVO;
import com.exam.domain.FboardVO;
import com.exam.domain.GraphVO;
import com.exam.mapper.AdminMapper;

@Service
@Transactional
public class AdminService {
	
	@Autowired
	private AdminMapper adminMapper;
	
	// admin/members
	public List<Map<String, Object>> getAddressOfMember() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<GraphVO> member = adminMapper.getAddressOfMember();
		for (GraphVO graphVO : member) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("address", graphVO.getAddress());
			map.put("cnt", graphVO.getCnt());
			list.add(map);
		}
		return list;
	}
	
	
	// admin/boards
	public List<Map<String, Object>> getTotalCountOfBoards() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<GraphVO> board = adminMapper.getTotalCountOfBoards();
		for (GraphVO graphVO : board) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("tableName", graphVO.getTableName());
			map.put("numrow", graphVO.getNumrow());
			list.add(map);
		}
		return list;
	}
	public List<BoardVO> getAdminTboards(int startRow, int pageSize, String tSearch) {
		int endRow = startRow + pageSize - 1;
		List<BoardVO> list = adminMapper.getAdminTboards(startRow, endRow, tSearch);
		return list;
	}
	public List<FboardVO> getAdminFboards(int startRow, int pageSize, String fSearch) {
		int endRow = startRow + pageSize - 1;
		List<FboardVO> list = adminMapper.getAdminFboards(startRow, endRow, fSearch);
		return list;
	}
}
