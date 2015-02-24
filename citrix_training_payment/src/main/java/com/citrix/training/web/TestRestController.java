package com.citrix.training.web;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.citrix.training.hibernate.entity.User;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(value = "api/user")
@Api(description="Api to operate on user resource.",value = "/user")
public class TestRestController {

	@ApiOperation(value="List all the users.")
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public User listUser() {
		User user = new User();
		user.setFirstName("shiv");
		user.setLastName("khillar");
		return user;
	}

}
