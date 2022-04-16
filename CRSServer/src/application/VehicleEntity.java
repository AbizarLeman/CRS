package application;

import java.io.Serializable;

public class VehicleEntity implements Serializable {
	public int id;
	public int year;
	public String make;
	public String model;
	public String color;
	public boolean isRenting;
	
	public VehicleEntity () {
	}

}
