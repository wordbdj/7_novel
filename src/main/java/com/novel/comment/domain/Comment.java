package com.novel.comment.domain;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class Comment {
	private int id;
	private int userId;
	private int novelId;
	private int contentId;
	private String comment;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
