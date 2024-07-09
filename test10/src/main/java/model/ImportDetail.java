package model;

import java.util.logging.Logger;

//chi tiết của từng đợt nhập hàng, sẽ đại diện cho từng món hàng trong đợt nhập hàng đó 
public class ImportDetail {
	//mã của chi tiết món hàng nhập
	private int id; 
	//do là đợt nhập hàng được hiển thị dưới dàng 
	//danh sách nhập nên từng chi tiết nhập hàng sẽ phải có kiểu đối tượng như vầy
	private ImportItem impoItem;
	private Item item; //mã của món hàng
	private int quantity; //số lượng nhập về
	private double price; //giá thành nhập về 
	public ImportDetail(int id, ImportItem impoItem, Item item, int quantity, double price) {
		super();
		this.id = id;
		this.impoItem = impoItem;
		this.item = item;
		this.quantity = quantity;
		this.price = price;
	}
	public ImportDetail() {
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public ImportItem getImpoItem() {
		return impoItem;
	}
	public void setImpoItem(ImportItem impoItem) {
		this.impoItem = impoItem;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	@Override
	public String toString() {
		return "ImportDetail [id=" + id + ", impoItem=" + impoItem + ", item=" + item + ", quantity=" + quantity
				+ ", price=" + price + "]";
	}
	
}
