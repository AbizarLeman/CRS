package application;

import java.io.Serializable;

public class BillEntity implements Serializable {
	private int id;
	private int rentalId;
	private int customerId;
	private String issuedOn;
	private String dueDate;
	private float amount;
	private String status;
	
	public BillEntity () {
		this.id = 0;
		this.rentalId = 0;
		this.customerId = 0;
		this.issuedOn = "";
		this.dueDate = "";
		this.amount = ((float) 0.0);
		this.status = "In Due";
	}
	
	public BillEntity (int id, int rentalId, int customerId, String issuedOn, String dueDate, float amount, String status) {
		this.id = id;
		this.rentalId = rentalId;
		this.customerId = customerId;
		this.issuedOn = issuedOn;
		this.dueDate = dueDate;
		this.amount = amount;
		this.status = status;
	}

	public int getRentalId() {
		return rentalId;
	}

	public void setRentalId(int rentalId) {
		this.rentalId = rentalId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getIssuedOn() {
		return issuedOn;
	}

	public void setIssuedOn(String issuedOn) {
		this.issuedOn = issuedOn;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}
}