package model;
/**
 * lơp dành cho mã khuyên mãi cho tất ca cac sp
 */
public class PromotionAll {
	private int id;
	private Promotion promotion;
	private int hidden;//0 an 1:hien
	public PromotionAll(int id, Promotion promotion, int hidden) {
		super();
		this.id = id;
		this.promotion = promotion;
		this.hidden = hidden;
	}
	public PromotionAll() {
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
	public int getHidden() {
		return hidden;
	}
	public void setHidden(int hidden) {
		this.hidden = hidden;
	}
	@Override
	public String toString() {
		return "PromotionAll [id=" + id + ", promotion=" + promotion + ", hidden=" + hidden + "]";
	}
	
	

}
