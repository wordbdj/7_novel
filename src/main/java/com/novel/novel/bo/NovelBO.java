package com.novel.novel.bo;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novel.novel.domain.Novel;
import com.novel.novel.mapper.NovelMapper;

@Service
public class NovelBO {

	private static final int NOVEL_MAX_SIZE = 10;
	
	@Autowired
	private NovelMapper novelMapper;
	
public List<Novel> getPostListByUserId(int userId, Integer prevId, Integer nextId){
		
		Integer standardId = null; // 기준 postId
		String direction = null; // 방향
		if (prevId != null) { // 2) 이전
			standardId = prevId;
			direction = "prev";
			
			List<Novel> novelList = novelMapper.selectNovelListByUserId(userId, standardId, direction, NOVEL_MAX_SIZE);
			Collections.reverse(novelList);
			
			return novelList;
			
		} else if (nextId != null) { // 1) 다음 
			standardId = nextId;
			direction = "next";
		}
		
		// 3) 페이징 X, 1) 다음
		return novelMapper.selectNovelListByUserId(userId, standardId, direction, NOVEL_MAX_SIZE);
	}
	
	// 이전 페이지의 마지막인가?
	
	public boolean isPrevLastPageByUserId(int userId,int prevId) {
		
		int maxPostId = novelMapper.selectNovelIdByUserIdAsSort(userId, "DESC");
		return maxPostId == prevId; // 같으면 마지막
	}
	
	public boolean isNextLastPageByUSerId(int userId,int nextId) {
		
		int minPostId = novelMapper.selectNovelIdByUserIdAsSort(userId, "ASC");
		return minPostId == nextId; // 같으면 마지막
	}
	
}
