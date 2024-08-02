package com.novel.admin.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novel.follow.bo.FollowBO;
import com.novel.user.bo.UserBO;
import com.novel.user.domain.User;

@Service
public class AdminBO {
	
	@Autowired
	private UserBO userBO;
	
	@Autowired
	private FollowBO followBO;
	
	public List<User> generateAdminViewList(Integer userId) {
		
		return userBO.getUserJoinFollow(userId);
	}

	public User getUserByLoginIdEmail(String loginId, String email) {

		return userBO.getUserByLoginIdEmail(loginId, email);
	}

	public int insertFollow(int editerUserId, int writerUserId) {

		return followBO.insertFollow(editerUserId, writerUserId);

	}
		

}
