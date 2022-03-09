package com.muxi.service.impl;

import java.util.List;

import com.muxi.bean.Page;
import com.muxi.bean.Post;
import com.muxi.dao.PostDao;
import com.muxi.dao.impl.PostDaoImpl;
import com.muxi.service.PostService;

public class PostServiceImpl implements PostService {

	PostDao postDao = new PostDaoImpl();
	@Override
	public List<Post> getPostList(Post post, Page page) {
		return postDao.getPostList(post, page);
	}

	@Override
	public int getPostListTotal(Post post) {
		return postDao.getPostListTotal(post);
	}

	@Override
	public boolean deletePost(String[] ids) {
		return postDao.deletePost(ids);
	}

	@Override
	public boolean editPost(Post post) {
		return postDao.editPost(post);
	}

	@Override
	public List<Post> getOverhead() {
		return postDao.getOverhead();
		
	}

	@Override
	public List<Post> getHotPost() {
		return postDao.getHotPost();
	}

	@Override
	public boolean addPost(Post post) {
		return postDao.addPost(post);
	}

	@Override
	public Post postShow(int id) {
		return postDao.postShow(id);
	}

	@Override
	public boolean pageView(int id) {
		return postDao.postView(id);
	}

}
