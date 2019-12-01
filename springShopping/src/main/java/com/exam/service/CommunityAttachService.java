package com.exam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exam.domain.FattachVO;
import com.exam.mapper.CommunityAttachMapper;

@Service
@Transactional
public class CommunityAttachService {

	@Autowired
	private CommunityAttachMapper communityAttachMapper;
	
	public void insertAttach(FattachVO attachVO) {
		communityAttachMapper.insertAttach(attachVO);
	}
	public void insertAttaches(List<FattachVO> attachList) {
		if (attachList.size() > 0) {
			for (FattachVO attachVO : attachList) {
				communityAttachMapper.insertAttach(attachVO);
			}
		}
	}
	public List<FattachVO> getAttaches(int bno) {
		List<FattachVO> list = communityAttachMapper.getAttaches(bno);
		return list;
	}
	public void deleteAttachByBno(int bno) {
		communityAttachMapper.deleteAttachByBno(bno);
	}
	public void deleteAttachByUuid(String uuid) {
		communityAttachMapper.deleteAttachByUuid(uuid);
	}
	public FattachVO getAttachByUuid(String uuid) {
		FattachVO attachVO = communityAttachMapper.getAttachByUuid(uuid);
		return attachVO;
	}
}
