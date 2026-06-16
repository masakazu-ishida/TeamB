package jp.co.ramen.dto;

import java.sql.Date;

public class PurchasesDTO {

	private int purchase_id;
	private String purchased_user;
	private Date purchased_date;
	private String destination;
	private String cancel;
	private ItemsDTO itemsDto;
	private PurchaseDetailsDTO purchaseDetaillsDto;

	public PurchasesDTO() {

	}

	public PurchasesDTO(int purchase_id, String purchased_user, Date purchased_date, String destination,
			String cancel) {
		this.purchase_id = purchase_id;
		this.purchased_user = purchased_user;
		this.purchased_date = purchased_date;
		this.destination = destination;
		this.cancel = cancel;
	}

	public int getPurchase_id() {
		return purchase_id;
	}

	public void setPurchase_id(int purchase_id) {
		this.purchase_id = purchase_id;
	}

	public String getPurchased_user() {
		return purchased_user;
	}

	public void setPurchased_user(String purchased_user) {
		this.purchased_user = purchased_user;
	}

	public Date getPurchased_date() {
		return purchased_date;
	}

	public void setPurchased_date(Date purchased_date) {
		this.purchased_date = purchased_date;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getCancel() {
		return cancel;
	}

	public void setCancel(String cancel) {
		this.cancel = cancel;
	}

	public ItemsDTO getItemsDto() {
		return itemsDto;
	}

	public void setItemsDto(ItemsDTO itemsDto) {
		this.itemsDto = itemsDto;
	}

	public PurchaseDetailsDTO getPurchaseDetaillsDto() {
		return purchaseDetaillsDto;
	}

	public void setPurchaseDetaillsDto(PurchaseDetailsDTO purchaseDetaillsDto) {
		this.purchaseDetaillsDto = purchaseDetaillsDto;
	}

}
