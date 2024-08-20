package com.novel.comment.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novel.comment.domain.Comment;
import com.novel.comment.mapper.CommentMapper;

@Service
public class CommentBO {
	
	@Autowired
	private CommentMapper commentMapper;

	public List<Comment> getCommentByContentId(int contentId) {
		
		return commentMapper.selectCommentByContentId(contentId);
	}

}
