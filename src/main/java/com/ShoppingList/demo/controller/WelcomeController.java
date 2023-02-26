package com.ShoppingList.demo.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;


import com.ShoppingList.demo.dto.CategoriasDTO;
import com.ShoppingList.demo.dto.CompraDTO;
import com.ShoppingList.demo.dto.RandomUserDTO.Root;
import com.ShoppingList.demo.dto.UserDTO;
import com.ShoppingList.demo.repositories.CategoriaRepository;
import com.ShoppingList.demo.repositories.CompraRepository;
import com.google.gson.Gson;

@Controller
public class WelcomeController implements ErrorController{

	@Autowired
	CompraRepository compraRepository;	
	
	@Autowired
	CategoriaRepository categoriaRepository;	
	
	
	public List<CompraDTO> listaCompras =  new ArrayList<CompraDTO>();
	
	//Index
	@GetMapping("/")
	public String goToIndex(Model model) {
	
		return "index.html";
	}
	//Login form
	@RequestMapping("/go-to-Login")
	public String login(Model model) {
		UserDTO user = new UserDTO();
		model.addAttribute("user", user);
		
		return "/login/Login.html";
	}
	//Lista compras
	@Cacheable(value="compras")
	@GetMapping("/go-to-Lista")
	public String goToLista(Model model) {
	    List<CompraDTO> compras = compraRepository.getAllCompras();
	    for (CompraDTO c : compras) {
	        int idCategoria = c.getCategorias().getId();
	    }
	    model.addAttribute("productos", compras);
	    model.addAttribute("categorias", categoriaRepository.getAllCategorias());
	    
//		getRandomUser();
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		model.addAttribute("nombreUser", authentication.getName());
		
		Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>)    SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		
		boolean hasUserRole = authentication.getAuthorities().stream()
		          .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
		
		model.addAttribute("admin", hasUserRole);
		
	    return "/shoplist/pLista";
	}

	
	
	@PostMapping("/add-producto")
	public String goProductoToList(@ModelAttribute("producto") CompraDTO producto) {
		
		CategoriasDTO categoria = categoriaRepository.getCategoriasByID(producto.getCategorias().getId());
		producto.setCategorias(categoria);

		
		 if (producto.getId() ==0) {
		        compraRepository.saveCompra(producto);
		    } else {
		        compraRepository.updateCompra(producto);
		    }
		
		return "redirect:/go-to-lista";

	}
	
	private static void getRandomUser()
	{
	    final String uri = "https://randomuser.me/api/?results=2";

	    RestTemplate restTemplate = new RestTemplate();
	    String result = restTemplate.getForObject(uri, String.class);

	    Gson gson = new Gson();
	    
	    Root staff = gson.fromJson(result, Root.class);
	    System.out.println(result);
	}
	
	@GetMapping("/add-producto")
	public String goToFormProducto(Model model) {

		CompraDTO producto = new CompraDTO();
		CategoriasDTO cat = new CategoriasDTO();
//		producto.setCategoria(cat);

//		model.addAttribute("categoria",cat);
		model.addAttribute("producto", producto);

		model.addAttribute("categorias", categoriaRepository.getAllCategorias());

		return "pAddProducto";
	}
	
	@GetMapping("/pantallaRegistro")
	public String goPantallaRegistro(Model model) {
		
		UserDTO user = new UserDTO();
		model.addAttribute("usuario", user);
		
		return "registration";
	}
	
	@PostMapping("/irRegistroUsuario")
	public String realizarRegistro(@ModelAttribute("usuario") UserDTO user) {
		

		return "registration";
	}
	
	
	@GetMapping("/delete-producto")
	public String deleteProductoByID(@RequestParam("id") int id) {

		compraRepository.deleteCompra(id);

		return "redirect:/go-to-lista";
	}
	
	@GetMapping("/update-producto")
	public String updateProductoByID(@RequestParam("id") int id, Model model ) {
		
		CompraDTO compra=compraRepository.getCompraByID(id);
		model.addAttribute("producto",compra);
		model.addAttribute("categorias", categoriaRepository.getAllCategorias());
		
		return "pAddProducto";
		
	}		
	
}