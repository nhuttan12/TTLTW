package model;
/**
 * model cho 1 khuyên mãi mặc định
 */
public class Promotion {
	private int id,quantity,discount;
	private String name;
	private String description;
	public Promotion(int id, String name, String description, int quantity, int discount) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.quantity = quantity;
		this.discount = discount;
	}
	
	public Promotion(int id) {
		super();
		this.id = id;
	}

	public Promotion() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}

	@Override
	public String toString() {
		return "Promotion [id=" + id + ", quantity=" + quantity + ", discount=" + discount + ", name=" + name
				+ ", description=" + description + "]";
	}
	
	
	
	

}
