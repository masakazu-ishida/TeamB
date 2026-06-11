package jp.co.ramen.dto;

public class Items_in_cartDTO {

	private String user_id;
	private String item_id;
	private String amount;
	private String booked_date;

	public Items_in_cartDTO(String user_id, String item_id, String amount, String booked_date) {
		this.user_id = user_id;
		this.item_id = item_id;
		this.amount = amount;
		this.booked_date = booked_date;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getItem_id() {
		return item_id;
	}

	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getBooked_date() {
		return booked_date;
	}

	public void setBooked_date(String booked_date) {
		this.booked_date = booked_date;
	}

}
