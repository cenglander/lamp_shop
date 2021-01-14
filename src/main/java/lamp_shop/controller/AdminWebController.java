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

import lamp_shop.model.Order;
import lamp_shop.model.Product;
import lamp_shop.model.User;
import lamp_shop.service.AdminService;

@Controller
@RequestMapping("/admin/web")
public class AdminWebController {
	
	@Autowired
	AdminService adminService;

	@GetMapping("/orders/all")
	public String getAllOrders(Model m) {
		String template = handleNotLoggedIn("admin-orders");
		m.addAttribute("orders", adminService.findAllOrders());
		return template;
	}	

	@GetMapping("/orders/new")
	public String getNewOrders(Model m) {
		String template = handleNotLoggedIn("admin-orders-new");
		m.addAttribute("orders", adminService.findNewOrders());
		return template;
	}

	@GetMapping("/orders/completed")
	public String getCompletedOrders(Model m) {
		String template = handleNotLoggedIn("admin-orders-completed");
		m.addAttribute("orders", adminService.findCompletedOrders());
		return template;
	}
	
	@GetMapping("/login")
	public String getForm(Model m) {
		m.addAttribute("admin", new User());
		return "admin-login-form";
	}
	@PostMapping("/login")
	public String acceptForm(@ModelAttribute("admin") User admin, Model m) {
		if (adminService.loginAdmin(admin)) {
			return "redirect:/admin/web/orders/all";
		} else {
			m.addAttribute("errorMsg", "Invalid username or password. Try again.");
			return "admin-login-form";
		}
	}
	
	@GetMapping("/products")
	public String getAllProducts(Model m) {
		String template = handleNotLoggedIn("admin-products");
		m.addAttribute("products", adminService.getAllProducts());
		return template;
	}
	@GetMapping("/products/create")
	public String getProductForm(Model m) {
		String template = handleNotLoggedIn("add-product-form");
		m.addAttribute("categories", adminService.getCategories());
		m.addAttribute("product", new Product());
		return template;
	}

	@PostMapping("/products/create")
	public String sendProductForm(@ModelAttribute("product") Product product, Model m) {
		String template = handleNotLoggedIn("add-product-form");
		if (adminService.createProduct(product)) {
			m.addAttribute("name", product.getName());
			m.addAttribute("categories", adminService.getCategories());
			m.addAttribute("product", new Product());
		} else {
			m.addAttribute("errorMessage","Product could not be created");
		}
		return template;
	}
	
	@GetMapping("/orders/{id}")
	public String getOrder(@PathVariable("id") int id, Model m) {
		String template = handleNotLoggedIn("admin-order");
		Optional<Order> optOrder = adminService.findOrderById(id);
		if(optOrder.isPresent()) {
			m.addAttribute("order", optOrder.get());
		}
		return template;
	}
	
	@PostMapping("/orders/{id}")
	public String markAsCompleted(@PathVariable("id") int id, Model m) {
		String template = handleNotLoggedIn("admin-order");
		adminService.markOrderAsCompleted(id);
		Optional<Order> optOrder = adminService.findOrderById(id);
		if(optOrder.isPresent()) {
			m.addAttribute("order", optOrder.get());
		} 
		return template;
	}
	
	public String handleNotLoggedIn(String template) {
		if (!adminService.isLoggedIn()) {		
			return "redirect:/admin/web/login";
		} else {
			return template;
		}
	}

	
}
