package com.muxi.bean;

/**
 * 部门实体类
 */
public class Depart {
	private int id;  //部门id
	private String name;//部门名称
	private String info;//部门信息
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
}
