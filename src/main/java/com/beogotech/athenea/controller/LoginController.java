package com.beogotech.athenea.controller;

import com.beogotech.athenea.util.AtheneaUtil;
import com.beogotech.athenea.util.LoggerFactoryUtil;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
	
	private static Logger LOG = LoggerFactoryUtil.getLog(LoginController.class);
	
	@GetMapping(value = {"/", "/login"})
	public String showLoginPage(Model model){
		model.addAttribute("year", AtheneaUtil.COPYRIGHT_YEAR_INT);
		return AtheneaUtil.LOGIN_PAGE_URI;
	}
	
	@PostMapping(value = "/login")
	public String logUserIn(Model model){
		model.addAttribute("year", AtheneaUtil.COPYRIGHT_YEAR_INT);
		model.addAttribute("page-title", AtheneaUtil.COPYRIGHT_YEAR_INT);
		return "redirect:dashboard";
	}
}
