package com.novel.comment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.novel.comment.domain.Comment;

@Mapper
public interface CommentMapper {

	List<Comment> selectCommentByContentId(int contentId);

}
