package jp.co.ramen.dto;

import java.time.LocalDate;

public class ItemsInCartDTO {

	private String user_id;
	private int item_id;
	private int amount;
	private LocalDate booked_date;
	private ItemsDTO itemsDto;

	public ItemsInCartDTO(String user_id, int item_id, int amount, LocalDate booked_date, ItemsDTO itemsDto) {
		super();
		this.user_id = user_id;
		this.item_id = item_id;
		this.amount = amount;
		this.booked_date = booked_date;
		this.itemsDto = itemsDto;
	}

	public ItemsInCartDTO() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

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

	public LocalDate getBooked_date() {
		return booked_date;
	}

	public void setBooked_date(LocalDate booked_date) {
		this.booked_date = booked_date;
	}

	public ItemsDTO getItemsDto() {
		return itemsDto;
	}

	public void setItemsDto(ItemsDTO itemsDto) {
		this.itemsDto = itemsDto;
	}

}
