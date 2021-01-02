package com.ecommerceJee.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.ecommerceJee.demo.model.User;

@Controller
public class LoginController
{
	@RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
	public String login(Model m) {
		m.addAttribute("userVo", new User());
		return "login"; //page html !!!!!!!!!!!
	}
	
	@RequestMapping(value = { "/index"}, method = RequestMethod.GET)
	public String index(Model m) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		m.addAttribute("userName",  auth.getName());
		m.addAttribute("list",auth.getAuthorities());
		return "index"; //page html !!!!!!!!!!!
	}
	
	@RequestMapping(value = { "/access-denied" }, method = RequestMethod.GET)
	public String denied(Model m) {
		m.addAttribute("userVo", new User());
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		m.addAttribute("userName", "Ur logged as : " + auth.getName());


		return "acess-denied"; //page html !!!!!!!!!!!
	}
}
