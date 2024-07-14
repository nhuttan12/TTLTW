package model;
/**
 * lơp dành cho mã khuyên mãi cho từng sp riêng biệt
 */
public class PromotionItem {
	private int id;
	private Item item;
	private Promotion promotion;
	private int hidden;// 0:an 1:hien

	public PromotionItem() {
		super();
	}

	public PromotionItem(int id, Item item, Promotion promotion, int hidden) {
		super();
		this.id = id;
		this.promotion = promotion;
		this.item = item;
		this.hidden = hidden;
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

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public int getHidden() {
		return hidden;
	}

	public void setHidden(int hidden) {
		this.hidden = hidden;
	}

	@Override
	public String toString() {
		return "PromotionItem [id=" + id + ", item=" + item + ", promotion=" + promotion + ", hidden=" + hidden + "]";
	}
	

}
