package com.ShoppingList.demo.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ShoppingList.demo.dto.CompraDTO;

public class CompraRowMapper implements RowMapper<CompraDTO>{

	@Override
	public CompraDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		CompraDTO compras= new CompraDTO();
		compras.setId(rs.getInt(1));
		compras.setDescripcion(rs.getString(2));
		
		return compras;
	}


	
}
