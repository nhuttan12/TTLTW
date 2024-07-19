package model;

public class Inventory {
	private int id;
	private Item item;
	private int itemId;
	private int quantity;

	public Inventory(int id, Item item, int itemId, int quantity) {
		super();
		this.id = id;
		this.item = item;
		this.itemId = itemId;
		this.quantity = quantity;
	}

	public Inventory() {
	}

	@Override
	public String toString() {
		return "Inventory [id=" + id + ", item=" + item + ", itemId=" + itemId + ", quantity=" + quantity + "]";
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

}
