package com.muxi.dao;

import java.util.List;

import com.muxi.bean.Type;
import com.muxi.bean.Page;

public interface TypeDao {
	public List<Type> getTypeList(Type type,Page page);
	public int getTypeListTotal(Type type);
	public boolean deleteType(String[] ids);
	public boolean addType(Type type) ;
	public boolean editType(Type type);

}
