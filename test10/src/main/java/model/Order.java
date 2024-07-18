package model;

public class Order {
	int orderId;
	int userId;
	double totalPrice; // hỗ trợ hiển thị tổng tiền của hoá đơn
	String name;
	String phone;
	String address;
	String note;
	String orderDate;
	String deliveriDate;
	int statusOrderId;
	StatusOrder statusOrder;
	int dateDifference;

	public Order(int orderId, int userId, String name, String phone, String address, String note, String orderDate,
			String deliveriDate, int statusOrderId) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.note = note;
		this.orderDate = orderDate;
		this.deliveriDate = deliveriDate;
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

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public StatusOrder getStatusOrder() {
		return statusOrder;
	}

	public void setStatusOrder(StatusOrder statusOrder) {
		this.statusOrder = statusOrder;
	}

	public int getDateDifference() {
		return dateDifference;
	}

	public void setDateDifference(int dateDifference) {
		this.dateDifference = dateDifference;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", userId=" + userId + ", totalPrice=" + totalPrice + ", name=" + name
				+ ", phone=" + phone + ", address=" + address + ", note=" + note + ", orderDate=" + orderDate
				+ ", deliveriDate=" + deliveriDate + ", statusOrderId=" + statusOrderId + ", statusOrder=" + statusOrder
				+ "]";
	}


}