package lamp_shop.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lamp_shop.model.Category;
import lamp_shop.model.Order;
import lamp_shop.model.Product;
import lamp_shop.repositories.CategoryRepository;
import lamp_shop.repositories.OrderRepository;
import lamp_shop.repositories.ProductRepository;

@Service
public class AdminService {
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	public List<Order> findAllOrders() {
		List<Order> orders = orderRepository.findAll();
		return orders;
	}
	
	public List<Order> findCompletedOrders() {
		List<Order> orders = findAllOrders();
		List<Order> completedOrders = orders.stream()
				.filter(o -> o.isCompleted()).collect(Collectors.toList());;
		return completedOrders;
	}
	
	public List<Order> findNewOrders() {
		List<Order> orders = findAllOrders();
		List<Order> notCompletedOrders = orders.stream()
				.filter(o -> !o.isCompleted()).collect(Collectors.toList());
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
	
	public List<Category> getCategories() {
		return categoryRepository.findAll();
	}
	
	public boolean createProduct(Product product) {
		try {
			productRepository.save(product);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public List<Product> getAllProducts(){
		return productRepository.findAll();
	}
	
	public Optional<Order> findOrderById(int id) {
		return orderRepository.findById(id);
	}

}