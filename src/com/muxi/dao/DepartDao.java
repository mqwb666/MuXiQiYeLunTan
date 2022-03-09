package com.muxi.dao;

import java.util.List;

import com.muxi.bean.Depart;
import com.muxi.bean.Page;

public interface DepartDao {

	public List<Depart> getDepartList(Depart depart,Page page);
	public int getDepartListTotal(Depart depart);
	public boolean deleteDepart(String[] ids);
	public boolean addDepart(Depart depart) ;
	public boolean editDepart(Depart depart);
}
