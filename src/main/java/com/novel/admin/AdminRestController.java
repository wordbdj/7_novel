package com.novel.admin;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.novel.admin.bo.AdminBO;
import com.novel.user.domain.User;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/admin")
@RestController
public class AdminRestController {

	@Autowired
	private AdminBO adminBO;
	
	@PostMapping("/follow/add-writer")
	public Map<String, Object> addWriter(
			@RequestParam("loginId")String loginId,
			@RequestParam("email")String email,
			HttpSession session) {
		
		
		User user = adminBO.getUserByLoginIdEmail(loginId, email);
		
		
		Map<String, Object> result = new HashMap<>();
		
		if (user != null) {
			int EditerUserId = (Integer) session.getAttribute("userId");
			int WriterUserId = user.getId();
			adminBO.insertFollow(EditerUserId, WriterUserId);
			result.put("code", 200);
			result.put("result", "성공");
		} else {
			result.put("code", 403);
			result.put("error_message", "존재하지 않는 사용자입니다.");
		}

		return result;
	}
}
