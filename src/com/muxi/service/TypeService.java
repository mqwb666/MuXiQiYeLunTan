package com.muxi.service;

import java.util.List;

import com.muxi.bean.Type;

public interface TypeService {
	public List<Type> getTypeList(String name,int currentPage,int pageSize);
	public int getTypeListTotal(String name);
	public boolean deleteType(String[] ids);
	public boolean addType(Type type);
	public boolean editType(Type type);
}
