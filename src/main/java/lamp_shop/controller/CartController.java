package lamp_shop.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lamp_shop.model.Product;
import lamp_shop.service.CustomerService;

@Controller
@RequestMapping("/web/cart")
public class CartController {
	
	@Autowired
	CustomerService customerService;
	
	@GetMapping("/add")
	public String getButton(Model m) {
		String template = handleNotLoggedIn("products");
		m.addAttribute("product", new Product());
		return template;
	}
	
	@PostMapping("/add/{id}")
	public String addToCart(@PathVariable("id") int id,  Model m, HttpServletRequest request) {
		Product product=customerService.getProductById(id);
		String referer = request.getHeader("Referer");
		String template = handleNotLoggedIn("redirect:"+ referer);
		if (template.equals("redirect:"+ referer)) {
			customerService.getCart().addToCart(product);
		}
	    return template;
	}
	
	@GetMapping("/increase/{id}")
	public String increaseQuantity(@PathVariable("id") int productId, Model m) {
		customerService.getCart().increaseQuantity(productId);
		return "redirect:/web/cart/mycart";	
	}
	
	@GetMapping("/decrease/{id}")
	public String decreaseQuantity(@PathVariable("id") int productId, Model m) {
		customerService.getCart().decreaseQuantity(productId);
		return "redirect:/web/cart/mycart";
	}
	
	@GetMapping("/mycart")
	public String showCart(Model m) {
		String template = handleNotLoggedIn("cart");
		if (template.equals("cart")) {
			m.addAttribute("orderLines", customerService.getCart().getOrderLines());
			m.addAttribute("total", customerService.getCart().getCartTotal());
		}
		return template;
	}
	
	public String handleNotLoggedIn(String template) {
		if (!customerService.isLoggedIn()) {		
			return "redirect:/web/login";
		} else {
			return template;
		}
	}
	
}
