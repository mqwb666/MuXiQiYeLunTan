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

import com.muxi.bean.Depart;
import com.muxi.service.DepartService;
import com.muxi.service.impl.DepartServiceImpl;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@WebServlet("/DepartServlet")
public class DepartServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;


	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
		String method = request.getParameter("method");
		if("toDepartListView".equals(method)){
			departList(request,response);
		}else if("DepartList".equals(method)){
			getDepartList(request,response);
		}else if("DeleteDepart".equals(method)){
			deleteDepart(request,response);
		}else if("AddDepart".equals(method)){
			addDepart(request, response);
		}else if("EditDepart".equals(method)){
			editDepart(request, response);
		}

	}
	private void editDepart(HttpServletRequest request,HttpServletResponse response) throws IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name"); 
		String info = request.getParameter("info");
		Depart depart = new Depart();
		depart.setName(name);
		depart.setInfo(info);
		depart.setId(id);
		DepartService departService = new DepartServiceImpl();
		if(departService.editDepart(depart)){
				response.getWriter().write("success");
			
		}
	}

	private void addDepart(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String name = request.getParameter("name"); 
		String info = request.getParameter("info");
		Depart depart = new Depart();
		depart.setName(name);
		depart.setInfo(info);
		DepartService departService = new DepartServiceImpl();
		if(departService.addDepart(depart)){
			response.getWriter().write("success");
			
		}
		
	}
	
	private void deleteDepart(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String[] ids = request.getParameterValues("ids[]");
		
		DepartService departService = new DepartServiceImpl();
		if(departService.deleteDepart(ids)){
			response.getWriter().write("success");
		}
	}

	private void getDepartList(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String name = request.getParameter("departName");
		Integer currentPage = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
		Integer pageSize = request.getParameter("rows") == null ? 999 : Integer.parseInt(request.getParameter("rows"));

		DepartService departService = new DepartServiceImpl();
		List<Depart> departList = departService.getDepartList(name, currentPage, pageSize);
		int total = departService.getDepartListTotal(name);

		response.setCharacterEncoding("UTF-8");
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("total", total);
		ret.put("rows", departList);
		String from = request.getParameter("from");
		if("combox".equals(from)){
			response.getWriter().write(JSONArray.fromObject(departList).toString());
		}else{
			response.getWriter().write(JSONObject.fromObject(ret).toString());
		}
		
	}

	
	private void departList(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
		request.getRequestDispatcher("admin/view/departList.jsp").forward(request, response);
	}
}
