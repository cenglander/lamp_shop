package lamp_shop.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lamp_shop.model.Order;
import lamp_shop.model.Product;
import lamp_shop.service.AdminService;

@RestController
@RequestMapping("/admin/rest")
public class AdminRestController {

	@Autowired
	AdminService adminService;
	
	@GetMapping("/completed")
	public ResponseEntity<List<Order>> findCompletedOrders() {
		return ResponseEntity.accepted().body(adminService.findCompletedOrders());
	}
	
	@GetMapping("/not-completed")
	public ResponseEntity<List<Order>> findNotCompletedOrders() {
		return ResponseEntity.accepted().body(adminService.findNewOrders());
	}
	
	@PostMapping("/mark-as-completed/{id}") 
	public ResponseEntity<Optional<Order>> markOrderAsCompleted(@PathVariable int id) {
		if (adminService.markOrderAsCompleted(id)) {
			return ResponseEntity.accepted().body(adminService.findOrderById(id));
		} else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(Optional.empty());
		}
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping("/product/add")
	public ResponseEntity<List> createProduct(@RequestBody Product product) {
		adminService.createProduct(product);
		return ResponseEntity.accepted().body(adminService.getAllProducts());
	}
	
	
}
