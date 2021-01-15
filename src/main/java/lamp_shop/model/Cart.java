package lamp_shop.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@SessionScope
@Component
public class Cart {

	private User customer;

	private List<OrderLine> orderLines = new ArrayList<>();
	
	public Cart() {
	}
	
	public Cart(User customer) {
		this.customer = customer;
	}

	public boolean addToCart(Product product) {
		for (OrderLine orderLine : orderLines) {
			if(orderLine.getProduct().getId()==product.getId()) {
				increaseQuantity(product.getId());
				return true;
			}
		}
		OrderLine orderLine = new OrderLine();
		orderLine.setProduct(product);
		orderLine.setQuantity(1);
		try {
			orderLines.add(orderLine);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public void increaseQuantity(int productId) {
		for (OrderLine orderLine : orderLines) {
			if (orderLine.getProduct().getId() == productId) {
				orderLine.setQuantity(orderLine.getQuantity() + 1);
				break;
			}
		}
	}

	public void decreaseQuantity(int productId) {
		for (int i = 0; i < orderLines.size(); i++) {
			OrderLine orderline = orderLines.get(i);
			if (orderline.getProduct().getId() == productId) {
				orderline.setQuantity(orderline.getQuantity() - 1);
				if (orderline.getQuantity() < 1) {
					orderLines.remove(i);
				}
				break;
			}
		}
	}

	public double getCartTotal() {
		double total = 0;
		Iterator<OrderLine> iterator = orderLines.iterator(); 
		while(iterator.hasNext()) {
			OrderLine orderLine = (OrderLine) iterator.next();
			total +=(orderLine.getQuantity() * orderLine.getProduct().getPrice());
		}
		return total;
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

}
