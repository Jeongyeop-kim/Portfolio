package com.exam.mapper;

import java.util.*;

import org.apache.ibatis.annotations.Param;

import com.exam.domain.BoardVO;
import com.exam.domain.FboardVO;
import com.exam.domain.GraphVO;

public interface AdminMapper {

	// admin/members
	public List<GraphVO> getAddressOfMember();
	
	
	// admin/boards
	public List<GraphVO> getTotalCountOfBoards();
	public List<BoardVO> getAdminTboards(@Param("startRow") int startRow, @Param("endRow") int endRow, @Param("tSearch") String tSearch);
	public List<FboardVO> getAdminFboards(@Param("startRow") int startRow, @Param("endRow") int endRow, @Param("fSearch") String fSearch);
}
