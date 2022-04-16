package application;
import java.io.IOException;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class CustomerListController {
	private final List<CustomerEntity> customerList;
	
	public CustomerListController(List<CustomerEntity> customerList) {
	    this.customerList = customerList ;
	}
}
