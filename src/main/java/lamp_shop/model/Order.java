package lamp_shop.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // TODO
	private int id;

	private LocalDateTime order_date;

	private boolean completed;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "customer", nullable = false)
	private User customer;

	@OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	@JoinColumn(name = "order_id", nullable = false)
	private List<OrderLine> orderLines = new ArrayList<>();

	public void addOrderLine(OrderLine orderLine) {
		orderLines.add(orderLine);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getOrder_date() {
		return order_date;
	}

	public void setOrder_date(LocalDateTime order_date) {
		this.order_date = order_date;
	}

	public void setOrderLines(List<OrderLine> orderLines) {
		this.orderLines = orderLines;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
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

	public double getTotalAmount() {
		double total = 0;
		Iterator<OrderLine> iterator = orderLines.iterator(); 
		while(iterator.hasNext()) {
			OrderLine orderLine = (OrderLine) iterator.next();
			total +=(orderLine.getQuantity() * orderLine.getProduct().getPrice());
		}
		return total;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", order_date=" + order_date + ", completed=" + completed + ", customer=" + customer
				+ ", orderLines=" + orderLines + "]";
	}
	

	

}
