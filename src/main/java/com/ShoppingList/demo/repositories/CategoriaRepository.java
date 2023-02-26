package com.ShoppingList.demo.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ShoppingList.demo.dto.CategoriasDTO;

import com.ShoppingList.demo.mapper.CategoriaRowMapper;

@Repository
public class CategoriaRepository implements ICategorias{
	
	@Autowired
	@Qualifier("jdbcTemplateDB1")
	 private JdbcTemplate jdbcTemplate;
	    
    
    @Override
    public boolean saveCategoria(CategoriasDTO categoria) {
        String sql = "INSERT INTO category (categoria) VALUES (?)";
        int rows = jdbcTemplate.update(sql, categoria.getCategoria());
        return rows > 0;
    }

    @Override
    public boolean updateCompra(CategoriasDTO categoria) {
        String sql = "UPDATE category SET categoria = ? WHERE id = ?";
        int rows = jdbcTemplate.update(sql, categoria.getCategoria(), categoria.getId());
        return rows > 0;
    }

    @Override
    public List<CategoriasDTO> getAllCategorias() {
        String sql = "SELECT * FROM category";
        return jdbcTemplate.query(sql, new CategoriaRowMapper());
    }

    @Override
    public CategoriasDTO getCategoriasByID(int id) {
        String sql = "SELECT * FROM category WHERE id = "+id;
        return jdbcTemplate.queryForObject(sql, new CategoriaRowMapper());
    }

    @Override
    public boolean deleteCategoria(int id) {
        String sql = "DELETE FROM category WHERE id = ?";
        int rows = jdbcTemplate.update(sql, id);
        return rows > 0;
    }
    @Override
	public List<CategoriasDTO> getAllCategoria() {
		
		
		return jdbcTemplate.query("SELECT * from category", new CategoriaRowMapper());
	}

	@Override
	public CategoriasDTO getCategoriasById(int id) {
		String sql = String.format("SELECT * FROM category WHERE id='%d'",id);
		return jdbcTemplate.queryForObject(sql, new CategoriaRowMapper());
	}

	@Override
	public CategoriasDTO getCategoriaById(int id) {
		String sql = String.format("SELECT * FROM category WHERE id='%d'",id);
		return jdbcTemplate.queryForObject(sql, new CategoriaRowMapper());
	}
}

