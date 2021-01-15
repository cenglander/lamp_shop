package lamp_shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lamp_shop.model.Cart;
import lamp_shop.model.Category;
import lamp_shop.model.Order;
import lamp_shop.model.OrderLine;
import lamp_shop.model.Product;
import lamp_shop.model.User;
import lamp_shop.repositories.CategoryRepository;
import lamp_shop.repositories.OrderRepository;
import lamp_shop.repositories.ProductRepository;
import lamp_shop.repositories.UserRepository;

@Service
public class CustomerService {
	
	private boolean isLoggedIn;
	
	private Cart cart;
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	EmailService emailServiceImplAlternative2;
	
	public boolean loginCustomer(User cust) {
		User customer = userRepository.findOneByName(cust.getName());
		if (customer!=null && cust.getPassword().equals(customer.getPassword())) {
			System.out.println("Customer=" + customer);
			cart = new Cart(customer);
			isLoggedIn = true;
			return true;
		} else {
			System.out.println("Customer is null");
			isLoggedIn = false;
			return false;
		}
	}
	
	public boolean registerCustomer(User customer) {
		try {
			customer.setRole("customer");
			userRepository.save(customer);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public Order placeOrder() {
		User customer = getCart().getCustomer();
		List<OrderLine> orderLines = getCart().getOrderLines();
		Order order = new Order(customer, orderLines);
		
		order = orderRepository.save(order);
		
		String email = customer.getEmail();
		int orderId = order.getId();
		emailServiceImplAlternative2.sendMessage(email, orderId, orderLines);
		return order;
	}
	
	public List<Product> findProductsByName(String name) {
		return productRepository.findByNameIgnoreCase(name);		
	}
	
	public Product getProductById(int id) {
		return productRepository.findOneById(id);
	}
	
	public List<Category> getCategories() {
		return categoryRepository.findAll();
	}
	
	public List<Product> getAllProducts(){
		return productRepository.findAll();
	}
	
	public List<Product> getProductsByCategory(Category category){
		return productRepository.findByCategory(category);
	}
	
	public Category getCategoryById(int id) {
		return categoryRepository.findById(id);
	}
	
	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}
	public boolean isLoggedIn() {
		return isLoggedIn;
	}
	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}
	
}
