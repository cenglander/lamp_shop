package lamp_shop;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class LampRestController {
	
	@Autowired
	LampShopService lampShopService;
	
	@Autowired
	ProductService productService;

	@GetMapping("/products")
	public ResponseEntity<List<Product>> getAllProducts() {
		return ResponseEntity.accepted().body(productService.getAllProducts());
	}
	
	@GetMapping("/orders")
	public ResponseEntity<List<Order>> getAllOrders() {
		return ResponseEntity.accepted().body(lampShopService.getAllOrders());
	}
	
	@PostMapping("/product/add")
	public ResponseEntity<List> createProduct(@RequestBody String name, Double price,  String category) {
		// TODO problem med raden ovan, kolla upp hur @RequestBody eller annat ska användas här
		System.out.println(name);
		System.out.println(price);
		System.out.println(category);
		productService.createProduct(name, price, category);
		return ResponseEntity.accepted().body(productService.getAllProducts());
	}
	
//	@PostMapping("/body")
//	public ResponseEntity<List> guessFeedbackPairBody(@RequestBody String guess) {
//		new GuessFeedbackPair(guess, mooService.checkGuess(mooService.getAnswerKey(), guess));
//		return ResponseEntity.accepted().body(mooService.getGuessFeedbackPairs());
//	}
}
