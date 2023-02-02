package com.ShoppingList.demo.controles;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ShoppingList.demo.dto.CategoriasDTO;
import com.ShoppingList.demo.dto.CompraDTO;
import com.ShoppingList.demo.dto.UserDTO;
import com.ShoppingList.demo.repositories.CategoriaRepository;
import com.ShoppingList.demo.repositories.CompraRepository;




@Controller
public class Control{

	@Autowired
	CompraRepository compraRepository;	
	
	@Autowired
	CategoriaRepository categoriaRepository;	
	
	
	public List<CompraDTO> listaCompras =  new ArrayList<CompraDTO>();
	
	@GetMapping("/")
	public String goToIndex(Model model) {
	
		
		model.addAttribute("nombre", "Aitor");
		return "index";
	}
	
	/**
	 * Metodo GET para obtener la request
	 * y mostrar los resultados de las compras
	 * @param model
	 * @param categoria
	 * @return
	 */
	@Cacheable(value="compras")
	@GetMapping("/go-to-lista")
	public String goToLista(Model model) {
	    List<CompraDTO> compras = compraRepository.getAllCompras();
	    for (CompraDTO c : compras) {
	        int idCategoria = c.getCategorias().getId();
	    }
	    model.addAttribute("productos", compras);
	    model.addAttribute("categorias", categoriaRepository.getAllCategorias());
	    return "pLista";
	}

	
	@GetMapping("/add-producto")
	public String goToFormProducto(Model model) {
		CompraDTO producto = new CompraDTO();
		model.addAttribute("producto", producto);
		model.addAttribute("categorias", categoriaRepository.getAllCategorias());
		return "pAddProducto";
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
	
	@GetMapping("/delete-producto")
	public String deleteCompra(@RequestParam("id") int id) {
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
	
	@RequestMapping("/login")
	public String login(Model model) {
		UserDTO user = new UserDTO();
		model.addAttribute("user", user);
		
		return "login.html";
	}
	
	
	
}