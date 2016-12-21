package com.jung.tj.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.jung.tj.dto.BDto;
import com.jung.tj.util.Constant;

public class BDao {
	
	JdbcTemplate template = null;
	
	public BDao() {
		
		template = Constant.template;
		
	}
	
	public ArrayList<BDto> list() {
		
		String query = "select bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent from mvc_board order by bGroup desc, bStep asc";
		return (ArrayList<BDto>) template.query(query, new BeanPropertyRowMapper<BDto>(BDto.class));

	}

	public void write(String bName, String bTitle, String bContent) {
		
		String sql = "select ifnull(max(bId), 0)+1 from mvc_board";
		int curval = template.queryForObject(sql, Integer.class);
		String query = "insert into mvc_board (bName, bTitle, bContent, bGroup) values (?, ?, ?, ?)";
		template.update(query, new Object[]{bName, bTitle, bContent, curval} );

	}

	public Map<String, Object> contentView(String sbId) {
		
		upHit(sbId);
		String query = "select * from mvc_board where bId = ?";
		return template.queryForMap(query, sbId);
	
	}

	private void upHit(String bId) {
		
		String query = "update mvc_board set bHit = bHit + 1 where bId = ?";
		template.update(query, bId);
		
	}

	public void modify(Map<String, Object> map) {

		String query = "update mvc_board set bTitle = ?, bContent = ? where bId = ?";
		template.update(query, new Object[] {map.get("bTitle"), map.get("bContent"), map.get("bId")});
		
	}

	public void delete(String sbId) {
		
		String query = "delete from mvc_board where bId = ?";
		template.update(query, sbId);
		
	}

	public Map<String, Object> replyView(String sbId) {
		
		String query = "select * from mvc_board where bId = ?";
		return template.queryForMap(query, sbId);
		
	}

	public void replyShape(String bGroup, String bStep) {
		
		String query = "update mvc_board set bStep = bStep + 1 where bGroup = ? and bStep > ?";
		template.update(query, bGroup, bStep);

	}

	public String reply(String bName, String bTitle, String bContent, String bGroup, String bStep, String bIndent) {
		
		replyShape(bGroup, bStep);
		
		String query = "insert into mvc_board (bName, bTitle, bContent, bGroup, bStep, bIndent) values (?, ?, ?, ?, ?, ?)";
		template.update(query, bName, bTitle, bContent, bGroup, bStep + 1, bIndent + 1);
		String sql = "select ifnull(max(bId), 0) from mvc_board";
		return template.queryForObject(sql, String.class);

	}
	
}
