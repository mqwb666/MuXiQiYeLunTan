package com.muxi.bean;

/**
 *用户信息实体类
 */
public class User {
	private int id;//用户id
	private String username;//用户名
	private String password;//登录密码
	private int sex;//性别
	private String mobile;//电话号码
	private int depart_id;//所属部门id
	private String mood;//个性签名
	private int is_root;//是否是管理员
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String moblie) {
		this.mobile = moblie;
	}
	public int getDepart_id() {
		return depart_id;
	}
	public void setDepart_id(int depart_id) {
		this.depart_id = depart_id;
	}
	public String getMood() {
		return mood;
	}
	public void setMood(String mood) {
		this.mood = mood;
	}
	public int getIs_root() {
		return is_root;
	}
	public void setIs_root(int is_root) {
		this.is_root = is_root;
	}
	
	
	
}
