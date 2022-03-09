package com.muxi.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.muxi.bean.Depart;
import com.muxi.bean.Post;
import com.muxi.bean.Type;
import com.muxi.service.DepartService;
import com.muxi.service.PostService;
import com.muxi.service.TypeService;
import com.muxi.service.impl.DepartServiceImpl;
import com.muxi.service.impl.PostServiceImpl;
import com.muxi.service.impl.TypeServiceImpl;

@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession();
		
		TypeService typeService = new TypeServiceImpl();
		List<Type> typeList = typeService.getTypeList(null, 1, 999);
		session.setAttribute("typeList", typeList);
		
		DepartService departService = new DepartServiceImpl();
		List<Depart> departList = departService.getDepartList(null, 1, 999);
		session.setAttribute("departList", departList);
		
		//  公告栏
		PostService postService = new PostServiceImpl();
		List<Post> overheadList = postService.getOverhead();
		session.setAttribute("overheadList", overheadList);
		
		//  最新帖子
		List<Map<String, Object>> newList = new ArrayList<Map<String, Object>>();
		session.setAttribute("newList", newList);
		
		//  最火帖子
		List<Post> hotPostList= postService.getHotPost();
		session.setAttribute("hotPostList", hotPostList);
		
		request.setAttribute("mainPage", "/user/view/main.jsp");
		request.getRequestDispatcher("user/view/mainTemp.jsp").forward(request, response);
	}
	
}
