package com.ShoppingList.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ShoppingList.demo.dto.CompraDTO;
import com.ShoppingList.demo.repositories.CompraRepository;


@RestController
public class CompraApi {
	
	@Autowired
	CompraRepository compraRepository;
	
	@GetMapping ("/compras")
	public List<CompraDTO> getCompras(){
		
		return compraRepository.getAllCompras();
		
	}
	
}
