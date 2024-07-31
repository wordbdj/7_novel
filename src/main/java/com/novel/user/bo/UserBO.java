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

}
