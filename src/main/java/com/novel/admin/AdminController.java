package com.novel.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin")
@Controller
public class AdminController {

	@GetMapping("/admin-view")
	public String AdminView() {
		
		return "admin/admin";
	}
	
	@GetMapping("/add-writer-view")
	public String addWriterView() {
		
		return "admin/addWriter";
	}
	
}
