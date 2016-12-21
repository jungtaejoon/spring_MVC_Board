package com.jung.tj.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jung.tj.command.BCommand;
import com.jung.tj.command.BContentCommand;
import com.jung.tj.command.BDeleteCommand;
import com.jung.tj.command.BListCommand;
import com.jung.tj.command.BModifiyCommand;
import com.jung.tj.command.BReplyCommand;
import com.jung.tj.command.BReplyViewCommand;
import com.jung.tj.command.BWriteCommand;
import com.jung.tj.util.Constant;

@Controller
public class BController {

	BCommand command;
	
	public JdbcTemplate template;
	
	@Autowired
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
		Constant.template = this.template;
	}
	
	@RequestMapping("/list")
	public String list(Model model) {
		
		command = new BListCommand();
		command.execute(model);
		
		return "list";
	}
	
	@RequestMapping("/write_view")
	public String write_view(Model model) {
		
		
		
		return "write_view";
	}
	
	@RequestMapping("/write")
	public String write(HttpServletRequest request, Model model) {
		
		command = new BWriteCommand();
		
		model.addAttribute("request", request);
		command.execute(model);
		
		return "redirect:list";
		
	}
	
	@RequestMapping("content_view")
	public String content_view(HttpServletRequest request, Model model) {
		
		command = new BContentCommand();
		
		model.addAttribute("request", request);
		command.execute(model);
		
		return "content_view";
		
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/modify")
	public String modify(HttpServletRequest request, Model model) {
		command = new BModifiyCommand();
		
		model.addAttribute("request", request);
		command.execute(model);
		
		return "redirect:content_view?bId=" + request.getParameter("bId");
	}
	
	@RequestMapping("/reply_view")
	public String reply_view(HttpServletRequest request, Model model) {
		command = new BReplyViewCommand();
		
		model.addAttribute("request", request);
		command.execute(model);
		
		return "reply_view";
	}
	
	@RequestMapping("/reply")
	public String reply(HttpServletRequest request, Model model) {
		command = new BReplyCommand();
		
		model.addAttribute("request", request);
		command.execute(model);

		return "redirect:content_view";
		
	}
	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request, Model model) {
		command = new BDeleteCommand();
		
		model.addAttribute("request", request);
		command.execute(model);
		
		return "redirect:list";
	}
	
	@RequestMapping("/modify_view")
	public String modifyView(HttpServletRequest request, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		Enumeration<?> enumeration = request.getParameterNames();
         
        String key = null;
        String[] values = null;
        while(enumeration.hasMoreElements()){
            key = (String) enumeration.nextElement();
            values = request.getParameterValues(key);
            if(values != null){
                map.put(key, (values.length > 1) ? values:values[0] );
            }
        }
		
		model.addAttribute("modify_view", map);
		return "modify_view";
	}
	
}
