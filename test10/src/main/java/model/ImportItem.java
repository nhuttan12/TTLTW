package model;

import java.sql.Date;

//biểu trưng cho đừng đợt nhập hàng về
public class ImportItem {
	private int id; //mã của đợt nhập hàng đó
	private User user; //người nhập
	private Date importDate; //ngày nhập
	public ImportItem(int id, User user, Date importDate) {
		super();
		this.id = id;
		this.user = user;
		this.importDate = importDate;
	}
	public ImportItem() {
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getImportDate() {
		return importDate;
	}
	public void setImportDate(Date importDate) {
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
}
