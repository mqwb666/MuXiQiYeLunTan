package com.muxi.service.impl;

import java.util.List;

import com.muxi.bean.Type;
import com.muxi.bean.Page;
import com.muxi.dao.TypeDao;
import com.muxi.dao.impl.TypeDaoImpl;
import com.muxi.service.TypeService;

public class TypeServiceImpl implements TypeService {

	TypeDao typeDao = new TypeDaoImpl();
	@Override
	public List<Type> getTypeList(String name, int currentPage, int pageSize) {
		
		Page page = new Page(currentPage,pageSize);
		Type type = new Type();
		type.setName(name);
		
		return typeDao.getTypeList(type, page);
	}

	@Override
	public int getTypeListTotal(String name) {
		
		Type type = new Type();
		type.setName(name);
		 
		return typeDao.getTypeListTotal(type);
	}

	@Override
	public boolean deleteType(String[] ids) {
		return typeDao.deleteType(ids);
	}

	@Override
	public boolean addType(Type type) {
		return typeDao.addType(type);
	}

	@Override
	public boolean editType(Type type) {
		return typeDao.editType(type);
	}

}
