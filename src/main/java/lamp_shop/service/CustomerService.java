package lamp_shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lamp_shop.model.Category;
import lamp_shop.model.Product;
import lamp_shop.repositories.CategoryRepository;
import lamp_shop.repositories.OrderRepository;
import lamp_shop.repositories.ProductRepository;

@Service
public class CustomerService {
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	public List<Category> getCategories() {
		return categoryRepository.findAll();
	}
	public List<Product> getAllProducts(){
		return productRepository.findAll();
	}
	public List<Product> getProductsByCategory(Category category){
		return productRepository.findByCategory(category);
	}
	
}
