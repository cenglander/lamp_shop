package lamp_shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import lamp_shop.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
