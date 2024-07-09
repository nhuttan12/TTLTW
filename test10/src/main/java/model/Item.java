package model;

public class Item {
	private int id;
	private String name;
	private double price;// gia mua hiển thị trên menu
	private String imageName;
	private double discount;
	private double difference;
	private String discription;	
	private Category category;
	private ImportDetail importDetail;//hỗ trợ lấy số lượng nhập với giá nhập dễ dàng
	private int hidden;//trang thai (0: ẩn, 1: hiện)

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public Item(int id, String name, double price, String imageName, double discount, Category category,
			String discription) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.imageName = imageName;
		this.discount = discount;
		this.category = category;
		this.discription = discription;
	}
	

	public Item(int id, String name, double price, String imageName, double discount, Category category,
			String discription,int hidden) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.imageName = imageName;
		this.discount = discount;
		this.category = category;
		this.discription = discription;
		this.hidden=hidden;
	}
	

	// item de them vao gio hang
	public Item(int id, String name, double price, double discount, Category category) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.discount = discount;
		this.category = category;
	}

	// item de them san pham vao list sp tren web

	public Item(int id) {
		super();
		this.id = id;
	}

	public Item() {
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

	

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", price=" + price + ", imageName=" + imageName + ", discount="
				+ discount + ", difference=" + difference + ", category="
				+ category + ", discription=" + discription + "]";
	}

	public ImportDetail getImportDetail() {
		return importDetail;
	}

	public void setImportDetail(ImportDetail importDetail) {
		this.importDetail = importDetail;
	}

	public int getHidden() {
		return hidden;
	}

	public void setHidden(int hidden) {
		this.hidden = hidden;
	}

	public void setDifference(double difference) {
		this.difference = difference;
	}
}
