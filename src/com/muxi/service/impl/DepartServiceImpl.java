package com.muxi.service.impl;

import java.util.List;

import com.muxi.bean.Depart;
import com.muxi.bean.Page;
import com.muxi.dao.DepartDao;
import com.muxi.dao.impl.DepartDaoImpl;
import com.muxi.service.DepartService;

public class DepartServiceImpl implements DepartService {

	DepartDao departDao = new DepartDaoImpl();
	@Override
	public List<Depart> getDepartList(String name, int currentPage, int pageSize) {	
		Page page = new Page(currentPage,pageSize);
		Depart depart = new Depart();
		depart.setName(name);
		return departDao.getDepartList(depart, page);
	}

	@Override
	public int getDepartListTotal(String name) {	
		Depart depart = new Depart();
		depart.setName(name);
		return departDao.getDepartListTotal(depart);
	}

	@Override
	public boolean deleteDepart(String[] ids) {
		return departDao.deleteDepart(ids);
	}

	@Override
	public boolean addDepart(Depart depart) {
		return departDao.addDepart(depart);
	}

	@Override
	public boolean editDepart(Depart depart) {
		return departDao.editDepart(depart);
	}





}
