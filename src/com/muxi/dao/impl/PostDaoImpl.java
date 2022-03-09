package com.muxi.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.muxi.bean.Page;
import com.muxi.bean.Post;
import com.muxi.dao.PostDao;
import com.muxi.util.JDBCUtils;
import com.muxi.util.StringUtil;

public class PostDaoImpl implements PostDao{
	JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
	
	public List<Post> getPostList(Post post,Page page){
		List<Post> postList = null;
		List ret = new ArrayList();
		
		String sql = "select * from post where 1 = 1 ";
		if(!StringUtil.isEmpty(post.getTitle())){
			sql += "and title like ?";
			ret.add("%"+post.getTitle()+"%");
			
		}
		if(!StringUtil.isEmpty(post.getContent())){
			sql += "and content like ?";
			ret.add("%"+post.getContent()+"%");
			
		}
		if(post.getType_id() != -1){
			sql += " and type_id = ?";
			ret.add(post.getType_id()+"");
		}
		if(post.getDepart_id() != -1){
			sql += " and depart_id = ?";
			ret.add(post.getDepart_id()+"");
		}
		if(post.getUser_id() != -1){
			sql += " and user_id = ?";
			ret.add(post.getUser_id()+"");
		}
		sql += " limit ?,?";
		ret.add(page.getStart());
		ret.add(page.getPageSize());
		
		postList = template.query(sql, new BeanPropertyRowMapper<Post>(Post.class),ret.toArray());
		
		return postList;
	}
	
	public int getPostListTotal(Post post){
		int total = 0;
		List ret = new ArrayList();
		String sql = "select count(*)as total from post where 1 = 1 ";
		if(!StringUtil.isEmpty(post.getTitle())){
			sql += "and title like ?";
			ret.add("%"+post.getTitle()+"%");
			
		}
		if(!StringUtil.isEmpty(post.getContent())){
			sql += "and content like ?";
			ret.add("%"+post.getContent()+"%");
			
		}
		if(post.getType_id() != -1){
			sql += " and type_id = ?";
			ret.add(post.getType_id()+"");
		}
		if(post.getDepart_id() != -1){
			sql += " and depart_id = ?";
			ret.add(post.getDepart_id()+"");
		}
		if(post.getUser_id() != -1){
			sql += " and user_id = ?";
			ret.add(post.getUser_id()+"");
		}
		total = template.queryForObject(sql,int.class,ret.toArray());
		
		return total;
	}

	@Override
	public boolean deletePost(String[] ids) {
		String sql = "delete from post where 1=0 ";
		for (String id : ids) {
			sql += " or id = ? ";
		}
		int update = template.update(sql,ids);
		return update>0;
	}

	@Override
	public boolean editPost(Post post) {
		String sql = "update post set type_id = ?,depart_id = ?,is_overhead = ? where id = ?";
		int update = template.update(sql,post.getType_id(),post.getDepart_id(),post.getIs_overhead(),post.getId());
		return update>0;
	}

	@Override
	public List<Post> getOverhead() {
		String sql = "SELECT * FROM post WHERE is_overhead=1 ORDER BY publishTime DESC LIMIT 0,5";
		List<Post> overList = template.query(sql, new BeanPropertyRowMapper<Post>(Post.class));
		return overList;
	}

	@Override
	public List<Post> getHotPost() {
		String sql = "SELECT * FROM post ORDER BY pageview DESC LIMIT 0,5";
		List<Post> hotPostList = template.query(sql, new BeanPropertyRowMapper<Post>(Post.class));
		return hotPostList;
	}

	@Override
	public boolean addPost(Post post) {
		String sql="insert into post values(null,?,?,now(),?,?,0,?,0)";
		int update = template.update(sql,post.getTitle(),post.getContent(),post.getType_id(),post.getDepart_id(),post.getUser_id());
		return update>0;
	}

	@Override
	public Post postShow(int id) {
		String sql="select * from post where id = ?";
		Post post = template.queryForObject(sql, new BeanPropertyRowMapper<Post>(Post.class),id);
		return post;
	}

	@Override
	public boolean postView(int id) {
		String sql = "update post set pageview = pageview+1 where id = ?";
		int update = template.update(sql,id);
		return update>0;
	}
	
}
