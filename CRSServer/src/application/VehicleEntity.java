package application;

import java.io.Serializable;

public class VehicleEntity implements Serializable {
	private int year;
	private int id;
	private String make;
	private String model;
	private String color;
	private boolean isRenting;
	
	public VehicleEntity () {
		this.id = 0;
		this.year = 0;
		this.make = "";
		this.model = "";
		this.color = "";
		this.isRenting = false;
	}
	
	public VehicleEntity (int id, int year, String make, String model, String color, boolean isRenting) {
		this.id = id;
		this.year = year;
		this.make = make;
		this.model = model;
		this.color = color;
		this.isRenting = isRenting;
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

	public void setModel(String model) {
		this.model = model;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public boolean getIsRenting() {
		return isRenting;
	}

	public void setIsRenting(boolean isRenting) {
		this.isRenting = isRenting;
	}
}
