package model;

public class StatusUser {
	int id;
	String name;
	public StatusUser(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public StatusUser() {
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

}
