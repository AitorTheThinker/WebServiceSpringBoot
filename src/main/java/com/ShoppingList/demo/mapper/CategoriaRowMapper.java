package com.ShoppingList.demo.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.ShoppingList.demo.dto.CategoriasDTO;


public class CategoriaRowMapper implements RowMapper<CategoriasDTO>{
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
    @Override
    public CategoriasDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        CategoriasDTO categorias= new CategoriasDTO();
        categorias.setId(rs.getInt("id"));
        categorias.setCategoria(rs.getString("categoria"));
        return categorias;
    }
}
