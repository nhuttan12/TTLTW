package model;

public class StatusOrder {
	int id;
	String name;

	public StatusOrder() {
	}

	public StatusOrder(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
<<<<<<< HEAD
	public StatusOrder() {
	}
=======

>>>>>>> fd416312b978d75da5da4228c9118ebfeff927a3
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
		return "StatusOrder [id=" + id + ", name=" + name + "]";
	}

}
