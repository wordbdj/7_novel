package com.novel.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.novel.user.domain.User;

@Mapper
public interface UserMapper {

	public User selectUserByLoginId(
			@Param("loginId")String loginId);


	public int insertUser(@Param("loginId")String loginId,
			@Param("password")String password, 
			@Param("name")String name, 
			@Param("email")String email, 
			@Param("type")String type);


	public User selectUserByLoginIdPassword(@Param("loginId")String loginId,
			@Param("password") String password);

	
}
