package com.muxi.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.muxi.bean.Page;
import com.muxi.bean.Post;
import com.muxi.bean.User;
import com.muxi.service.PostService;
import com.muxi.service.impl.PostServiceImpl;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@WebServlet("/PostServlet")
public class PostServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;


	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
		request.setCharacterEncoding("utf-8");
		String method = request.getParameter("method");
		if("toPostListView".equals(method)){
			postList(request,response);
		}else if("PostList".equals(method)){
			getPostList(request,response);
		}else if("DeletePost".equals(method)){
			deletePost(request,response);
		}else if("EditPost".equals(method)){
			editPost(request, response);
		}else if("preSave".equals(method)){
			preSave(request,response);
		}else if("postSave".equals(method)){
			postSave(request,response);
		}else if("showPost".equals(method)){
			postShow(request,response);
		}else if("userPostList".equals(method)){
			userPostList(request,response);
		}
	}
	
	private void userPostList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("user/view/postList.jsp").forward(request, response);		
		
	}

	private void postShow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		int id=Integer.parseInt(request.getParameter("id"));
		PostService postService = new PostServiceImpl();
		Post post = postService.postShow(id);
		postService.pageView(id);
		request.setAttribute("post", post);
		request.setAttribute("mainPage", "/user/view/postShow.jsp");
		request.getRequestDispatcher("user/view/mainTemp.jsp").forward(request, response);
		
	}
	
	private void postSave(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		Integer type_id=Integer.parseInt(request.getParameter("type_id"));
		Integer depart_id=Integer.parseInt(request.getParameter("depart_id"));
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		Post post=new Post();
		post.setTitle(title);
		post.setType_id(type_id);
		post.setDepart_id(depart_id);
		post.setContent(content);
		post.setUser_id(user.getId());
		PostService postService = new PostServiceImpl();
		if(postService.addPost(post))
		{
			request.getRequestDispatcher("MainServlet").forward(request, response);
			
		}else{
			request.setAttribute("error", "±£¥Ê ß∞‹");
			request.setAttribute("mainPage", "/user/view/postSave.jsp");
			request.getRequestDispatcher("user/view/mainTemp.jsp").forward(request, response);
		}
		
	}

	
	private void preSave(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setAttribute("mainPage", "/user/view/postSave.jsp");
			request.getRequestDispatcher("user/view/mainTemp.jsp").forward(request, response);		
		
	}
	
	private void editPost(HttpServletRequest request,HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		Integer id = Integer.parseInt(request.getParameter("id"));
		Integer type_id = Integer.parseInt(request.getParameter("type_id"));
		Integer depart_id = Integer.parseInt(request.getParameter("depart_id"));
		Integer is_overhead = Integer.parseInt(request.getParameter("is_overhead"));
		
		Post post = new Post();
		post.setType_id(type_id);
		post.setDepart_id(depart_id);
		post.setIs_overhead(is_overhead);
		post.setId(id);
		PostService postService = new PostServiceImpl();
		if(postService.editPost(post)){
				response.getWriter().write("success");
			
		}
	}
	
	private void deletePost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
		
		PostService postService = new PostServiceImpl();
		String[] ids = request.getParameterValues("ids[]");
		if(ids==null) {
			ids = request.getParameterValues("id");
		}
		if(postService.deletePost(ids)){
				response.getWriter().write("success");
		}
	}
	
	private void getPostList(HttpServletRequest request,HttpServletResponse response) throws IOException {

		Integer currentPage = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
		Integer pageSize = request.getParameter("rows") == null ? 999 : Integer.parseInt(request.getParameter("rows"));

		String title = request.getParameter("postTitle");
		String content = request.getParameter("postContent");
		int type_id = request.getParameter("type_id") == null ? -1 : Integer.parseInt(request.getParameter("type_id"));
		int depart_id = request.getParameter("depart_id") == null ? -1 : Integer.parseInt(request.getParameter("depart_id"));
		int user_id = request.getParameter("user_id") == null ? -1 : Integer.parseInt(request.getParameter("user_id"));
		
		Post post = new Post();
		post.setTitle(title);
		post.setContent(content);
		post.setType_id(type_id);
		post.setDepart_id(depart_id);
		post.setUser_id(user_id);
		
		PostService postService = new PostServiceImpl();
		List<Post> postList = postService.getPostList(post, new Page(currentPage, pageSize));
		int total = postService.getPostListTotal(post);

		response.setCharacterEncoding("UTF-8");
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("total", total);
		ret.put("rows", postList);
		String from = request.getParameter("from");
		if("combox".equals(from)){
			response.getWriter().write(JSONArray.fromObject(postList).toString());
		}else{
			response.getWriter().write(JSONObject.fromObject(ret).toString());
		}
		
	}

	
	private void postList(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
			request.getRequestDispatcher("admin/view/postList.jsp").forward(request, response);
		
	}
}
