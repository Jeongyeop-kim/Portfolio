package com.exam.domain;

import java.util.Date;

import lombok.Data;

@Data
public class SellVO {
	private int num;
	private String name;
	private int quantity;
	private int minPrice;
	private String sellerName;
	private String uuid;
	private String filepath;
	private String filename;
	private String info;
	private Date regDate;
	private int maxPrice;
} // class SellVO
