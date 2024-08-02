package com.novel.admin;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.novel.user.domain.User;

@RequestMapping("/admin")
@RestController
public class AdminRestController {

	@PostMapping("/follow/add-writer")
	public Map<String, Object> addWriter(
			@RequestParam("loginId")String loginId,
			@RequestParam("email")String email) {
		
		
		User user = userBO.getUserByLoginIdEmail(loginId, email);
		
		
		Map<String, Object> result = new HashMap<>();
		
		if (user != null) {
			result.put("code", 200);
			result.put("result", "성공");
		} else {
			result.put("code", 403);
			result.put("error_message", "존재하지 않는 사용자입니다.");
		}

		return result;
	}
}
