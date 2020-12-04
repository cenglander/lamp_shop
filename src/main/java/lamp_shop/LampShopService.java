package lamp_shop;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@Service
@SessionScope
public class LampShopService {
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	OrderRepository orderRepository;
	
//	public List<Product> getAllProducts(){
//		return productRepository.findAll();
//	}
	
	public List<Order> getAllOrders() {
		return orderRepository.findAll();
	}

}
