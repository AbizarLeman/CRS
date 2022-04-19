package application;

import java.io.Serializable;

public class VehicleEntity implements Serializable {
	private int year;
	private int id;
	private String make;
	private String model;
	private String color;
	private int currentCustomerId;
	
	public VehicleEntity () {
		this.id = 0;
		this.year = 0;
		this.make = "";
		this.model = "";
		this.color = "";
		this.currentCustomerId = 0;
	}
	
	public VehicleEntity (int id, int year, String make, String model, String color, int currentCustomerId) {
		this.id = id;
		this.year = year;
		this.make = make;
		this.model = model;
		this.color = color;
		this.currentCustomerId = currentCustomerId;
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getCurrentCustomerId() {
		return currentCustomerId;
	}

	public void setCurrentCustomerId(int currentCustomerId) {
		this.currentCustomerId = currentCustomerId;
	}
}
