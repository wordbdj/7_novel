package com.novel.content;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.novel.content.bo.ContentBO;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/content")
@RestController
public class ContentRestController {

	@Autowired
	private ContentBO contentBO;
	
	@PostMapping("/create")
	public Map<String, Object> create(
			@RequestParam("title")String title,
			@RequestParam("content")String content,
			@RequestParam("novelId") int novelId,
			@RequestParam(value = "uploadTime", required = false) LocalDateTime uploadTime,
			HttpSession session){
		
		int userId = (int) session.getAttribute("userId");
		
		if (uploadTime == null) {
			LocalDateTime time = LocalDateTime.now();
			LocalDateTime  DayPast = time.plusDays(1);
			uploadTime = DayPast;
		}
		
		Integer Content = contentBO.addContent(userId, novelId, title, content, uploadTime);
					
		Map<String, Object> result = new HashMap<>();
		
		if (Content != null) {
			result.put("code", 200);
			result.put("result", "성공");
		} else {
			result.put("code", 403);
			result.put("error_message", "존재하지 않는 사용자입니다.");
		}

		return result;
		
	}
	
	@PutMapping("/update")
	public Map<String, Object> update(
			@RequestParam("title")String title,
			@RequestParam("content")String content,
			@RequestParam("novelId") int novelId,
			@RequestParam("contentId") int contentId,
			@RequestParam(value = "uploadTime", required = false) LocalDateTime uploadTime,
			HttpSession session){
		
		int userId = (int) session.getAttribute("userId");
		
		if (uploadTime == null) {
			LocalDateTime time = LocalDateTime.now();
			LocalDateTime  DayPast = time.plusDays(1);
			uploadTime = DayPast;
		}
		
		Integer Content = contentBO.updateContentByUserIdNovelIdContentId(userId, novelId, contentId, title, content, uploadTime);
		
		Map<String, Object> result = new HashMap<>();
		
		if (Content != null) {
			result.put("code", 200);
			result.put("result", "성공");
		} else {
			result.put("code", 403);
			result.put("error_message", "존재하지 않는 사용자입니다.");
		}
		
		return result;
		
	}
	
}
