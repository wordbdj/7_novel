package com.novel.novel.domain;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class Novel {

	private int id;
	private int userId;
	private String title; 
	private String explain;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
}
