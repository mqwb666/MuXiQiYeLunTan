package com.muxi.bean;

/**
 * 帖子实体类
 */
public class Post {
	private int id; //帖子id
	private String title;	//帖子标题
	private String content;  //帖子内容
	private String publishtime;  //发布时间
	private int type_id;  //类型id
	private int depart_id; //部门id
	private int pageview; //浏览数
	private int user_id; //帖子用户id
	private int is_overhead;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPublishtime() {
		return publishtime;
	}
	public void setPublishtime(String publishtime) {
		this.publishtime = publishtime;
	}
	public int getType_id() {
		return type_id;
	}
	public void setType_id(int type_id) {
		this.type_id = type_id;
	}
	public int getDepart_id() {
		return depart_id;
	}
	public void setDepart_id(int depart_id) {
		this.depart_id = depart_id;
	}
	public int getPageview() {
		return pageview;
	}
	public void setPageview(int pageview) {
		this.pageview = pageview;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getIs_overhead() {
		return is_overhead;
	}
	public void setIs_overhead(int is_overhead) {
		this.is_overhead = is_overhead;
	}
	
	
}
