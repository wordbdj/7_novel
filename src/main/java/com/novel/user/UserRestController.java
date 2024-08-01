package com.novel.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.novel.common.EncryptUtils;
import com.novel.user.bo.UserBO;
import com.novel.user.domain.User;

import jakarta.servlet.http.HttpSession;

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
	
	@PostMapping("/sign-up")
	public Map<String, Object> signUp(
			@RequestParam("loginId") String loginId, 
			@RequestParam("password") String password, 
			@RequestParam("name") String name, 
			@RequestParam("email") String email,
			@RequestParam("type") String type){
		
		
		// password md5 알고리즘 => hashing  ex) aa => 2232 (항상 hashing 되는 값은 같다)
		// aaaa => 74b8733745420d4d33f80c4663dc5e5
		String hashedPassword = EncryptUtils.md5(password);
		
		
		// db insert
		int user = userBO.addUser(loginId, hashedPassword, name, email, type);
		
		// 응답값
		Map<String, Object> result = new HashMap<>();
		if (user != 0) {
			result.put("code", 200);
			result.put("result", "성공");			
		} else {
			result.put("code", 500);
			result.put("error_message", "회원가입에 실패했습니다.");		
		}
		return result;
		
	}
	
	@PostMapping("/sign-in")
	public Map<String, Object> signIn(
			@RequestParam("loginId")String loginId,
			@RequestParam("password")String password,
			HttpSession session) {
		
		// Hashing Password
		String hashedPassword = EncryptUtils.md5(password);

		// Check user (UserEntity or null)
		
		User user = userBO.getUserEntityByLoginIdPassword(loginId, hashedPassword);
		
		// session & result
		Map<String, Object> result = new HashMap<>();
		if (user != null) {
			session.setAttribute("userId", user.getId());
			session.setAttribute("userLoginId", user.getLoginId());
			session.setAttribute("userType", user.getType());
			result.put("userType", user.getType());
			result.put("code", 200);
			result.put("result", "성공");
		} else {
			result.put("code", 403);
			result.put("error_message", "존재하지 않는 사용자입니다.");
		}
		
		return result;
	}
	
}
