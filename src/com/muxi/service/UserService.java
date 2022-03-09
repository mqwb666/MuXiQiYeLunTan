package com.muxi.service;

import java.util.List;

import com.muxi.bean.Page;
import com.muxi.bean.User;

public interface UserService {
    /**
     * ×¢²áÓÃ»§
     * @param user
     * @return
     */
	public boolean regist(User user);
    public User login(String username,String password);
    public boolean modify(User user,String newPassword);
    public List<User> getUserList(User user,Page page);
    public int getUserListTotal(User user);
    public boolean deleteUser(String[] ids);
	public boolean editUser(User user);
}
