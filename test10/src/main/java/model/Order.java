package model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Order {
	int orderId;
	int userId;
	String name;
	String phone;
	String address;
	String note;
	String orderDate;
	String deliveriDate;
	int statusOrderId;

	public Order(int orderId, int userId, String name, String phone, String address, String note, String orderDate,
		 int statusOrderId) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.note = note;
		this.orderDate = orderDate;
		this.statusOrderId = statusOrderId;
	}

	public Order(int orderId, int userId, String name, String phone, String address, String note, int statusOrderId) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.note = note;
		this.orderDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());
		this.statusOrderId = statusOrderId;
	}

	public Order(int userId, String name, String phone, String address, String note, String orderDate,
			String deliveriDate, int statusOrderId) {
		super();
		this.userId = userId;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.note = note;
		this.orderDate = orderDate;
		this.deliveriDate = deliveriDate;
		this.statusOrderId = statusOrderId;
	}

	public Order() {
		super();
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getDeliveriDate() {
		return deliveriDate;
	}

	public void setDeliveriDate(String deliveriDate) {
		this.deliveriDate = deliveriDate;
	}

	public int getStatusOrderId() {
		return statusOrderId;
	}

	public void setStatusOrderId(int statusOrderId) {
		this.statusOrderId = statusOrderId;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", userId=" + userId + ", name=" + name + ", phone=" + phone + ", address="
				+ address + ", note=" + note + ", orderDate=" + orderDate + ", deliveriDate=" + deliveriDate
				+ ", statusOrderId=" + statusOrderId + "]";
	}

}