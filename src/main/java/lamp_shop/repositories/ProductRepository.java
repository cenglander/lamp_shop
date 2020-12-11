package lamp_shop.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import lamp_shop.model.Category;
import lamp_shop.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
public List<Product> findByCategory(Category category);
}
