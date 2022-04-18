package application;

import java.io.Serializable;

public class CustomerEntity implements Serializable {
	private int id;
	private String email;
	private String password;
	private String fullname;
	private float balance;
	private boolean isRenting;
	
	public CustomerEntity () {
		this.id = 0;
		this.email = "";
		this.password = "";
		this.fullname = "";
		this.balance = (float) 0.0;
		this.isRenting = false;
	}
	
	public CustomerEntity (int id, String email, String password, String fullname, float balance, boolean isRenting) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.fullname = fullname;
		this.balance = balance;
		this.isRenting = isRenting;
	}
	
	public CustomerEntity (int id, String email, String fullname, float balance, boolean isRenting) {
		this.id = id;
		this.email = email;
		this.fullname = fullname;
		this.balance = balance;
		this.isRenting = isRenting;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public boolean getIsRenting() {
		return isRenting;
	}

	public void setIsRenting(boolean isRenting) {
		this.isRenting = isRenting;
	}
}
