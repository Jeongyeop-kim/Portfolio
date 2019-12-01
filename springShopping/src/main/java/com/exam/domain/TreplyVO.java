package com.exam.domain;

import java.util.Date;

import lombok.Data;

@Data
public class TreplyVO {
	private int num;
	private String username;
	private String content;
	private String ip;
	private Date regDate;
	private int reRef;
	private int reLev;
	private int reSeq;
	private int parentnum;
} // TreplyVO class
