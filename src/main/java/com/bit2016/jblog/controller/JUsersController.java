package com.bit2016.jblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bit2016.jblog.service.JuserService;
import com.bit2016.jblog.vo.Jusers;
import com.bit2016.security.Auth;

@Controller
@RequestMapping("/user")
public class JUsersController {

	@Autowired
	private JuserService juserSerivce;

	@RequestMapping("/loginform")
	public String loginform() {
		return "user/login";
	}

	@RequestMapping("/login")
	public String login(@ModelAttribute Jusers vo, Model model) {

		return "redirect:/";
	}

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join(@ModelAttribute Jusers jusers) {
		return "user/join";
	}

	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(@ModelAttribute Jusers vo, Model model) {
		Long no = juserSerivce.join(vo);
		vo.setNo(no);
		juserSerivce.insert(vo);
		return "user/joinsuccess";
	}

	@Auth
	@RequestMapping("/logout")
	public String logout() {
		return "main/index";
	}

}
