package com.jung.tj.command;

import java.util.ArrayList;

import org.springframework.ui.Model;

import com.jung.tj.dao.BDao;
import com.jung.tj.dto.BDto;

public class BListCommand implements BCommand {

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		
		BDao dao = new BDao();
		ArrayList<BDto> dtos = dao.list();
		
		model.addAttribute("list", dtos);
		
	}

}
