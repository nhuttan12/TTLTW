package model;

public class Category {
	private int id;
	private String categoryName;
	public Category() {
	}
<<<<<<< HEAD
=======
	
	public Category(int id) {
		super();
		this.id = id;
	}

	public Category(int id, String categoryName) {
		super();
		this.id = id;
		this.categoryName = categoryName;
	}

>>>>>>> origin/code
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
<<<<<<< HEAD
=======
	@Override
	public String toString() {
		return "Category [id=" + id + ", categoryName=" + categoryName + "]";
	}
>>>>>>> origin/code
}
