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
import com.muxi.bean.User;
import com.muxi.service.UserService;
import com.muxi.service.impl.UserServiceImpl;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
		String method = request.getParameter("method");
		if("toUserListView".equals(method)){
			userList(request,response);
		}else if("UserList".equals(method)){
			getUserList(request,response);
		}else if("DeleteUser".equals(method)){
			deleteUser(request,response);
		}else if("EditUser".equals(method)){
			editUser(request, response);
		}else if("preSave".equals(method)){
			preSave(request,response);
		}else if("userShow".equals(method)){
			userShow(request,response);
		}
	}
	
	
	private void userShow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("user/view/userShow.jsp").forward(request, response);		
	}

	private void preSave(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("mainPage", "/user/view/userSave.jsp");
		request.getRequestDispatcher("user/view/mainTemp.jsp").forward(request, response);		
	}
	
	private void editUser(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
		String action = request.getParameter("action");
		User user=new User();
		
 		Integer id = request.getParameter("id")==null?-1:Integer.parseInt(request.getParameter("id"));
		String username = request.getParameter("username");
		String mobile = request.getParameter("mobile");
		String mood = request.getParameter("mood");
		Integer is_root = request.getParameter("is_root")==null?-1:Integer.parseInt(request.getParameter("is_root"));
		
		user.setId(id);
		user.setUsername(username);
		user.setMobile(mobile);
		user.setMood(mood);
		user.setIs_root(is_root);
		
		UserService usertService = new UserServiceImpl();
		if(usertService.editUser(user)){
			if("user".equals(action)) {
				HttpSession session = request.getSession();
				session.setAttribute("user",user);
				preSave(request, response);
			}
			response.getWriter().write("success");	
		}
	}
	
	private void deleteUser(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String[] ids = request.getParameterValues("ids[]");
		
		UserService userService = new UserServiceImpl();
		if(userService.deleteUser(ids)){
			response.getWriter().write("success");
		}
	}

	private void getUserList(HttpServletRequest request,HttpServletResponse response) throws IOException {
		Integer currentPage = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
		Integer pageSize = request.getParameter("rows") == null ? 999 : Integer.parseInt(request.getParameter("rows"));

		String name = request.getParameter("username");
		int depart_id = request.getParameter("depart_id") == null ? -1 : Integer.parseInt(request.getParameter("depart_id"));

		User user = new User();
		user.setUsername(name);
		user.setDepart_id(depart_id);
		
		UserService userService = new UserServiceImpl();
		List<User> userList = userService.getUserList(user, new Page(currentPage, pageSize));
		int total = userService.getUserListTotal(user);

		response.setCharacterEncoding("UTF-8");
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("total", total);
		ret.put("rows", userList);
		String from = request.getParameter("from");
		if("combox".equals(from)){
			response.getWriter().write(JSONArray.fromObject(userList).toString());
		}else{
			response.getWriter().write(JSONObject.fromObject(ret).toString());
		}
	}

	
	private void userList(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
		request.getRequestDispatcher("admin/view/userList.jsp").forward(request, response);
	}
}
