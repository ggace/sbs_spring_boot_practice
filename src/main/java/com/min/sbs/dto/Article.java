package com.min.sbs.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article {
	int id;
	Date regDate;
	Date updateDate;
	int memberId;
	String title;
	String body;
}
