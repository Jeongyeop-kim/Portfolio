package com.exam.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.exam.domain.FattachVO;

public interface CommunityAttachMapper {

	public void insertAttach(FattachVO attachVO);
	public List<FattachVO> getAttaches(@Param("bno") int bno);
	public void deleteAttachByBno(@Param("bno") int bno);
	public void deleteAttachByUuid(@Param("uuid") String uuid);
	public FattachVO getAttachByUuid(@Param("uuid") String uuid);
}
