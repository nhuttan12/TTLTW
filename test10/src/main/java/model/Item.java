package model;

public class Item {
	private int id;
	private String name;
	private double unitPrice;// gia tren web
	private double price;// gia mua
	private int quantityAvailable;// sl ton kho
	private int quantity;// sl mua
	private String type;
	private String imageName;

//	public Item() {
//		id = name = img_name = "";
//		unitPrice = 0.0;
//		quantityAvailable = 0;
//	}

	public Item(int id, String name, double unitPrice, double price, int quantityAvailable, int quantity, String type,
			String imgName) {
		this.id = id;
		this.name = name;
		this.unitPrice = unitPrice;
		this.price = price;
		this.quantityAvailable = quantityAvailable;
		this.quantity = quantity;
		this.type = type;
		this.imageName = imgName;

	}

	// item de them vao gio hang
	public Item(int id, String name, double price, int quantity) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.quantity = quantity;

	}

	// item de them san pham vao list sp tren web
	public Item(int id, String name, double unitPrice, int quantityAvailable, String type, String imgName) {
		this.id = id;
		this.name = name;
		this.unitPrice = unitPrice;
		this.quantityAvailable = quantityAvailable;
		this.type = type;
		this.imageName = imgName;
	}

	
	public Item(String name, double unitPrice, double price, int quantity, String imageName) {
		super();
		this.name = name;
		this.unitPrice = unitPrice;
		this.price = price;
		this.quantity = quantity;
		this.imageName = imageName;
	}

	public Item(String name, double unitPrice, int quantityAvailable, String type, String imageName) {
		super();
		this.name = name;
		this.unitPrice = unitPrice;
		this.quantityAvailable = quantityAvailable;
		this.type = type;
		this.imageName = imageName;
	}

	public Item(int id) {
		super();
		this.id = id;
	}

	public Item() {
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

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public double getPrice() {
		return this.unitPrice * this.quantity;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantityAvailable() {
		return quantityAvailable;
	}

	public void setQuantityAvailable(int quantityAvailable) {
		this.quantityAvailable = quantityAvailable;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", unitPrice=" + unitPrice + ", price=" + price
				+ ", quantityAvailable=" + quantityAvailable + ", quantity=" + quantity + ", type=" + type
				+ ", img_name=" + imageName + "]";
	}
	

}
