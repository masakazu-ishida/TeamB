package jp.co.ramen.dto;

public class ItemsDTO {
	private String item_id;
	private String name;
	private String manufacturer;
	private String category_id;
	private String color;
	private String price;
	private String stock;
	private String recommended;

	public ItemsDTO(String item_id, String name, String manufacturer, String category_id, String color, String price,
			String stock, String recommended) {
		this.item_id = item_id;
		this.name = name;
		this.manufacturer = manufacturer;
		this.category_id = category_id;
		this.color = color;
		this.price = price;
		this.stock = stock;
		this.recommended = recommended;
	}

	public String getItem_id() {
		return item_id;
	}

	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getCategory_id() {
		return category_id;
	}

	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

	public String getRecommended() {
		return recommended;
	}

	public void setRecommended(String recommended) {
		this.recommended = recommended;
	}

}
