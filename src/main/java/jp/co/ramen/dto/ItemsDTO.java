package jp.co.ramen.dto;

public class ItemsDTO {
	private int item_id;
	private String name;
	private String manufacturer;
	private int category_id;
	private String color;
	private int price;
	private int stock;
	private String recommended;

	public ItemsDTO(int item_id, String name, String manufacturer, int category_id, String color, int price, int stock,
			String recommended) {
		super();
		this.item_id = item_id;
		this.name = name;
		this.manufacturer = manufacturer;
		this.category_id = category_id;
		this.color = color;
		this.price = price;
		this.stock = stock;
		this.recommended = recommended;
	}

	public ItemsDTO() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public int getItem_id() {
		return item_id;
	}

	public void setItem_id(int item_id) {
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

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getRecommended() {
		return recommended;
	}

	public void setRecommended(String recommended) {
		this.recommended = recommended;
	}
}
