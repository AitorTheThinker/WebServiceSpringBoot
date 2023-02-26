package com.ShoppingList.demo.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ShoppingList.demo.dto.CategoriesDTO;
import com.ShoppingList.demo.dto.PurchaseDTO;

public class PurchaseRowMapper implements RowMapper<PurchaseDTO>{

	@Override
	public PurchaseDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		PurchaseDTO compra = new PurchaseDTO();
		compra.setId(rs.getInt(1));
		compra.setDescripcion(rs.getString(2));
		compra.setCategorias(new CategoriesDTO(rs.getInt(3),rs.getString(4)));
		compra.setImagenUrl(rs.getString(5));
		return compra;
	}
}