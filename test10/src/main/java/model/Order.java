package model;

public class Order {
	int orderId;
	int shoppingCartId;
	String date;
	double orderPrice;
	int status;
	public Order(int orderId, int shoppingCartId, String date, double orderPrice, int status) {
		super();
		this.orderId = orderId;
		this.shoppingCartId = shoppingCartId;
		this.date = date;
		this.orderPrice = orderPrice;
		this.status = status;
	}
	
	public Order() {
		super();
	}

	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getShoppingCartId() {
		return shoppingCartId;
	}
	public void setShoppingCartId(int shoppingCartId) {
		this.shoppingCartId = shoppingCartId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public double getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(double orderPrice) {
		this.orderPrice = orderPrice;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", shoppingCartId=" + shoppingCartId + ", date=" + date + ", orderPrice="
				+ orderPrice + ", status=" + status + "]";
	}
	

}