package com.novel.memo.domain;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class Memo {

	private int id;
	private int userId; 
	private int novelId; 
	private int contentId;
	private String memo; 
	private LocalDateTime createdAt; 
	private LocalDateTime updatedAt;
}
