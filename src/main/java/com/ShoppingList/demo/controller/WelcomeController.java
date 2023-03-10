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


import com.ShoppingList.demo.dto.CategoriesDTO;
import com.ShoppingList.demo.dto.PurchaseDTO;
import com.ShoppingList.demo.dto.RandomUserDTO.Root;
import com.ShoppingList.demo.dto.UserDTO;
import com.ShoppingList.demo.repositories.CategoryRepository;
import com.ShoppingList.demo.repositories.PurchaseRepository;
import com.google.gson.Gson;

@Controller
public class WelcomeController implements ErrorController{

	@Autowired
	PurchaseRepository compraRepository;	
	
	@Autowired
	CategoryRepository categoriaRepository;	
	
	
	public List<PurchaseDTO> listaCompras =  new ArrayList<PurchaseDTO>();
	
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
	    List<PurchaseDTO> compras = compraRepository.getAllCompras();
	    for (PurchaseDTO c : compras) {
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
	public String goProductoToList(@ModelAttribute("producto") PurchaseDTO producto) {
		
		CategoriesDTO categoria = categoriaRepository.getCategoriasByID(producto.getCategorias().getId());
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

		PurchaseDTO producto = new PurchaseDTO();
		CategoriesDTO cat = new CategoriesDTO();
//		producto.setCategoria(cat);

//		model.addAttribute("categoria",cat);
		model.addAttribute("producto", producto);

		model.addAttribute("categorias", categoriaRepository.getAllCategorias());

		return "/shoplist/pAddProducto";
	}
	
	@GetMapping("/go-to-PantallaRegistro")
	public String goPantallaRegistro(Model model) {
		
		UserDTO user = new UserDTO();
		model.addAttribute("usuario", user);
		
		return "/login/registration";
	}
	
	@PostMapping("/irRegistroUsuario")
	public String realizarRegistro(@ModelAttribute("usuario") UserDTO user) {
		

		return "/";
	}
	
	
	@GetMapping("/delete-producto")
	public String deleteProductoByID(@RequestParam("id") int id) {

		compraRepository.deleteCompra(id);

		return "redirect:/go-to-lista";
	}
	
	@GetMapping("/update-producto")
	public String updateProductoByID(@RequestParam("id") int id, Model model ) {
		
		PurchaseDTO compra=compraRepository.getCompraByID(id);
		model.addAttribute("producto",compra);
		model.addAttribute("categorias", categoriaRepository.getAllCategorias());
		
		return "pAddProducto";
		
	}		
	
}