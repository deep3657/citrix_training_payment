package com.citrix.training.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.citrix.training.hibernate.entity.User;
import com.citrix.training.service.UserService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(value = "api/user")
@Api(description="Api to operate on user resource.",value = "/user")
public class UserApiController {
	
	@Autowired
	private UserService userService;
	
	@ApiOperation(value="List all the users.")
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<User> listUser() {
		return userService.list();
	}
	
	@ApiOperation(value="Get a user.")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public User listUser(@PathVariable Long id) {
		return userService.get(id);
	}
	
	@ApiOperation(value="Update a user.")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public User updateUser(@PathVariable Long id,@RequestBody User user) {
		user.setId(id);
		User updatedUser = userService.update(user);
		return updatedUser;
	}
	
	@ApiOperation(value="Create a user.")
	@RequestMapping(value = "/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public User saveUser(@RequestBody User user) {
		User savedUser = userService.save(user);
		return savedUser;
	}
	
	@ApiOperation(value="Get a user.")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ResponseStatus(value=HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		 userService.delete(id);
	}

}