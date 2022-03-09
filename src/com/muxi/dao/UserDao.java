package com.muxi.dao;

import java.util.List;

import com.muxi.bean.Page;
import com.muxi.bean.User;

public interface UserDao {

    public User findByUsername(String username);
    public void save(User user);
    public boolean update(User user);
    public User findByUsernameAndPassword(String username, String password);
    public List<User> getUserList(User user,Page page);
    public int getUserListTotal(User user);
	public boolean deleteUser(String[] ids);
	public boolean editUser(User user);
}
