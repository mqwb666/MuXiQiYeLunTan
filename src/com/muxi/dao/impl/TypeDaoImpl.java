package com.muxi.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.muxi.bean.Depart;
import com.muxi.bean.Page;
import com.muxi.bean.Type;
import com.muxi.dao.TypeDao;
import com.muxi.util.JDBCUtils;
import com.muxi.util.StringUtil;

public class TypeDaoImpl implements TypeDao{
	JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
	
	public List<Type> getTypeList(Type type,Page page){
		List<Type> typeList = null;
		List ret = new ArrayList();
		
		String sql = "select * from type where 1 = 1 ";
		if(!StringUtil.isEmpty(type.getName())){
			sql += "and name like ?";
			ret.add("%"+type.getName()+"%");
			
		}
		sql += " limit ?,?";
		ret.add(page.getStart());
		ret.add(page.getPageSize());
		
		typeList = template.query(sql, new BeanPropertyRowMapper<Type>(Type.class),ret.toArray());
		
		return typeList;
	}
	
	public int getTypeListTotal(Type type){
		int total = 0;
		List ret = new ArrayList();
		String sql = "select count(*)as total from type where 1 = 1 ";
		if(!StringUtil.isEmpty(type.getName())){
			sql += "and name like ?";
			ret.add("%"+type.getName()+"%");
			
		}
		total = template.queryForObject(sql,int.class,ret.toArray());
		
		return total;
	}
	
	public boolean deleteType(String[] ids) {
		// TODO Auto-generated method stub
		String sql = "delete from type where 1=0 ";
		for (String id : ids) {
			sql += " or id = ? ";
		}
		int update = template.update(sql,ids);
		return update>0;
	}

	@Override
	public boolean addType(Type type) {
		String sql = "insert into type values(null,?,?) ";
		int update = template.update(sql,type.getName(),type.getInfo());
		return update>0;
	}

	@Override
	public boolean editType(Type type) {
		String sql = "update type set name = ?,info = ? where id = ?";
		int update = template.update(sql,type.getName(),type.getInfo(),type.getId());
		return update>0;

		
	}
}
