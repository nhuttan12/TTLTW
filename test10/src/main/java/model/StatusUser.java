package model;

public class StatusUser {
	int id;
	String name;

	public StatusUser() {
	}

	public StatusUser(int id, String name) {
		super();
		this.id = id;
		this.name = name;
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

	@Override
	public String toString() {
		return "StatusUser [id=" + id + ", name=" + name + "]";
	}
}
