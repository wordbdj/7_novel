package com.novel.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.novel.user.domain.User;

@Mapper
public interface UserMapper {

	public User selectUserByLoginId(
			@Param("loginId")String loginId);

	
}
