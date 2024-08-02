package com.novel.user.domain;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class User {

	private int id; 
	private String loginId;
	private String password;
	private String name;  
	private String email; 
	private String type;
	private LocalDateTime createdAt;  
	private LocalDateTime updatedAt ;
	
	
}
