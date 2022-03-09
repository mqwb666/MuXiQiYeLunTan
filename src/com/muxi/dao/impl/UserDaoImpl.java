package com.muxi.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.muxi.bean.Page;
import com.muxi.bean.User;
import com.muxi.bean.User;
import com.muxi.dao.UserDao;
import com.muxi.util.JDBCUtils;
import com.muxi.util.StringUtil;

public class UserDaoImpl implements UserDao {

	private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

	@Override
	public User findByUsername(String username) {
		User user = null;
		try {
			// 1.∂®“Âsql
			String sql = "select * from user where username = ?";
			// 2.÷¥––sql
			user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username);
		} catch (Exception e) {

		}

		return user;
	}

	@Override
	public void save(User user) {

	}

	@Override
	public boolean update(User user) {
		String sql = "update user set password = ? where id = ?";
		return template.update(sql, user.getPassword(),user.getId()) > 0;

	}

	@Override
	public User findByUsernameAndPassword(String username, String password) {
		User user = null;
		try {
			String sql = "select * from user where username = ? and password = ? ";
			user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username, password);
		} catch (Exception e) {

		}
		return user;
	}
	
	
	
	public List<User> getUserList(User user,Page page){
		List<User> userList = null;
		List ret = new ArrayList();
		
		String sql = "select * from user where 1 = 1 ";
		if(!StringUtil.isEmpty(user.getUsername())){
			sql += "and username like ?";
			ret.add("%"+user.getUsername()+"%");
			
		}
		
		if(user.getDepart_id() != -1){
			sql += " and depart_id = ?";
			ret.add(user.getDepart_id()+"");
		}
		sql += " limit ?,?";
		ret.add(page.getStart());
		ret.add(page.getPageSize());
		
		userList = template.query(sql, new BeanPropertyRowMapper<User>(User.class),ret.toArray());
		
		return userList;
	}
	
	public int getUserListTotal(User user){
		int total = 0;
		List ret = new ArrayList();
		String sql = "select count(*)as total from user where 1 = 1 ";
		if(!StringUtil.isEmpty(user.getUsername())){
			sql += "and username like ?";
			ret.add("%"+user.getUsername()+"%");
			
		}
		
		if(user.getDepart_id() != -1){
			sql += " and depart_id = ?";
			ret.add(user.getDepart_id()+"");
		}
		total = template.queryForObject(sql,int.class,ret.toArray());
		
		return total;
	}

	@Override
	public boolean deleteUser(String[] ids) {
		String sql = "delete from user where 1=0 ";
		for (String id : ids) {
			sql += " or id = ? ";
		}
		int update = template.update(sql,ids);
		return update>0;
	}

	@Override
	public boolean editUser(User user) {
				
		List<User> userList = null;
		List ret = new ArrayList();
		
		
		String sql = "update user set ";
		if(!StringUtil.isEmpty(user.getUsername())){
			
			sql += "username = ? ,";
			ret.add(user.getUsername());
			
		}
		
		if(!StringUtil.isEmpty(user.getMobile())){
			sql += "mobile = ? ,";
			ret.add(user.getMobile());
			
		}
		if(!StringUtil.isEmpty(user.getMood())){
			sql += "mood = ? ,";
			ret.add(user.getMood());
			
		}
		if(user.getIs_root()!=-1){
			sql += "is_root = ? ,";
			ret.add(user.getIs_root());
			
		}
		sql = sql.substring(0, sql.length()-1);
		sql += " where id = ?";
		ret.add(user.getId());
		
		
		int update = template.update(sql,ret.toArray());
		return update>0;
	}
}
