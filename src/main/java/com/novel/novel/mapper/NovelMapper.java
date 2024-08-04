package com.novel.novel.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.novel.novel.domain.Novel;

@Mapper
public interface NovelMapper {

	List<Novel> selectNovelListByUserId(
			@Param("userId")int userId,
			@Param("standardId") Integer standardId,
			@Param("direction") String direction,
			@Param("limit") int limit);

	int selectNovelIdByUserIdAsSort(
			@Param("userId")int userId,
			@Param("sort") String sort);


}
