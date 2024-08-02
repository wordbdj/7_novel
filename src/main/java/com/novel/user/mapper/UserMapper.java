package com.novel.user.mapper;

import java.util.List;
import java.util.Map;

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


	public User selectUserByLoginIdEmail(
			@Param("loginId")String loginId,
			@Param("email")String email);


	public User selectUserById(int id);


	public List<User> selectUserJoinFollowByEditorUserId(int userId);



	
}
