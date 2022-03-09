package com.muxi.servlet;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.muxi.bean.User;
import com.muxi.service.UserService;
import com.muxi.service.impl.UserServiceImpl;
import com.muxi.util.StringUtil;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {


	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		doPost(request, response);
	}
	

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String method = request.getParameter("method");
		if ("logout".equals(method)) {
			logout(request, response);
			return;
		}
		String vcode = request.getParameter("vcode");
		String name = request.getParameter("account");
		String password = request.getParameter("password");
		String loginCpacha = request.getSession().getAttribute("loginCapcha").toString();
		if (StringUtil.isEmpty(vcode)) {
			response.getWriter().write("vcodeError");
			return;
		}
		if (!vcode.toUpperCase().equals(loginCpacha.toUpperCase())) {
			response.getWriter().write("vcodeError");
			return;
		}
		
		String loginStatus = "loginFaild";

		UserService userService = new UserServiceImpl();
		User user = userService.login(name, password);

		if (user == null) {
			response.getWriter().write("loginError");
			return;
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
		if (user.getIs_root()==1) {
			loginStatus = "loginSuccess_root";
		}else {
			loginStatus = "loginSuccess_user";
		}
		response.getWriter().write(loginStatus);
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.getSession().removeAttribute("user");
		response.sendRedirect("index.jsp");
	}
}
