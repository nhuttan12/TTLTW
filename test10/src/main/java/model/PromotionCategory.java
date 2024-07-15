package model;
/**
 * lơp dành cho mã khuyên mãi cho loại sp
 */
public class PromotionCategory {
	private int id;
	private Promotion promotion;
	private Category category;
	private int hidden;//0: an 1:hien
	public PromotionCategory(int id, Promotion promotion, Category category, int hidden) {
		super();
		this.id = id;
		this.promotion = promotion;
		this.category = category;
		this.hidden = hidden;
	}
	public PromotionCategory() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Promotion getPromotion() {
		return promotion;
	}
	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public int getHidden() {
		return hidden;
	}
	public void setHidden(int hidden) {
		this.hidden = hidden;
	}
	@Override
	public String toString() {
		return "PromotionCategory [id=" + id + ", promotion=" + promotion + ", category=" + category + ", hidden="
				+ hidden + "]";
	}
	
	

}
