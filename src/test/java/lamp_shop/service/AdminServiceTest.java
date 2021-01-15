package lamp_shop.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import lamp_shop.model.Category;
import lamp_shop.model.Order;
import lamp_shop.model.OrderLine;
import lamp_shop.model.Product;
import lamp_shop.model.User;
import lamp_shop.repositories.OrderRepository;

class AdminServiceTest {
	private OrderRepository mockOrderRepository;
	private Order order1;
	private Order order2;
	private Order order3;
	private Order order4;
	private OrderLine orderLine1;
	private OrderLine orderLine2;
	private Product product;
	private Category category;
	private User user1;
	private User user2;
	private List<OrderLine> orderLines1;
	private List<OrderLine> orderLines2;
	private AdminService adminService;
	
	@BeforeEach
	void setUpBeforeClass() throws Exception {
		mockOrderRepository=mock(OrderRepository.class);
		adminService = new AdminService();
		user1= new User(1,"Kalle","kalle@gmail.com","customer","kalle");
		user2= new User(2,"Emily","emily@gmail.com","customer","emily");
		category = new Category();
		category.setId(1);
		category.setName("category");
		product = new Product("Lamp 1", 50.0, category);
		product.setId(1);
		orderLine1=new OrderLine(1,2,product);
		orderLine2=new OrderLine(2,3,product);
		orderLines1=new ArrayList<>();
		orderLines2=new ArrayList<>();
		orderLines1.add(orderLine1);
		orderLines1.add(orderLine2);
		orderLines2.add(orderLine2);
		orderLines2.add(orderLine1);
		order1=new Order(user1,orderLines1);
		order2=new Order(user2,orderLines2);
		order3=new Order(user1,orderLines1);
		order4=new Order(user2,orderLines2);
		order3.setCompleted(true);
		order4.setCompleted(true);
		when(mockOrderRepository.findAll()).thenReturn(Arrays.asList(order1, order2, order3, order4));
//		adminService.setOrderRepository(mockOrderRepository); // Pekar mot mocken
		ReflectionTestUtils.setField(adminService, "orderRepository", mockOrderRepository);
	}
	
	@Test
	void testFindCompletedOrders() {
		List<Order> actualOrders=adminService.findCompletedOrders();
		List<Order> expectedOrders = new ArrayList<Order>();
		expectedOrders.add(order3);
		expectedOrders.add(order4);
	    assertEquals(actualOrders, expectedOrders);
	}

//	@Test
//	void testFindNewOrders() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testMarkOrderAsCompleted() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testCreateProduct() {
//		fail("Not yet implemented");
//	}

}
