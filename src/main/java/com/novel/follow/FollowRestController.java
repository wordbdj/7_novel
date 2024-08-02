package com.novel.follow;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.novel.follow.bo.FollowBO;

@RequestMapping("/follow")
@RestController
public class FollowRestController {
	
	@Autowired
	private FollowBO followBO;
	
	@DeleteMapping("/delete")
	public Map<String, Object> delete(
			@RequestParam("WriterUserId") int WriterUserId) {
		
		followBO.removeByWriterUserId(WriterUserId);

		Map<String, Object> result = new HashMap<>();
		result.put("code", 200);
		result.put("result", "성공");
		return result;
	}
	
}
