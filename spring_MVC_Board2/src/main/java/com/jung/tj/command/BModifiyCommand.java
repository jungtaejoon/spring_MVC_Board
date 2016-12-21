package com.jung.tj.command;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.jung.tj.dao.BDao;

public class BModifiyCommand implements BCommand {

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		map = new HashMap<String, Object>();
		Enumeration<String> e = request.getParameterNames();
		while(e.hasMoreElements()) {
			String name = e.nextElement();
			map.put(name, request.getParameter(name));
		}
		
		BDao dao = new BDao();
		dao.modify(map);
		
	}
	

}

