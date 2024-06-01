package model;

public class Item {
	private int id;
	private String name;
	private double price;// gia mua
	private String imageName;
	private double discount;
	private double difference;
	private double importPrice;
	private int category_id;
	private String discription;

//	public Item() {
//		id = name = img_name = "";
//		unitPrice = 0.0;
//		quantityAvailable = 0;
//	}

	public Item(int id, String name, double price, String imageName, double discount, int category_id,
			String discription) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.imageName = imageName;
		this.discount = discount;
		this.category_id = category_id;
		this.discription = discription;
	}

	// item de them vao gio hang
	public Item(int id, String name, double price, double discount, int category_id) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.discount = discount;
		this.category_id = category_id;
	}

	// item de them san pham vao list sp tren web

	public Item(int id) {
		super();
		this.id = id;
	}

	public Item() {
		super();
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
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

	

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public double getDifference() {
		return difference;
	}

	public void setDifference(double difference) {
		this.difference = difference;
	}

	

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", price=" + price + ", imageName=" + imageName + ", discount="
				+ discount + ", difference=" + difference + ", importPrice=" + importPrice + ", category_id="
				+ category_id + ", discription=" + discription + "]";
	}

	public double getImportPrice() {
		return importPrice;
	}

	public void setImportPrice(double importPrice) {
		this.importPrice = importPrice;
	}

}
