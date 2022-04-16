package application;

import java.io.Serializable;

public class CustomerEntity implements Serializable {
	public int id;
	public String email;
	public String password;
	public String fullname;
	public float balance;
	public boolean isRenting;
	
	public CustomerEntity () {
	}

}
