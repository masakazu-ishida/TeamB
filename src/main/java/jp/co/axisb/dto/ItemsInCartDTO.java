package jp.co.axisb.dto;

import java.sql.Date;

public class ItemsInCartDTO {
	private String userId;
	private int itemId;
	private int amount;
	private Date bookedDate;
	
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
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
	public Date getBooked_date() {
		return booked_date;
	}
	public void setBooked_date(Date booked_date) {
		this.booked_date = booked_date;
	}

}
