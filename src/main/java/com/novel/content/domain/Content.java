package com.novel.content.domain;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class Content {
	private int id;
	private int userId;
	private int novelId;
	private String title;
	private String content;
	private LocalDateTime uploadTime;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
}
