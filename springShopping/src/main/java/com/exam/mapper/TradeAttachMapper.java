package com.exam.mapper;

import java.util.*;

import org.apache.ibatis.annotations.Param;

import com.exam.domain.AttachVO;

public interface TradeAttachMapper {

	public void insertAttach(AttachVO attachVO);
	public List<AttachVO> getAttaches(@Param("bno") int bno);
	public void deleteAttachByBno(@Param("bno") int bno);
	public void deleteAttachByUuid(@Param("uuid") String uuid);
	public AttachVO getAttachByUuid(@Param("uuid") String uuid);
}
