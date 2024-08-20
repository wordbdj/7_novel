package com.novel.memo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.novel.memo.bo.MemoBO;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/memo")
public class MemoRestController {
	
	@Autowired
	private MemoBO memoBO;

	@PostMapping("/create")
	public Map<String, Object> create(
			@RequestParam("novelId") int novelId,
			@RequestParam("contentId") int contentId,
			@RequestParam("memo") String memo,
			HttpSession session){
			
		int userId = (int) session.getAttribute("userId");
		
		Integer Memo = memoBO.addMemo(userId, novelId, contentId, memo);
					
		Map<String, Object> result = new HashMap<>();
		
		if (Memo != null) {
			result.put("code", 200);
			result.put("result", "성공");
		} else {
			result.put("code", 403);
			result.put("error_message", "존재하지 않는 사용자입니다.");
		}

		return result;
		
	}
	
	
}
