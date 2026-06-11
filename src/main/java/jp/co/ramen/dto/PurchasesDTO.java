package jp.co.ramen.dto;

public class PurchasesDTO {

	private String purchase_id;
	private String purchased_user;
	private String purchased_date;
	private String destination;
	private String cancel;

	public PurchasesDTO(String purchase_id, String purchased_user, String purchased_date, String destination,
			String cancel) {
		this.purchase_id = purchase_id;
		this.purchased_user = purchased_user;
		this.purchased_date = purchased_date;
		this.destination = destination;
		this.cancel = cancel;
	}

	public String getPurchase_id() {
		return purchase_id;
	}

	public void setPurchase_id(String purchase_id) {
		this.purchase_id = purchase_id;
	}

	public String getPurchased_user() {
		return purchased_user;
	}

	public void setPurchased_user(String purchased_user) {
		this.purchased_user = purchased_user;
	}

	public String getPurchased_date() {
		return purchased_date;
	}

	public void setPurchased_date(String purchased_date) {
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

}
