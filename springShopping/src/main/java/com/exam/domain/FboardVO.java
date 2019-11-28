package com.exam.domain;

import java.util.Date;

import lombok.Data;

@Data
public class FboardVO {
	private int num;
	private String username;
	private String subject;
	private String content;
	private int readcount;
	private String ip;
	private Date regDate;
	private int reRef;
	private int reLev;
	private int reSeq;
	private int good;
	private String usernick;
	private String gooduser;
} // FboardVO class
