package lamp_shop.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CartTest {

	Cart cart;
	Product product1;
	Product product2;
	Category category;
	
	@BeforeEach
	void setUp() throws Exception {
		cart = new Cart();
		category = new Category();
		category.setId(1);
		category.setName("category");
		product1 = new Product("Lamp 1", 50.0, category);
		product1.setId(1);
		product2 = new Product("Lamp 2", 100.0, category);
		product2.setId(2);
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Test
	void testAddFirstItemToCart() {
		cart.addToCart(product1);
		assertNotNull(cart.getOrderLines().get(0));
	}
	
	@Test
	void testAddTwoSameItemsToCart() {
		cart.addToCart(product1);
		cart.addToCart(product1);
		assertTrue(cart.getOrderLines().get(0).getQuantity()==2);
		assertEquals(1, cart.getOrderLines().size());
		assertNotEquals(2, cart.getOrderLines().size());
	}
	
	@Test
	void testAddTwoDifferentItemsToCart() {
		cart.addToCart(product1);
		cart.addToCart(product2);
		assertEquals(2, cart.getOrderLines().size());
		assertNotEquals(1, cart.getOrderLines().size());
	}
	
	@Test
	void testDecreaseQuantityToZero() {
		cart.addToCart(product1);
		cart.decreaseQuantity(1);
		assertEquals(0, cart.getOrderLines().size());
		assertNotEquals(2, cart.getOrderLines().size());
	}
	
	@Test
	void testDecreaseQuantityByOne() {
		cart.addToCart(product1);
		cart.addToCart(product1);
		cart.decreaseQuantity(1);
		assertEquals(1, cart.getOrderLines().get(0).getQuantity());
		assertNotEquals(2, cart.getOrderLines().get(0).getQuantity());
	}
	
	@Test
	void testGetCartTotal() {
		cart.addToCart(product1);
		cart.addToCart(product2);
		cart.addToCart(product2);
		assertEquals(250, cart.getCartTotal());
		assertNotEquals(200, cart.getCartTotal());
	}
	
	@Test
	void testGetCartTotalAfterDecrease() {
		cart.addToCart(product1);
		cart.addToCart(product2);
		cart.decreaseQuantity(2);
		assertEquals(50, cart.getCartTotal());
		assertNotEquals(200, cart.getCartTotal());
	}

	
}
