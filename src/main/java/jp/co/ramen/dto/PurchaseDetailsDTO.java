package jp.co.ramen.dto;

public class PurchaseDetailsDTO {

	private String purchase_detail_id;
	private String purchase_id;
	private String item_id;
	private int amount;

	public PurchaseDetailsDTO(String purchase_detail_id, String purchase_id, String item_id, int amount) {
		this.purchase_detail_id = purchase_detail_id;
		this.purchase_id = purchase_id;
		this.item_id = item_id;
		this.amount = amount;
	}

	public PurchaseDetailsDTO() {

	}

	public String getPurchase_detail_id() {
		return purchase_detail_id;
	}

	public void setPurchase_detail_id(String purchase_detail_id) {
		this.purchase_detail_id = purchase_detail_id;
	}

	public String getPurchase_id() {
		return purchase_id;
	}

	public void setPurchase_id(String purchase_id) {
		this.purchase_id = purchase_id;
	}

	public String getItem_id() {
		return item_id;
	}

	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}//コミットしたい

}
