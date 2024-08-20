package com.novel.memo.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novel.memo.domain.Memo;
import com.novel.memo.mapper.MemoMapper;

@Service
public class MemoBO {
	
	@Autowired
	private MemoMapper memoMapper;

	public List<Memo> getMemoByContentId(int contentId) {
		
		return memoMapper.selectMemoByContentId(contentId);
	}

	public Integer addMemo(int userId, int novelId, int contentId, String memo) {
		
		return memoMapper.insertMemo(userId, novelId, contentId, memo);
	}


}
