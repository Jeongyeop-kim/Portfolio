package com.exam.domain;

import java.util.Date;

import lombok.Data;

@Data
public class MemberVO {
	private String id;
	private String passwd;
	private String name;
	private String nickname;
	private String email;
	private String address;
	private String tel;
	private String mtel;
	private Date regDate;
} // MemberVO class
