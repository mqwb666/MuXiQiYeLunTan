package com.muxi.dao;

import java.util.List;

import com.muxi.bean.Page;
import com.muxi.bean.Post;

public interface PostDao {
	public List<Post> getPostList(Post post,Page page);
	public int getPostListTotal(Post post);
	public boolean deletePost(String[] ids);
	public boolean editPost(Post post);
	public List<Post> getOverhead();
	public List<Post> getHotPost();
	public boolean addPost(Post post);
	public Post postShow(int id);
	public boolean postView(int id);
}
