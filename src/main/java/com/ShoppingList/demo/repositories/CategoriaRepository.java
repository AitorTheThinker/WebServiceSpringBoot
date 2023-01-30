package com.ShoppingList.demo.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ShoppingList.demo.dto.CategoriasDTO;

import com.ShoppingList.demo.mapper.CategoriaRowMapper;

@Repository
public class CategoriaRepository implements ICategoriasDTO{
	
	@Autowired
	@Qualifier("jdbcTemplate")
	 private JdbcTemplate jdbcTemplate;
	    
	    @Autowired
	    public CategoriaRepository(JdbcTemplate jdbcTemplate) {
	        this.jdbcTemplate = jdbcTemplate;
	    }
    
    @Override
    public boolean saveCategoria(CategoriasDTO categoria) {
        String sql = "INSERT INTO categorias (categoria) VALUES (?)";
        int rows = jdbcTemplate.update(sql, categoria.getcategoria());
        return rows > 0;
    }

    @Override
    public boolean updateCompra(CategoriasDTO categoria) {
        String sql = "UPDATE categorias SET categoria = ? WHERE id = ?";
        int rows = jdbcTemplate.update(sql, categoria.getcategoria(), categoria.getId());
        return rows > 0;
    }

    @Override
    public List<CategoriasDTO> getAllCategorias() {
        String sql = "SELECT * FROM categorias";
        return jdbcTemplate.query(sql, new CategoriaRowMapper());
    }

    @Override
    public CategoriasDTO getCategoriasByID(int id) {
        String sql = "SELECT * FROM categorias WHERE id = "+id;
        return jdbcTemplate.queryForObject(sql, new CategoriaRowMapper());
    }

    @Override
    public boolean deleteCategoria(int id) {
        String sql = "DELETE FROM categorias WHERE id = ?";
        int rows = jdbcTemplate.update(sql, id);
        return rows > 0;
    }
}

