package in.spring4buddies.poc.data;

import java.io.Serializable;

public class Order implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String orderNumber;
	private String productId;
	private double amount;

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Order [orderNumber=" + orderNumber + ", productId=" + productId + ", amount=" + amount + "]";
	}
}