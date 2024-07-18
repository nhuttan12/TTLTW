package model;

public class OrderDetail {
	int id;
	int oderId;
	int cartId;
	int quantity;
	double price; // tổng giá của cả đơn
	public OrderDetail(int oderId, int cartId, int quantity, double price) {
		super();
		this.oderId = oderId;
		this.cartId = cartId;
		this.quantity = quantity;
		this.price = price;
	}
	
	
	public OrderDetail(int id, int oderId, int cartId) {
		super();
		this.id = id;
		this.oderId = oderId;
		this.cartId = cartId;
	}


	public OrderDetail(int id, int oderId, int cartId, int quantity, double price) {
		super();
		this.id = id;
		this.oderId = oderId;
		this.cartId = cartId;
		this.quantity = quantity;
		this.price = price;
	}

	public OrderDetail() {

	}
	public int getOderId() {
		return oderId;
	}
	public void setOderId(int oderId) {
		this.oderId = oderId;
	}
	public int getCartId() {
		return cartId;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "OrderDetail [oderId=" + oderId + ", cartId=" + cartId + ", quantity=" + quantity + ", price=" + price
				+ "]";
	}
	
	

}
