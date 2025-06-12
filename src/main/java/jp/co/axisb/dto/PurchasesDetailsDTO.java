package jp.co.axisb.dto;

public class PurchasesDetailsDTO {
	private int purchases_details_id;
	private int purchases_id;
	private int item_id;
	private int amount;
	
	public int getPurchases_details_id() {
		return purchases_details_id;
	}
	public void setPurchases_details_id(int purchases_details_id) {
		this.purchases_details_id = purchases_details_id;
	}
	public int getPurchases_id() {
		return purchases_id;
	}
	public void setPurchases_id(int purchases_id) {
		this.purchases_id = purchases_id;
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
