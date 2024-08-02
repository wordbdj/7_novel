package com.novel.follow.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novel.follow.mapper.FollowMapper;

@Service
public class FollowBO {
	
	@Autowired
	private FollowMapper followMapper;

	public int insertFollow(int EditerUserId, int WriterUserId) {

		return followMapper.insertFollow(EditerUserId, WriterUserId);
	}

	public void removeByWriterUserId(int WritterUserId) {

		followMapper.deleteByWriterUserId(WritterUserId);
	}

}
