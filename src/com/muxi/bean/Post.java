package com.muxi.bean;

/**
 * ����ʵ����
 */
public class Post {
	private int id; //����id
	private String title;	//���ӱ���
	private String content;  //��������
	private String publishtime;  //����ʱ��
	private int type_id;  //����id
	private int depart_id; //����id
	private int pageview; //�����
	private int user_id; //�����û�id
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
