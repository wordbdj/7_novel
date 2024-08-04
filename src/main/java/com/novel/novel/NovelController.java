package com.novel.novel;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.novel.novel.bo.NovelBO;
import com.novel.novel.domain.Novel;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/novel")
@Controller
public class NovelController {

	@Autowired
	private NovelBO novelBO;
	
	@GetMapping("/novel-list-view")
	public String novelListView(
			@RequestParam(value = "prevId", required = false) Integer prevIdParam,
			@RequestParam(value = "nextId", required = false) Integer nextIdParam,
			HttpSession session, Model model) {
		
		Integer userId = (Integer) session.getAttribute("userId");
		if (userId == null) {
			return "redirect:/user/sign-in-view";
		}

		// DB 조회 - 글 목록
		List<Novel> postList = novelBO.getPostListByUserId(userId, prevIdParam, nextIdParam);
		int prevId = 0;
		int nextId = 0;
		if (postList.isEmpty() == false) { // 글목록이 비어있지 않을 떄 페이징 정보 세팅
			prevId = postList.get(0).getId(); // 첫번째칸 id 
			nextId = postList.get(postList.size()- 1).getId(); // 마지막 칸 id
			
			// 이전 방향의 끝인가? 그러면 0
			// prevId와 테이블의 제일 큰 숫자와 같으면 이전의 끝 페이지
			if(novelBO.isPrevLastPageByUserId(userId, prevId)) {
				prevId = 0;
			}
			
			// 다음 방향의 끝인가? 그러면 0
			// nextId와 테이블의 제일 작은 숫자와 같으면 다음의 끝 페이지
			if (novelBO.isNextLastPageByUSerId(userId, nextId)) {
				nextId = 0;
			}
			
		}
		// 모델에 담기
		model.addAttribute("postList", postList);
		model.addAttribute("prevId", prevId);
		model.addAttribute("nextId", nextId);

		return "novel/novelList";
	}
}
