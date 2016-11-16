package com.bit2016.jblog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bit2016.jblog.DTO.JSONResult;
import com.bit2016.jblog.DTO.SingletonClass;
import com.bit2016.jblog.service.JuserService;

@Controller
@RequestMapping("/user/api")
public class JUserApiController {

	@Autowired
	private JuserService userService;

	@ResponseBody
	@RequestMapping("/chkid")
	public JSONResult checkEmail(@RequestParam(value = "id", required = true, defaultValue = "") String id) {
		boolean result = userService.idExist(id);

		SingletonClass sc = SingletonClass.getInstance();
		return JSONResult.success(result ? "exist" : "not exist");
	}
}
