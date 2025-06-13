package jp.co.axisb.dto;

public class PurchasesDetailsDTO {
	
	private int purchasesDetailsId;
	private int purchasesId;
	private int itemId;
	private int amount;
	private PurchasesDTO purchases;
	private ItemsDTO items;
	
	public int getPurchasesDetailsId() {
		return purchasesDetailsId;
	}
	public void setPurchasesDetailsId(int purchasesDetailsId) {
		this.purchasesDetailsId = purchasesDetailsId;
	}
	public int getPurchasesId() {
		return purchasesId;
	}
	public void setPurchasesId(int purchasesId) {
		this.purchasesId = purchasesId;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public PurchasesDTO getPurchases() {
		return purchases;
	}
	public void setPurchases(PurchasesDTO purchases) {
		this.purchases = purchases;
	}
	public ItemsDTO getItems() {
		return items;
	}
	public void setItems(ItemsDTO items) {
		this.items = items;
	}
	
	

}
