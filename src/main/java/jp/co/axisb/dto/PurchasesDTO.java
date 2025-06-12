package jp.co.axisb.dto;

import java.sql.Date;
import java.util.List;

public class PurchasesDTO {
	
	private int purchaseId;
	private String purchasedUser;
	private Users users;
	private Date purchasedDate;
	private String destination;
	private boolean cancel;
	private List<PurchaseDetailDTO> purchaseDetailDTO;
	
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
	public Users getUsers() {
		return users;
	}
	public void setUsers(Users users) {
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
	public List<PurchaseDetailDTO> getPurchaseDetailDTO() {
		return purchaseDetailDTO;
	}
	public void setPurchaseDetailDTO(List<PurchaseDetailDTO> purchaseDetailDTO) {
		this.purchaseDetailDTO = purchaseDetailDTO;
	}
	
	

}
