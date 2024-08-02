package com.novel.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.novel.admin.bo.AdminBO;
import com.novel.user.domain.User;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/admin")
@Controller
public class AdminController {

	@Autowired
	private AdminBO adminBO;
	
	@GetMapping("/admin-view")
	public String AdminView(Model model,
			HttpSession session) {
		
		Integer userId = (Integer) session.getAttribute("userId");
		
		if (userId == null) {
			return "user/signIn";
		}
		
		List<User> AdminViewList = adminBO.generateAdminViewList(userId);
		
		model.addAttribute("AdminViewList", AdminViewList);
		
		return "admin/admin";
	}
	
	@GetMapping("/add-writer-view")
	public String addWriterView() {
		
		return "admin/addWriter";
	}
	
}
