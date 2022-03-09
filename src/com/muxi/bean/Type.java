package com.muxi.bean;

/**
 * 帖子类别实体类
 */
public class Type {
	private int id;//类别id
	private String name;//类别名称
	private String info;//类别信息
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
