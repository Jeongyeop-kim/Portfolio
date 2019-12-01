package com.exam.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exam.domain.AttachVO;
import com.exam.mapper.TradeAttachMapper;

@Service
@Transactional
public class TradeAttachService {

	@Autowired
	private TradeAttachMapper tradeAttachMapper;
	
	public void insertAttach(AttachVO attachVO) {
		tradeAttachMapper.insertAttach(attachVO);
	}
	public void insertAttaches(List<AttachVO> attachList) {
		if (attachList.size() > 0) {
			for (AttachVO attachVO : attachList) {
				tradeAttachMapper.insertAttach(attachVO);
			}
		}
	}
	public List<AttachVO> getAttaches(int bno) {
		List<AttachVO> list = tradeAttachMapper.getAttaches(bno);
		return list;
	}
	public void deleteAttachByBno(int bno) {
		tradeAttachMapper.deleteAttachByBno(bno);
	}
	public void deleteAttachByUuid(String uuid) {
		tradeAttachMapper.deleteAttachByUuid(uuid);
	}
	public AttachVO getAttachByUuid(String uuid) {
		AttachVO attachVO = tradeAttachMapper.getAttachByUuid(uuid);
		return attachVO;
	}
}
