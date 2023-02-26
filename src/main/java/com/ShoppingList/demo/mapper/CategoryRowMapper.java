package com.ShoppingList.demo.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.ShoppingList.demo.dto.CategoriesDTO;


public class CategoryRowMapper implements RowMapper<CategoriesDTO>{
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public CategoriesDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		CategoriesDTO cat = new CategoriesDTO();
		cat.setId(rs.getInt(1));
		cat.setCategoria(rs.getString(2));
		
		return cat;
	}
}
