package lamp_shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lamp_shop.model.Cart;
import lamp_shop.model.Product;

@Controller
@RequestMapping("/web/cart")
public class CartController {
	
	@Autowired
	Cart cart;
	
	@GetMapping("/add")
	public String getButton(Model m) {
		m.addAttribute("product", new Product());
		return "products";
	}
	
	@PostMapping("/add")
	public String addToCart(@ModelAttribute("product") Product product, Model m) {
		cart.addToCart(product);
		System.out.println(cart);
		return "products";
	}
	
	
}
