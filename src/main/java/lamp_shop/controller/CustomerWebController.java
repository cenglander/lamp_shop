package lamp_shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lamp_shop.model.Category;
import lamp_shop.repositories.CategoryRepository;
import lamp_shop.service.CustomerService;

@Controller
@RequestMapping("/web")
public class CustomerWebController {

	@Autowired
	CustomerService customerService;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@GetMapping("/products")
	public String getAllProducts(Model m) {
		m.addAttribute("products", customerService.getAllProducts());
		return "products" ;
	}
	
	@GetMapping("/category/{id}")
	public String getCategories(@PathVariable("id") int id, Model m) {
	Category category=categoryRepository.findById(id);
	m.addAttribute("products", customerService.getProductsByCategory(category));
		return "products-of-category";
	
	}
	
	@GetMapping("/categories")
	public String getCategories(Model m) {
	m.addAttribute("categories", customerService.getCategories());
		return "categories";
	
	}
	
}
