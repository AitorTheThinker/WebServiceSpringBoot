	package com.ShoppingList.demo.repositories;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ShoppingList.demo.dto.PurchaseDTO;
import com.ShoppingList.demo.mapper.PurchaseRowMapper;

@Repository
public class PurchaseRepository implements IPurchaseRepository{

	@Autowired
	@Qualifier("jdbcTemplateDB1")
	JdbcTemplate jdbcTemplate;

	
	@Autowired
	@Qualifier("jdbcTemplateDB2")
	JdbcTemplate jdbcTemplate2;

	
	@Override
	public boolean saveCompra(PurchaseDTO compra) {
		   try { /*descripcion, categoria, imagenUrl, enabled  VALUES(%)*/
			   String sql = "INSERT INTO shoplist(descripcion, categoria) VALUES(?,?)";
		        jdbcTemplate.update(sql, compra.getDescripcion(), compra.getCategorias().getId());
		        jdbcTemplate2.update("INSERT INTO proveedor (nombre, email) VALUES(?,?)", "Proveedores", "Email@Proveedor.com");
		    }catch(Exception e){
		    return false;
		    }
		    return true;
		}
	

	@Override
	public boolean updateCompra(PurchaseDTO compra) {
	    try {
	        String sql = "UPDATE shoplist SET descripcion = ? WHERE id = ?";
	        jdbcTemplate.update(sql,compra.getDescripcion(),compra.getId());
	    } catch (Exception e) {
	        return false;
	    }
	    return true;
	}
	
	@Override
	public List<PurchaseDTO> getAllCompras() {
		
		return jdbcTemplate.query("SELECT * FROM shoplist", new PurchaseRowMapper());
		

	}
	@Override
	public List<PurchaseDTO> getAllCategorias() {
		
		return jdbcTemplate.query("SELECT * FROM category", new PurchaseRowMapper());
		
	}

	@Override
	public PurchaseDTO getCompraByID(int id) {
		return jdbcTemplate.queryForObject("SELECT * FROM shoplist WHERE id="+id,
				new PurchaseRowMapper());
	}

	
	@Override
	public boolean deleteCompra(int id) {
	    try {
	        String sql = String.format("DELETE FROM shoplist WHERE id = '%d'", id);
	        jdbcTemplate.execute(sql);
	    } catch (Exception e) {
	        return false;
	    }
	    return true;
	}



	public PurchaseDTO getCompraByDescription(String descripcion) {
		
		String sql = String.format("SELECT c.id,c.descripcion,c.categoria,cat.nombre,c.imagenUrl FROM shoplist c,categorias cat where c.categoria=cat.id where descripcion='%s'",descripcion);
		return jdbcTemplate.queryForObject(sql, new PurchaseRowMapper());
	}
	
}
