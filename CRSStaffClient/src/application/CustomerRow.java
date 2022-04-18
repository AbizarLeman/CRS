package application;

import javafx.beans.property.SimpleStringProperty;
 
public class CustomerRow {	
    private SimpleStringProperty id;
    private SimpleStringProperty email;
    private SimpleStringProperty fullname;
    private SimpleStringProperty balance;
    private SimpleStringProperty isRenting;
    
    CustomerRow() {
        this.id = new SimpleStringProperty("");
        this.email = new SimpleStringProperty("");
        this.fullname = new SimpleStringProperty("");
        this.balance = new SimpleStringProperty("");
        this.isRenting = new SimpleStringProperty("");
    }

    CustomerRow(String id, String email, String fullname, String balance, String isRenting) {
        this.id = new SimpleStringProperty(id);
        this.email = new SimpleStringProperty(email);
        this.fullname = new SimpleStringProperty(fullname);
        this.balance = new SimpleStringProperty(balance);
        this.isRenting = new SimpleStringProperty(isRenting);
    }

	public SimpleStringProperty getId() {
		return id;
	}

	public void setId(SimpleStringProperty id) {
		this.id = id;
	}

	public SimpleStringProperty getEmail() {
		return email;
	}

	public void setEmail(SimpleStringProperty email) {
		this.email = email;
	}

	public SimpleStringProperty getFullname() {
		return fullname;
	}

	public void setFullname(SimpleStringProperty fullname) {
		this.fullname = fullname;
	}

	public SimpleStringProperty getBalance() {
		return balance;
	}

	public void setBalance(SimpleStringProperty balance) {
		this.balance = balance;
	}

	public SimpleStringProperty getIsRenting() {
		return isRenting;
	}

	public void setIsRenting(SimpleStringProperty isRenting) {
		this.isRenting = isRenting;
	}
}