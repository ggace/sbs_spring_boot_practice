package com.min.sbs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article {
	int id;
	String regDate;
	String updateDate;
	int memberId;
	String title;
	String body;
}
