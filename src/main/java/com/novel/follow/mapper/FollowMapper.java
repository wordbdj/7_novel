package com.novel.follow.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface FollowMapper {

	public int insertFollow(
			@Param("EditorUserId")int EditerUserId,
			@Param("WritterUserId")int WriterUserId);

	public void deleteByWriterUserId(int WritterUserId);

	
	
}
