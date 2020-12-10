package lamp_shop;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
		m.addAttribute("product", new Product());
		return "add-product-form";
	}

	@PostMapping("/products/create")
	public String sendProductForm(@ModelAttribute("product") Product product, Model m) {
		if (adminService.createProduct(product)) {
			m.addAttribute("name", product.getName());

		} else {
			m.addAttribute("errorMessage","Product could not be created");
		}
		return "add-product-form";

	}
	
	

}
