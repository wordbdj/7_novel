package com.novel.content.bo;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novel.content.domain.Content;
import com.novel.content.mapper.ContentMapper;

@Service
public class ContentBO {
	
	private static final int NOVEL_MAX_SIZE = 10;
	
	@Autowired
	private ContentMapper contentMapper;

	public List<Content> getContentListByUserId(Integer userId, int novelId, Integer prevId, Integer nextId) {
		
		Integer standardId = null; // 기준 postId
		String direction = null; // 방향
		if (prevId != null) { // 2) 이전
			standardId = prevId;
			direction = "prev";
			
			List<Content> contentList = contentMapper.selectContentListByUserIdNovelId(userId, novelId, standardId, direction, NOVEL_MAX_SIZE);
			Collections.reverse(contentList);
			
			return contentList;
			
		} else if (nextId != null) { // 1) 다음 
			standardId = nextId;
			direction = "next";
		}
		
		// 3) 페이징 X, 1) 다음
		return contentMapper.selectContentListByUserIdNovelId(userId, novelId, standardId, direction, NOVEL_MAX_SIZE);
	}
	
	// 이전 페이지의 마지막인가?
	
	public boolean isPrevLastPageByUserId(int userId,int prevId) {
		
		int maxPostId = contentMapper.selectContentIdByUserIdAsSort(userId, "DESC");
		return maxPostId == prevId; // 같으면 마지막
	}
	
	public boolean isNextLastPageByUSerId(int userId,int nextId) {
		
		int minPostId = contentMapper.selectContentIdByUserIdAsSort(userId, "ASC");
		return minPostId == nextId; // 같으면 마지막
	}

	public Integer addContent( int userId, int novelId, String title, String content, LocalDateTime uploadTime) {
		
		return contentMapper.insertContent(userId, novelId, title, content, uploadTime);
	}

	public List<Content> getContentListByUserId(int userId, int novelId) {
		
		return contentMapper.selectContentListByUserId(userId, novelId);
	}

	public List<Content> getContentListByTitle(String title) {

		return contentMapper.selectContentListByTitle(title);
	}

}
