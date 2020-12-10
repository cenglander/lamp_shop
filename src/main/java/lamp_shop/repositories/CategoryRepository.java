package lamp_shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import lamp_shop.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
