	package com.ShoppingList.demo.repositories;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ShoppingList.demo.dto.CompraDTO;
import com.ShoppingList.demo.mapper.CompraRowMapper;

@Repository
public class CompraRepository implements ICompraRepository{

	@Autowired
	JdbcTemplate jdbcTemplate;

	
	@Autowired
	JdbcTemplate jdbcTemplate2;
	
	@Override
	public boolean saveCompra(CompraDTO compra) {
		   try {
			   String sql = "INSERT INTO compras(descripcion, categoria) VALUES(?,?)";
		        jdbcTemplate.update(sql, compra.getDescripcion(), compra.getCategorias().getId());
		        jdbcTemplate2.update("INSERT INTO proveedor (nombre, email) VALUES(?,?)", "Proveedores", "Email@Proveedor.com");
		    }catch(Exception e){
		    return false;
		    }
		    return true;
		}
	

	@Override
	public boolean updateCompra(CompraDTO compra) {
	    try {
	        String sql = "UPDATE compras SET descripcion = ? WHERE id = ?";
	        jdbcTemplate.update(sql,compra.getDescripcion(),compra.getId());
	    } catch (Exception e) {
	        return false;
	    }
	    return true;
	}
	
	@Override
	public List<CompraDTO> getAllCompras() {
		
		return jdbcTemplate.query("SELECT * FROM compras", new CompraRowMapper());
		

	}
	@Override
	public List<CompraDTO> getAllCategorias() {
		
		return jdbcTemplate.query("SELECT * FROM categorias", new CompraRowMapper());
		
	}

	@Override
	public CompraDTO getCompraByID(int id) {
		return jdbcTemplate.queryForObject("SELECT * FROM compras WHERE id="+id,
				new CompraRowMapper());
	}

	
	@Override
	public boolean deleteCompra(int id) {
	    try {
	        String sql = String.format("DELETE FROM compras WHERE id = '%d'", id);
	        jdbcTemplate.execute(sql);
	    } catch (Exception e) {
	        return false;
	    }
	    return true;
	}
	
}
