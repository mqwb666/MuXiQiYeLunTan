package com.muxi.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.muxi.bean.User;
import com.muxi.service.UserService;
import com.muxi.service.impl.UserServiceImpl;

@WebServlet("/SystemServlet")
public class SystemServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String method = request.getParameter("method");
		if ("toPersonalView".equals(method)) {
			personalView(request, response);
			return;
		}else if ("EditPasswod".equals(method)) {
			editPassword(request, response);
			return;
		}
		request.getRequestDispatcher("/admin/view/system.jsp").forward(request, response);
		
	}

	private void editPassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String password = request.getParameter("password");
		String newPassword = request.getParameter("newpassword");
		response.setCharacterEncoding("UTF-8");

		// 管理员
		User user = (User) request.getSession().getAttribute("user");
		if (!user.getPassword().equals(password)) {
			response.getWriter().write("原密码错误！");
			return;
		}
		UserService userDao = new UserServiceImpl();
		if (userDao.modify(user, newPassword)) {
			response.getWriter().write("success");
		} else {
			response.getWriter().write("数据库修改错误");
		}

	}

	private void personalView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("admin/view/personalView.jsp").forward(request, response);
	}
}
