package jp.co.axisb.dto;

import java.sql.Date;
import java.util.List;

public class PurchasesDTO {

	private int purchaseId;
	private String userId;

	public List<PurchasesDetailsDTO> getPurchasesDetails() {
		return purchasesDetails;
	}

	public void setPurchasesDetails(List<PurchasesDetailsDTO> purchasesDetails) {
		this.purchasesDetails = purchasesDetails;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	private String purchasedUser;
	private UsersDTO users;
	private Date purchasedDate;
	private String destination;
	private boolean cancel;
	private List<PurchasesDetailsDTO> purchasesDetails;

	public int getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(int purchaseId) {
		this.purchaseId = purchaseId;
	}

	public String getPurchasedUser() {
		return purchasedUser;
	}

	public void setPurchasedUser(String purchasedUser) {
		this.purchasedUser = purchasedUser;
	}

	public UsersDTO getUsers() {
		return users;
	}

	public void setUsers(UsersDTO users) {
		this.users = users;
	}

	public Date getPurchasedDate() {
		return purchasedDate;
	}

	public void setPurchasedDate(Date purchasedDate) {
		this.purchasedDate = purchasedDate;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public boolean isCancel() {
		return cancel;
	}

	public void setCancel(boolean cancel) {
		this.cancel = cancel;
	}

	public List<PurchasesDetailsDTO> getPurchaseDetailDTO() {
		return purchasesDetails;
	}

	public void setPurchaseDetailDTO(List<PurchasesDetailsDTO> purchasesDetails) {
		this.purchasesDetails = purchasesDetails;
	}

}
