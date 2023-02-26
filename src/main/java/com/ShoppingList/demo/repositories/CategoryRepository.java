package com.ShoppingList.demo.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ShoppingList.demo.dto.CategoriesDTO;

import com.ShoppingList.demo.mapper.CategoryRowMapper;

@Repository
public class CategoryRepository implements ICategory{
	
	@Autowired
	@Qualifier("jdbcTemplateDB1")
	 private JdbcTemplate jdbcTemplate;
	    
    
    @Override
    public boolean saveCategoria(CategoriesDTO categoria) {
        String sql = "INSERT INTO category (categoria) VALUES (?)";
        int rows = jdbcTemplate.update(sql, categoria.getCategoria());
        return rows > 0;
    }

    @Override
    public boolean updateCompra(CategoriesDTO categoria) {
        String sql = "UPDATE category SET categoria = ? WHERE id = ?";
        int rows = jdbcTemplate.update(sql, categoria.getCategoria(), categoria.getId());
        return rows > 0;
    }

    @Override
    public List<CategoriesDTO> getAllCategorias() {
        String sql = "SELECT * FROM category";
        return jdbcTemplate.query(sql, new CategoryRowMapper());
    }

    @Override
    public CategoriesDTO getCategoriasByID(int id) {
        String sql = "SELECT * FROM category WHERE id = "+id;
        return jdbcTemplate.queryForObject(sql, new CategoryRowMapper());
    }

    @Override
    public boolean deleteCategoria(int id) {
        String sql = "DELETE FROM category WHERE id = ?";
        int rows = jdbcTemplate.update(sql, id);
        return rows > 0;
    }
    @Override
	public List<CategoriesDTO> getAllCategoria() {
		
		
		return jdbcTemplate.query("SELECT * from category", new CategoryRowMapper());
	}

	@Override
	public CategoriesDTO getCategoriasById(int id) {
		String sql = String.format("SELECT * FROM category WHERE id='%d'",id);
		return jdbcTemplate.queryForObject(sql, new CategoryRowMapper());
	}

	@Override
	public CategoriesDTO getCategoriaById(int id) {
		String sql = String.format("SELECT * FROM category WHERE id='%d'",id);
		return jdbcTemplate.queryForObject(sql, new CategoryRowMapper());
	}
}

