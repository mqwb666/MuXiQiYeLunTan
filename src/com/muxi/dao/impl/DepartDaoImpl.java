package com.muxi.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.muxi.bean.Page;
import com.muxi.bean.Depart;
import com.muxi.dao.DepartDao;
import com.muxi.util.JDBCUtils;
import com.muxi.util.StringUtil;

public class DepartDaoImpl implements DepartDao{
	JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
	
	public List<Depart> getDepartList(Depart depart,Page page){
		List<Depart> departList = null;
		List ret = new ArrayList();
		
		String sql = "select * from depart where 1 = 1 ";
		if(!StringUtil.isEmpty(depart.getName())){
			sql += "and name like ?";
			ret.add("%"+depart.getName()+"%");
			
		}
		sql += " limit ?,?";
		ret.add(page.getStart());
		ret.add(page.getPageSize());
		
		departList = template.query(sql, new BeanPropertyRowMapper<Depart>(Depart.class),ret.toArray());
		
		return departList;
	}
	
	public int getDepartListTotal(Depart depart){
		int total = 0;
		List ret = new ArrayList();
		String sql = "select count(*)as total from depart where 1 = 1 ";
		if(!StringUtil.isEmpty(depart.getName())){
			sql += "and name like ?";
			ret.add("%"+depart.getName()+"%");
			
		}
		total = template.queryForObject(sql,int.class,ret.toArray());
		
		return total;
	}
	public boolean deleteDepart(String[] ids) {
		// TODO Auto-generated method stub
		String sql = "delete from depart where 1=0 ";
		for (String id : ids) {
			sql += " or id = ? ";
		}
		int update = template.update(sql,ids);
		return update>0;
	}

	@Override
	public boolean addDepart(Depart depart) {
		String sql = "insert into depart values(null,?,?) ";
		int update = template.update(sql,depart.getName(),depart.getInfo());
		return update>0;
	}

	@Override
	public boolean editDepart(Depart depart) {
		String sql = "update depart set name = ?,info = ? where id = ?";
		int update = template.update(sql,depart.getName(),depart.getInfo(),depart.getId());
		return update>0;

		
	}

	
}
