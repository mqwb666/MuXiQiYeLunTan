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

import com.muxi.bean.Type;
import com.muxi.service.TypeService;
import com.muxi.service.impl.TypeServiceImpl;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@WebServlet("/TypeServlet")
public class TypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
		String method = request.getParameter("method");
		if("toTypeListView".equals(method)){
			typeList(request,response);
		}else if("TypeList".equals(method)){
			getTypeList(request,response);
		}else if("DeleteType".equals(method)){
			deleteType(request,response);
		}else if("AddType".equals(method)){
			addType(request, response);
		}else if("EditType".equals(method)){
			editType(request, response);
		}

	}
	private void editType(HttpServletRequest request,HttpServletResponse response) throws IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name"); 
		String info = request.getParameter("info");
		Type type = new Type();
		type.setName(name);
		type.setInfo(info);
		type.setId(id);
		TypeService typeService = new TypeServiceImpl();
		if(typeService.editType(type)){
			response.getWriter().write("success");
		}
	}

	private void addType(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String name = request.getParameter("name"); 
		String info = request.getParameter("info");
		Type type = new Type();
		type.setName(name);
		type.setInfo(info);
		TypeService typeService = new TypeServiceImpl();
		if(typeService.addType(type)){
			response.getWriter().write("success");
		}
		
	}
	
	private void deleteType(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String[] ids = request.getParameterValues("ids[]");
		
		TypeService typeService = new TypeServiceImpl();
		if(typeService.deleteType(ids)){
			response.getWriter().write("success");
		}
	}

	private void getTypeList(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String name = request.getParameter("typeName");
		Integer currentPage = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
		Integer pageSize = request.getParameter("rows") == null ? 999 : Integer.parseInt(request.getParameter("rows"));

		Type type = new Type();
		type.setName(name);
		
		TypeService typeService = new TypeServiceImpl();
		List<Type> typeList = typeService.getTypeList(name, currentPage, pageSize);
		int total = typeService.getTypeListTotal(name);

		response.setCharacterEncoding("UTF-8");
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("total", total);
		ret.put("rows", typeList);
		String from = request.getParameter("from");
		if("combox".equals(from)){
			response.getWriter().write(JSONArray.fromObject(typeList).toString());
		}else{
			response.getWriter().write(JSONObject.fromObject(ret).toString());
		}
	}

	
	private void typeList(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
		request.getRequestDispatcher("admin/view/typeList.jsp").forward(request, response);
	}
}
