package com.citrix.training.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.citrix.hibernate.dao.UserDao;
import com.citrix.training.hibernate.entity.User;
import com.citrix.training.service.UserService;
import com.techmahindra.customerapp.web.dtos.DataTablesInputDto;
import com.techmahindra.customerapp.web.dtos.DataTablesListDto;

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

	@Override
	public DataTablesListDto<User> dataTableUserList(
			DataTablesInputDto dataTablesInputDto) {
		int displayLen = dataTablesInputDto.getiDisplayLength();
		int page = dataTablesInputDto.getiDisplayStart();
		int index = page/displayLen;
		String filter = " ";
		String finalQuery = HQL_QUERY;
		String finalCountQuery = COUNT_QUERY;
		if(StringUtils.hasText(dataTablesInputDto.getsSearch())){
			filter = filter + "where firstName like '%"+dataTablesInputDto.getsSearch()+"%'";
			finalQuery += filter;
			finalCountQuery += filter;
		}
		String sortQueryFragment = "";
		switch (dataTablesInputDto.getiSortCol_0()) {
		case 0:
			sortQueryFragment = " order by firstName";
			break;
		case 1:
			sortQueryFragment = " order by lastName";
			break;

		default:
			break;
		}
		
		switch (dataTablesInputDto.getsSortDir_0()) {
		case asc:
			sortQueryFragment += " asc";
			break;
		case desc:
			sortQueryFragment += " desc";
			break;

		default:
			break;
		}
		
		
		
		Long totalRecs = userDao.count(finalCountQuery);
		finalQuery += sortQueryFragment;
		List<User> searchResult=  userDao.executeQuery(finalQuery, index+1, displayLen) ;
		
		DataTablesListDto<User> userListDto = new DataTablesListDto<User>();
		userListDto.setAaData(searchResult);
		userListDto.setiTotalDisplayRecords(totalRecs.intValue());
		userListDto.setiTotalRecords(totalRecs.intValue());
		
		return userListDto;
	}

}
