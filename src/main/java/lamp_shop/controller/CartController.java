package lamp_shop.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lamp_shop.model.Cart;
import lamp_shop.model.Product;
import lamp_shop.service.CustomerService;

@Controller
@RequestMapping("/web/cart")
public class CartController {
	
	@Autowired
	CustomerService customerService;
	
	@GetMapping("/add")
	public String getButton(Model m) {
		m.addAttribute("product", new Product());
		return "products";
	}
	
	@PostMapping("/add/{id}")
	public String addToCart(@PathVariable("id") int id,  Model m) {
		Product product=customerService.getProductById(id);
		customerService.getCart().addToCart(product);
		System.out.println(customerService.getCart());
		return "products";
	}
	
	@GetMapping("/mycart")
	public String showCart(Model m) {
		m.addAttribute("orderLines", customerService.getCart().getOrderLines());
		return "cart";
	}
	
}
