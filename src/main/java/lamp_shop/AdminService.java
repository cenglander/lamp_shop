package lamp_shop;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	public List<Order> findAllOrders() {
		List<Order> orders = orderRepository.findAll();
		return orders;
	}
	
	public Stream<Order> findCompletedOrders() {
		List<Order> orders = findAllOrders();
		Stream<Order> completedOrders = orders.stream()
				.filter(o -> o.isCompleted());
		return completedOrders;
	}
	
	public Stream<Order> findNotCompletedOrders() {
		List<Order> orders = findAllOrders();
		Stream<Order> notCompletedOrders = orders.stream()
				.filter(o -> !o.isCompleted());
		return notCompletedOrders;
	}
	
	public boolean markOrderAsCompleted(int id) {
		Optional<Order> order = orderRepository.findById(id);
		if(order.isPresent()) {
			Order tempOrder = order.get();
			tempOrder.setCompleted(true);
			orderRepository.save(tempOrder);
			return true;
		} else {
			return false;
		}
	}
	
	public void createProduct(Product product) {
		productRepository.save(product);
	}
	
	public List<Product> getAllProducts(){
		return productRepository.findAll();
	}
	
	public Optional<Order> findOrderById(int id) {
		return orderRepository.findById(id);
	}

}
