package lamp_shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lamp_shop.model.Category;
import lamp_shop.model.Order;
import lamp_shop.model.Product;
import lamp_shop.model.User;
import lamp_shop.service.CustomerService;

@Controller
@RequestMapping("/web")
public class CustomerWebController {

	@Autowired
	CustomerService customerService;

	@GetMapping("/products")
	public String getAllProducts(Model m) {
		m.addAttribute("products", customerService.getAllProducts());
		return "products";
	}

	@GetMapping("/category/{id}")
	public String getCategories(@PathVariable("id") int id, Model m) {
		Category category = customerService.getCategoryById(id);
		m.addAttribute("category", category.getName());
		m.addAttribute("products", customerService.getProductsByCategory(category));
		return "products-of-category";
	}

	@GetMapping("/categories")
	public String getCategories(Model m) {
		m.addAttribute("categories", customerService.getCategories());
		return "categories";
	}

	@GetMapping("/login")
	public String loginForm(Model m) {
		m.addAttribute("customer", new User());
		return "login-form";
	}

	@PostMapping("/login")
	public String acceptForm(@ModelAttribute("customer") User customer, Model m) {
		if (customerService.loginCustomer(customer)) {
			return "redirect:/web/categories";
		} else {
			m.addAttribute("errorMsg", "Wrong username or password. Try again or register.");
			return "login-form";
		}
	}

	@GetMapping("/register")
	public String registerForm(Model m) {
		m.addAttribute("customer", new User());
		return "register-form";
	}

	@PostMapping("/register")
	public String acceptRegistrationForm(@ModelAttribute("customer") User customer, Model m) {
		if (customerService.registerCustomer(customer)) {
			return "redirect:/web/categories";
		} else {
			m.addAttribute("errorMsg", "Invalid username. Try again.");
			return "register-form";
		}
	}

	@PostMapping("/order")
	public String acceptOrder(Model m) {
		Order order = customerService.placeOrder();
		m.addAttribute("order", order);
		return "order-confirmation";
	}

	@GetMapping("/search")
	public String showSearchForm(Model m) {

		return "search-results";
	}

	@PostMapping("/search")
	public String acceptSearch(@ModelAttribute("searchstring") String searchString, Model m) {
		List<Product> products = customerService.findProductsByName(searchString);
		m.addAttribute("products", products);
		return "search-results";
	}
	
}
