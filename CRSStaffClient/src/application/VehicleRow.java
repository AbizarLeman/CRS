package application;

import javafx.beans.property.SimpleStringProperty;
 
public class VehicleRow {	
    private SimpleStringProperty year;
    private SimpleStringProperty make;
    private SimpleStringProperty model;
    private SimpleStringProperty color;
    private SimpleStringProperty isRenting;
    
    VehicleRow() {
        this.year = new SimpleStringProperty("");
        this.make = new SimpleStringProperty("");
        this.model = new SimpleStringProperty("");
        this.color = new SimpleStringProperty("");
        this.isRenting = new SimpleStringProperty("");
    }

    VehicleRow(String year, String make, String model, String color, String isRenting) {
        this.year = new SimpleStringProperty(year);
        this.make = new SimpleStringProperty(make);
        this.model = new SimpleStringProperty(model);
        this.color = new SimpleStringProperty(color);
        this.isRenting = new SimpleStringProperty(isRenting);
    }

	public SimpleStringProperty getYear() {
		return year;
	}

	public void setYear(SimpleStringProperty year) {
		this.year = year;
	}

	public SimpleStringProperty getMake() {
		return make;
	}

	public void setMake(SimpleStringProperty make) {
		this.make = make;
	}

	public SimpleStringProperty getModel() {
		return model;
	}

	public void setModel(SimpleStringProperty model) {
		this.model = model;
	}

	public SimpleStringProperty getColor() {
		return color;
	}

	public void setColor(SimpleStringProperty color) {
		this.color = color;
	}

	public SimpleStringProperty getIsRenting() {
		return isRenting;
	}

	public void setIsRenting(SimpleStringProperty isRenting) {
		this.isRenting = isRenting;
	}
}