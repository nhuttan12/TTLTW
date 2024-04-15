package model;

import java.util.ArrayList;

public class ShoppingCart {
	private int id;
	User user;
	private ArrayList<Item> items;

	
	public ShoppingCart() {
		items = new ArrayList<Item>();
	}
	public ShoppingCart(User user) {
		items = new ArrayList<Item>();
		this.user = user;
	}
	public ShoppingCart(int id) {
		this.id = id;
		items = new ArrayList<Item>();
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

	public Item getItemByID(int id) {
		Item searchedItem = null;
		for (int i = 0; i < items.size(); i++) {
			Item item = items.get(i);
			if (item.getId()== id) {
				searchedItem = item;
				break;
			} // end if
		} // end for
		return searchedItem;
	}

	public Item getItem(int index) {
		if (index >= 0 && index < items.size())
			return items.get(index);
		return null;
	}

	public void removeItemByID(int id) {
		for (int i = 0; i < items.size(); i++) {
			Item item = items.get(i);
			if (item.getId()==id) {
				removeItem(i);
				break;
			} // end if
		} // end for
	}

	public void removeItem(int index) {
		if (index >= 0 && index < items.size())
			items.remove(index);
	}

	public int size() {
		return items.size();
	}

	public double calculateTotalPrice() {
		double totalPrice = 0.0;
		for (int i = 0; i < items.size(); i++) {
			Item item = items.get(i);
			totalPrice += (item.getUnitPrice() * item.getQuantityAvailable());
		}
		return totalPrice;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public ArrayList<Item> getItems() {
		return items;
	}
	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}
	

}
