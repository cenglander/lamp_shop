package lamp_shop.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import lamp_shop.model.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

	Optional<Order> findById(int id);

}
