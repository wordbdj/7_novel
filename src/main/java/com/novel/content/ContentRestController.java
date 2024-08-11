package com.novel.content;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@GetMapping("/create")
	public Map<String, Object> create(
			@RequestParam("title")String title,
			@RequestParam("content")String content,
			@RequestParam("novelId") int novelId,
			HttpSession session){
		
		int userId = (int) session.getAttribute("userId");
		
		Integer Content = contentBO.addContent(title, content, novelId, userId);
					
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
