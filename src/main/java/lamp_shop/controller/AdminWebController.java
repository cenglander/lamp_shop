package lamp_shop.controller;

import java.util.List;
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
import lamp_shop.service.AdminService;

@Controller
@RequestMapping("/admin/web")
public class AdminWebController {
	@Autowired
	AdminService adminService;

	@GetMapping("/orders/all")
	public String getAllOrders(Model m) {
		m.addAttribute("orders", adminService.findAllOrders());
		return "admin-orders";
	}

	@GetMapping("/orders/new")
	public String getNewOrders(Model m) {
		m.addAttribute("orders", adminService.findNewOrders());
		return "admin-orders-new";
	}
//	@PostMapping("/orders/new")
//	public String markAsCompleted(@ModelAttribute("orders") List<Order> orders, Model m) {
//		
//		adminService.markOrderAsCompleted());
//		return "admin-orders-new";
//	}

	@GetMapping("/orders/completed")
	public String getCompletedOrders(Model m) {
		m.addAttribute("orders", adminService.findCompletedOrders());
		return "admin-orders-completed";
	}

	@GetMapping("/products/create")
	public String getProductForm(Model m) {
		m.addAttribute("categories", adminService.getCategories());
		m.addAttribute("product", new Product());
		return "add-product-form";
	}

	@PostMapping("/products/create")
	public String sendProductForm(@ModelAttribute("product") Product product, Model m) {
		if (adminService.createProduct(product)) {
			m.addAttribute("name", product.getName());
			m.addAttribute("categories", adminService.getCategories());
			m.addAttribute("product", new Product());
		} else {
			m.addAttribute("errorMessage","Product could not be created");
		}
		return "add-product-form";
//		return "redirect:/admin/web/products/create";
	}
	
	@GetMapping("/orders/{id}")
	public String getOrder(@PathVariable("id") int id, Model m) {
		Optional<Order> optOrder = adminService.findOrderById(id);
		if(optOrder.isPresent()) {
			Order order = optOrder.get();
			m.addAttribute("order", order);
		}//TODO else 
		return "admin-order";
	}
	
	@PostMapping("/orders/{id}")
	public String markAsCompleted(@PathVariable("id") int id, Model m) {
//	public String markAsCompleted(@PathVariable("id") int id, @ModelAttribute("order") Order order, Model m) {
		adminService.markOrderAsCompleted(id);
		Optional<Order> optOrder = adminService.findOrderById(id);
		if(optOrder.isPresent()) {
			Order order = optOrder.get();
			m.addAttribute("order", order);
		} //TODO else 
		return "admin-order";
	}
}
