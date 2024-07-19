package model;

import java.sql.Date;

//biểu trưng cho đừng đợt nhập hàng về
public class ImportItem {
	private int id; // mã của đợt nhập hàng đó
	private User user; // người nhập
	private String importDate; // ngày nhập
	private ImportDetail importDetail;
	private int totalQuantity;
	private int totalItem;
	private int totalPrice;

	public ImportItem(int id, User user, String importDate, ImportDetail importDetail, int totalQuantity, int totalItem,
			int totalPrice) {
		super();
		this.id = id;
		this.user = user;
		this.importDate = importDate;
		this.importDetail = importDetail;
		this.setTotalQuantity(totalQuantity);
		this.setTotalItem(totalItem);
		this.setTotalPrice(totalPrice);
	}

	public ImportItem() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImportDate() {
		return importDate;
	}

	public void setImportDate(String importDate) {
		this.importDate = importDate;
	}

	@Override
	public String toString() {
		return "ImportItem [id=" + id + ", user=" + user + ", importDate=" + importDate + "]";
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ImportDetail getImportDetail() {
		return importDetail;
	}

	public void setImportDetail(ImportDetail importDetail) {
		this.importDetail = importDetail;
	}

	public int getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public int getTotalItem() {
		return totalItem;
	}

	public void setTotalItem(int totalItem) {
		this.totalItem = totalItem;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
}
