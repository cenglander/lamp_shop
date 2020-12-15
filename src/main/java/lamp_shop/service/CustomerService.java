package lamp_shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lamp_shop.model.Category;
import lamp_shop.model.Product;
import lamp_shop.model.User;
import lamp_shop.repositories.CategoryRepository;
import lamp_shop.repositories.OrderRepository;
import lamp_shop.repositories.ProductRepository;
import lamp_shop.repositories.UserRepository;


@Service
public class CustomerService {
	
	private boolean isLoggedIn;
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	UserRepository userRepository;
	
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
	
	public boolean loginCustomer(User cust) {
		User customer = userRepository.findOneByName(cust.getName());
		if (cust.getPassword().equals(customer.getPassword())) {
			isLoggedIn = true;
			return true;
		} else {
			isLoggedIn = false;
			return false;
		}
	}
	
}
