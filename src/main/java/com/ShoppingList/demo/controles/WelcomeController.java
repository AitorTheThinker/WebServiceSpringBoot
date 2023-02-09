package com.ShoppingList.demo.controles;



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
	
	@GetMapping("/")
	public String goToIndex(Model model) {
	
		return "pUsuarios.html";
	}
	
	//Login form
	@RequestMapping("/login")
	public String login(Model model) {
		UserDTO user = new UserDTO();
		model.addAttribute("user", user);
		
		return "login.html";
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
	    
//		getRandomUser();
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		model.addAttribute("nombreUser", authentication.getName());
		
		Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>)    SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		
		boolean hasUserRole = authentication.getAuthorities().stream()
		          .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
		
		model.addAttribute("admin", hasUserRole);
		
	    return "pLista";
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
//	@ExceptionHandler(ViewNotFoundException.class)
//	public ModelAndView handleStudentNotFoundException(ViewNotFoundException ex) {
//		Map<String, ViewNotFoundException> model = new HashMap<String, ViewNotFoundException>();
//		model.put("exception", ex);
//		return new ModelAndView("404error", model);
//
//	}
//
	
	
	
	
//	@RequestMapping("/error")
//	public String showError404Generic(Exception ex) {
//
//		return "404error";
//
//	}
	
	
	
	
	
//
//	@ExceptionHandler(Exception.class)
//	public ModelAndView handleException(Exception ex) {
//		Map<String, Exception> model = new HashMap<String, Exception>();
//		model.put("exception", ex);
//		return new ModelAndView("student.error.500", model);
//
//	}
		
	
}