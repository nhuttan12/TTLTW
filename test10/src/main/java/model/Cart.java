package model;

import java.util.ArrayList;

public class Cart {
	private int id;
	private int userId;
	private int itemId;
	private int quantity; // số lượng của từng item
	private double totalPrice; // tổng sl của từng loại sp
	private int status; // tình trạng giỏ hàng đã checkout chưa

	
	public Cart() {
		super();
	}

	public Cart(int id, int userId, int itemId, int quantity, double totalPrice) {
		super();
		this.id = id;
		this.userId = userId;
		this.itemId = itemId;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
		this.status = 0;
	}
	public Cart(int id, int userId, int itemId, int quantity, double totalPrice, int status) {
		super();
		this.id = id;
		this.userId = userId;
		this.itemId = itemId;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
		this.status = status;
	}

	public Cart(int userId, int items, int quantity, double totalPrice) {
		super();
		this.userId = userId;
		this.itemId = itemId;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
		this.status = 0;
	}

//////////////////////////////
//	public void addItem(Item item, int slm) {
//		Item i = getItemByID(item.getID());
//		if (i != null) {
//			int newQty = item.getQuantity() + slm;
//			i.setQuantity(newQty);
//		} else {
//			i = new Item();
//			i = item;
////			item.setImageName(img_name);
//			items.add(i);
//		}
//	}

//	public Item getItemByID(int id) {
//		Item searchedItem = null;
//		for (int i = 0; i < items.size(); i++) {
//			Item item = items.get(i);
//			if (item.getId() == id) {
//				searchedItem = item;
//				break;
//			} // end if
//		} // end for
//		return searchedItem;
//	}
//
//	public Item getItem(int index) {
//		if (index >= 0 && index < items.size())
//			return items.get(index);
//		return null;
//	}
//
//	public void removeItemByID(int id) {
//		for (int i = 0; i < items.size(); i++) {
//			Item item = items.get(i);
//			if (item.getId() == id) {
//				removeItem(i);
//				break;
//			} // end if
//		} // end for
//	}
//
//	public void removeItem(int index) {
//		if (index >= 0 && index < items.size())
//			items.remove(index);
//	}
//
//	public int size() {
//		return items.size();
//	}
//
//	public double calculateTotalPrice() {
//		double totalPrice = 0.0;
//		for (int i = 0; i < items.size(); i++) {
//			Item item = items.get(i);
//			totalPrice += (item.getUnitPrice() * item.getQuantityAvailable());
//		}
//		return totalPrice;
//	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Cart [id=" + id + ", userId=" + userId + ", itemId=" + itemId + ", quantity=" + quantity
				+ ", totalPrice=" + totalPrice + ", status=" + status + "]";
	}

	

}
