package com.ShoppingList.demo.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ShoppingList.demo.dto.CategoriasDTO;
import com.ShoppingList.demo.dto.CompraDTO;

public class CompraRowMapper implements RowMapper<CompraDTO>{

	@Override
	public CompraDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		CompraDTO compra = new CompraDTO();
		compra.setId(rs.getInt(1));
		compra.setDescripcion(rs.getString(2));
		compra.setCategorias(new CategoriasDTO(rs.getInt(3),rs.getString(4)));
		compra.setImagenUrl(rs.getString(5));
		return compra;
	}
}