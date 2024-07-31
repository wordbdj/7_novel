package com.novel.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.novel.user.bo.UserBO;
import com.novel.user.domain.User;

@RequestMapping("/user")
@RestController
public class UserRestController {

	@Autowired
	private UserBO userBO;
	
	@RequestMapping("/is-duplicated-id")
	public Map<String, Object> isDuplicatedId(
			@RequestParam("loginId") String loginId) {
		
		// db 조회
		User user = userBO.getUserByLoginId(loginId);
		
		// 응답값
		Map<String, Object> result = new HashMap<>();
		result.put("code", 200);
		if(user != null) {		
			result.put("is_duplicated_id", true);
		} else {
			result.put("is_duplicated_id", false);
		}
		return result;
		
	}
	
}
