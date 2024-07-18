package model;

public class OrderDetail {
	int id;
	int oderId;
	int itemId;
	int quantity;
	Item item;
	double price; // tổng giá của cả đơn
	public OrderDetail(int oderId, int itemId, int quantity, double price) {
		super();
		this.oderId = oderId;
		this.itemId = itemId;
		this.quantity = quantity;
		this.price = price;
	}
	
	public OrderDetail(int id, int oderId, int itemId, int quantity, double price) {
		super();
		this.id = id;
		this.oderId = oderId;
		this.itemId = itemId;
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
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
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
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	@Override
	public String toString() {
		return "OrderDetail [oderId=" + oderId + ", itemId=" + itemId + ", quantity=" + quantity + ", price=" + price
				+ "]";
	}
	
	

}
