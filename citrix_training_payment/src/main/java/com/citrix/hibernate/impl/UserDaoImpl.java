package com.citrix.hibernate.impl;


import org.springframework.stereotype.Repository;

import com.citrix.hibernate.DAO.HibernateDao;
import com.citrix.hibernate.DAO.UserDao;
import com.citrix.training.hibernate.entity.User;

@Repository("userDao")
public class UserDaoImpl extends HibernateDao<User, Long> implements UserDao {

}

