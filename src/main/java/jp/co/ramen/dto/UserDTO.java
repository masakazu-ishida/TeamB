package jp.co.ramen.dto;

public class UserDTO {
	private int userId;
	private String password;
	private String name;
	private String address;

	// コンストラクタ、Getter、Setter
	public UserDTO() {
	}

	public UserDTO(int userId, String password, String name, String address) {
		this.userId = userId;
		this.password = password;
		this.name = name;
		this.address = address;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
