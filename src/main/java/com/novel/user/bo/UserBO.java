package com.novel.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novel.user.domain.User;
import com.novel.user.mapper.UserMapper;

@Service
public class UserBO {

	@Autowired
	private UserMapper userMapper;
	
	public User getUserByLoginId(String loginId) {
		
		return userMapper.selectUserByLoginId(loginId);
	}

	public int addUser(String loginId, String password, String name, String email, String type) {
		// TODO Auto-generated method stub
		int user = userMapper.insertUser(loginId, password, name, email, type);
		return user;
	}

	public User getUserEntityByLoginIdPassword(String loginId, String password) {
		// TODO Auto-generated method stub
		return userMapper.selectUserByLoginIdPassword(loginId, password);
	}

}
