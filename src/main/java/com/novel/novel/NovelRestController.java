package com.novel.novel;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.novel.novel.bo.NovelBO;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/novel")
@RestController
public class NovelRestController {

	@Autowired
	private NovelBO novelBO;
	
	@PostMapping("/create")
	public Map<String, Object> create(
			@RequestParam("title")String title,
			@RequestParam("explain")String explain,
			HttpSession session){
			
		int userId = (int) session.getAttribute("userId");
		
		Integer novel = novelBO.addNovel(title, explain, userId);
					
		Map<String, Object> result = new HashMap<>();
		
		if (novel != null) {
			result.put("code", 200);
			result.put("result", "성공");
		} else {
			result.put("code", 403);
			result.put("error_message", "존재하지 않는 사용자입니다.");
		}

		return result;
		
	}
	
	
}
