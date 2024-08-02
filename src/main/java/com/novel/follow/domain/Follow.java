package com.novel.follow.domain;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class Follow {

	private int EditorUserId;  
	private int WritterUserId;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
}
