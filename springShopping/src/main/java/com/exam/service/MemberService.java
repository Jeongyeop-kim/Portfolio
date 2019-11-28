package com.exam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exam.domain.MemberVO;
import com.exam.mapper.MemberMapper;

@Service
@Transactional
public class MemberService {
	
	@Autowired
	private MemberMapper memberMapper;
	
	// 아이디 검색
	public int countMemberById(String id) {
		return memberMapper.countMemberById(id);
	}
	
	// 아이디 중복확인
	public boolean isIdDuplicated(String id) {
		boolean result = false;
		int count = memberMapper.countMemberById(id);
		if (count > 0) {
			result = true;
		}
		return result;
	}
	
	// 비밀번호 중복확인
	public boolean isPasswdDuplicated(String id, String newpasswd) {
		boolean result = false;
		MemberVO memberVO = memberMapper.getMemberById(id);
		if (newpasswd.equals(memberVO.getPasswd())) {
			result = true;
		} else {
			result = false;
		}
		return result;
	}
	
	// 회원 가입
	public int insertMember(MemberVO memberVO) {
		return memberMapper.insertMember(memberVO);
	}
	
	// 로그인
	public int userCheck(String id, String passwd) {
		int check = -1;
		MemberVO memberVO = memberMapper.getMemberById(id);
		if (memberVO != null) {
			if (passwd.equals(memberVO.getPasswd())) {
				check = 1;
			} else {
				check = 0;
			}
		} else {
			check = -1;
		}
		return check;
	}
	
	// 아이디 찾기
	public int findId(String name, String email) {
		int check = memberMapper.findId(name, email);
		return check;
	}
	
	// 비밀번호 찾기
	public int findPasswd(String id, String email) {
		int check = memberMapper.findPasswd(id, email);
		return check;
	}
	
	// 회원정보 가져오기
	public MemberVO getMember(String id) {
		MemberVO memberVO = memberMapper.getMemberById(id);
		return memberVO;
	}
	
	// 회원정보 가져오기(이름, 이메일)
	public MemberVO getMemberByNameAndEmail(String name, String email) {
		MemberVO memberVO = memberMapper.getMemberByNameAndEmail(name, email);
		return memberVO;
	}
	
	// 회원정보 가져오기(아이디, 이메일)
	public MemberVO getMemberByIdAndEmail(String id, String email) {
		MemberVO memberVO = memberMapper.getMemberByIdAndEmail(id, email);
		return memberVO;
	}
	
	// 회원 수
	public int getMemberCount(String mSearch) {
		return memberMapper.getMemberCount(mSearch);
	}
	
	// 회원목록
	public List<MemberVO> getMembers() {
		List<MemberVO> list = memberMapper.getMembers();
		return list;
	}
	
	// 회원목록(관리자)
	public List<MemberVO> getMembers(String id, int startRow, int pageSize) {
		int endRow = startRow + pageSize - 1;
		List<MemberVO> list = memberMapper.getMembersById(id, endRow, startRow);
		return list;
	}
	
	// 회원 정보 수정
	public void updateMember(MemberVO memberVO) {
		memberMapper.updateMember(memberVO);
	}
	
	// 비밀번호 변경
	public void updatePasswdOfMember(String newpasswd, String id) {
		memberMapper.updatePasswdOfMember(newpasswd, id);
	}
	
	// 비밀번호 변경(이메일)
	public void updatePasswdOfMemberByEmail(String passwd, String id, String email) {
		memberMapper.updatePasswdOfMemberByEmail(passwd, id, email);
	}
	
	// 회원 탈퇴
	public void deleteMember(String id) {
		memberMapper.deleteMember(id);
	}
}
