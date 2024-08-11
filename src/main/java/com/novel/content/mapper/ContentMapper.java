package com.novel.content.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.novel.content.domain.Content;

@Mapper
public interface ContentMapper {

	List<Content> selectContentListByUserId (
			@Param("userId")int userId,
			@Param("novelId")int novelId);

	List<Content> selectContentListByUserIdNovelId(
			@Param("userId")int userId,
			@Param("novelId")int novelId,
			@Param("standardId") Integer standardId,
			@Param("direction") String direction,
			@Param("limit") int limit);

	int selectContentIdByUserIdAsSort(
			@Param("userId")int userId,
			@Param("sort") String sort);

	Integer insertContent(
			@Param("title")String title,
			@Param("content")String content,
			@Param("userId")int userId,
			@Param("content") int novelId);

	
	

}
