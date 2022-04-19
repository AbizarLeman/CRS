package application;

import java.io.Serializable;

public class RentalEntity implements Serializable {
	private int id;
	private int customerId;
	private int vehicleId;
	private String startDate;
	private String endDate;
	private String status;
	
	public RentalEntity () {
		this.id = 0;
		this.customerId = 0;
		this.vehicleId = 0;
		this.startDate = "";
		this.endDate = "";
		this.status = "";
	}
	
	public RentalEntity (int id, int customerId, int vehicleId, String startDate, String endDate, String status) {
		this.id = id;
		this.customerId = customerId;
		this.vehicleId = vehicleId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

    public String getStartDate() {
	        return startDate;
	   }

	public void setStartDate(String startDate) {
	        this.startDate = startDate;
	   }

	public String getEndDate() {
	        return endDate;
	   }

	public void setEndDate(String endDate) {
	        this.endDate = endDate;
	   }

	public String getStatus() {
	        return status;
	   }

	public void setStatus(String status) {
	        this.status = status;
	   }

	public int getCustomerId() {
	        return customerId;
	   }

	public void setCustomerId(int customerId) {
	        this.customerId = customerId;
	   }

	public int getVehicleId() {
	        return vehicleId;
	   }

	public void setVehicleId(int vehicleId) {
	        this.vehicleId = vehicleId;
	}

	}