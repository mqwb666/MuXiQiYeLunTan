package com.muxi.service;

import java.util.List;

import com.muxi.bean.Depart;

public interface DepartService {
	public List<Depart> getDepartList(String name,int currentPage,int pageSize);
	public int getDepartListTotal(String name);
	public boolean deleteDepart(String[] ids);
	public boolean addDepart(Depart depart);
	public boolean editDepart(Depart depart);
}
