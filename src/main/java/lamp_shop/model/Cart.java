package lamp_shop.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@SessionScope
@Component
public class Cart {

	public Cart() {}
	
	public Cart(User customer) {
		this.customer = customer;
	}

	private User customer; 
	
	private List<OrderLine> orderLines=new ArrayList<>();
	
	public boolean addToCart(Product product) {
		OrderLine orderLine = new OrderLine();
		orderLine.setProduct(product);
		orderLine.setQuantity(1);
		System.out.println(product);
		try {
			System.out.println(orderLine);
			orderLines.add(orderLine);
			System.out.println(orderLines);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("catch - exception");
			return false;
		}
	}

	public User getCustomer() {
		return customer;
	}

	public void setCustomer(User customer) {
		this.customer = customer;
	}

	public List<OrderLine> getOrderLines() {
		return orderLines;
	}

	public void setOrderLines(List<OrderLine> orderLines) {
		this.orderLines = orderLines;
	}

	@Override
	public String toString() {
		return "Cart [customer=" + customer + ", orderLines=" + orderLines + "]";
	}

	
//	add to cart
//	remove from cart
//	increase quantity
//	decrease quantity
//	orderline total
//	cart total
	


	
	
	
	
}
