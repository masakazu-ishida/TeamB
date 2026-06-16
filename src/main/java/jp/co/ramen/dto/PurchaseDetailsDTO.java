package jp.co.ramen.dto;

public class PurchaseDetailsDTO {

	private int purchase_detail_id;
	private int purchase_id;
	private int item_id;
	private int amount;

	public PurchaseDetailsDTO(int purchase_detail_id, int purchase_id, int item_id, int amount) {
		this.purchase_detail_id = purchase_detail_id;
		this.purchase_id = purchase_id;
		this.item_id = item_id;
		this.amount = amount;
	}

	public PurchaseDetailsDTO() {

	}

	public int getPurchase_detail_id() {
		return purchase_detail_id;
	}

	public void setPurchase_detail_id(int purchase_detail_id) {
		this.purchase_detail_id = purchase_detail_id;
	}

	public int getPurchase_id() {
		return purchase_id;
	}

	public void setPurchase_id(int purchase_id) {
		this.purchase_id = purchase_id;
	}

	public int getItem_id() {
		return item_id;
	}

	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

}
