package com.exam.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.exam.domain.MemberVO;

public interface MemberMapper {
	
	public int countMemberById(String id);
	public int insertMember(MemberVO memberVO);
	public int findId(@Param("name") String name, @Param("email") String email);
	public int findPasswd(@Param("id") String id, @Param("email") String email);	
	public MemberVO getMemberById(String id);
	public MemberVO getMemberByNameAndEmail(@Param("name") String name, @Param("email") String email);
	public MemberVO getMemberByIdAndEmail(@Param("id") String id, @Param("email") String email);
	public int getMemberCount(@Param("mSearch") String mSearch);
	public List<MemberVO> getMembers();
	public List<MemberVO> getMembersById(@Param("id") String id, @Param("endRow") int endRow, @Param("startRow") int startRow);
	public void updateMember(MemberVO memberVO);
	public void updatePasswdOfMember(@Param("newpasswd") String newpasswd, @Param("id") String id);
	public void updatePasswdOfMemberByEmail(@Param("passwd") String passwd, @Param("id") String id, @Param("email") String email);
	public void deleteMember(String id);
}
