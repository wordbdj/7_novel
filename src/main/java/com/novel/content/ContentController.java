package com.novel.content;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.novel.comment.bo.CommentBO;
import com.novel.comment.domain.Comment;
import com.novel.content.bo.ContentBO;
import com.novel.content.domain.Content;
import com.novel.memo.bo.MemoBO;
import com.novel.memo.domain.Memo;

import jakarta.servlet.http.HttpSession;


@RequestMapping("/content")
@Controller
public class ContentController {

	@Autowired
	private ContentBO contentBO;
	
	@Autowired
	private MemoBO memoBO;
	
	@Autowired
	private CommentBO commentBO;
	
	@GetMapping("/content-list-view")
	public String novelListview(
			@RequestParam(value = "prevId", required = false) Integer prevIdParam,
			@RequestParam(value = "nextId", required = false) Integer nextIdParam,
			@RequestParam(value = "userId", required = false) Integer userId,
			@RequestParam("novelId") int novelId,
			HttpSession session, Model model) {
		
		if (userId == null) {
			Integer UserId = (Integer) session.getAttribute("userId");
			userId = UserId;
			if (userId == null) {
				return "redirect:/user/sign-in-view";
			}
		}
		
		List<Content> contentList = contentBO.getContentListByUserId(userId, novelId, prevIdParam, nextIdParam);
		int prevId = 0;
		int nextId = 0;
		if (contentList.isEmpty() == false) { // 글목록이 비어있지 않을 떄 페이징 정보 세팅
			prevId = contentList.get(0).getId(); // 첫번째칸 id 
			nextId = contentList.get(contentList.size()- 1).getId(); // 마지막 칸 id
			
			// 이전 방향의 끝인가? 그러면 0
			// prevId와 테이블의 제일 큰 숫자와 같으면 이전의 끝 페이지
			if(contentBO.isPrevLastPageByUserId(userId, prevId)) {
				prevId = 0;
			}
			
			// 다음 방향의 끝인가? 그러면 0
			// nextId와 테이블의 제일 작은 숫자와 같으면 다음의 끝 페이지
			if (contentBO.isNextLastPageByUSerId(userId, nextId)) {
				nextId = 0;
			}
			
		}
		// 모델에 담기
		model.addAttribute("ContentList", contentList);
		model.addAttribute("prevId", prevId);
		model.addAttribute("nextId", nextId);
		model.addAttribute("novelId", novelId);
		return "content/contentList";
	}
	
	@GetMapping("/content-detail-view")
	public String contentDetailView(
			@RequestParam(value="novelId") int novelId,
			@RequestParam(value="title", required = false)String title,
			@RequestParam(value = "userId", required = false) Integer userId,
			HttpSession session, Model model) {
		
		if (userId == null) {
			Integer UserId = (Integer) session.getAttribute("userId");
			userId = UserId;
			if (userId == null) {
				return "redirect:/user/sign-in-view";
			}
		}
		

		
		if (title != null) {
			List<Content> ContentList = contentBO.getContentListByTitle(title);
			model.addAttribute("ContentList", ContentList);
		} else {
			title = "noTitle";
		}
		
		
		model.addAttribute("title", title);
		model.addAttribute("novelId", novelId);
		
		return "content/contentDetail";
	}
	
	@GetMapping("/content-memo-view")
	public String novelMemoView(
			@RequestParam("contentId") int contentId,
			@RequestParam("title")String title,
			@RequestParam(value = "userId", required = false) Integer userId,

			HttpSession session, Model model) {
		
		if (userId == null) {
			Integer UserId = (Integer) session.getAttribute("userId");
			userId = UserId;
			if (userId == null) {
				return "redirect:/user/sign-in-view";
			}
		}
		
		List<Content> ContentList = contentBO.getContentListByTitle(title);
		List<Memo> MemoList = memoBO.getMemoByContentId(contentId);
		
		model.addAttribute("MemoList", MemoList);
		model.addAttribute("ContentList", ContentList);
		
		return "content/contentMemo";
	}
	

	@GetMapping("/content-comment-view")
	public String contentCommentView(
			@RequestParam("contentId") int contentId,
			@RequestParam("title")String title,
			@RequestParam(value = "userId", required = false) Integer userId,
			HttpSession session, Model model) {
		
		if (userId == null) {
			Integer UserId = (Integer) session.getAttribute("userId");
			userId = UserId;
			if (userId == null) {
				return "redirect:/user/sign-in-view";
			}
		}
		
		List<Content> ContentList = contentBO.getContentListByTitle(title);
		List<Comment> CommentList = commentBO.getCommentByContentId(contentId);
		
		model.addAttribute("CommentList", CommentList);
		model.addAttribute("ContentList", ContentList);
		
		return "content/contentComment";
	}
}
