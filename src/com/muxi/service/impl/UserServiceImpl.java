package com.muxi.service.impl;

import java.util.List;

import com.muxi.bean.Page;
import com.muxi.bean.User;
import com.muxi.dao.UserDao;
import com.muxi.dao.impl.UserDaoImpl;
import com.muxi.service.UserService;

public class UserServiceImpl implements UserService {
	
	UserDao userDao = new UserDaoImpl();
	
	@Override
	public boolean regist(User user) {
		return false;
	}

	@Override
	public User login(String username,String password) {
		return userDao.findByUsernameAndPassword(username, password);
	}

	@Override
	public boolean modify(User user, String newPassword) {
		user.setPassword(newPassword);
		return userDao.update(user);
	}

	@Override
	public List<User> getUserList(User user, Page page) {
		return userDao.getUserList(user, page);
	}

	@Override
	public int getUserListTotal(User user) {
		return userDao.getUserListTotal(user);
	}

	@Override
	public boolean deleteUser(String[] ids) {
		return userDao.deleteUser(ids);
	}

	@Override
	public boolean editUser(User user) {
		return userDao.editUser(user);
	}

}
