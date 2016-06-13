package com.citrix.training.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citrix.hibernate.dao.UserDao;
import com.citrix.training.hibernate.entity.User;
import com.citrix.training.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	private static final String COUNT_QUERY = "select count(*) from User";
	
	private static final String HQL_QUERY = " select u from User u";
	
	@Autowired
	private UserDao userDao;

	@Override
	public User save(User user) {
		userDao.add(user);
		return user;
	}

	@Override
	public User update(User user) {
		User savedUser= userDao.find(user.getId());
		savedUser.setFirstName(user.getFirstName());
		savedUser.setLastName(user.getLastName());
		userDao.update(savedUser);
		return savedUser;
	}

	@Override
	public void delete(Long id) {
		User user= userDao.find(id);
		userDao.remove(user);
	}

	@Override
	public User get(Long id) {
		User user= userDao.find(id);
		return user;
	}

	@Override
	public List<User> list() {
		return userDao.list();
	}

}
