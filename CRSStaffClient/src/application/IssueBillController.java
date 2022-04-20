package application;
import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.format.DateTimeFormatter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class IssueBillController {
    @FXML
    private DatePicker issuedOn;
    @FXML
    private DatePicker dueDate;
    @FXML
    private TextField amount;
    @FXML
    private Label errorMessage;
    
    private RentalEntity rentalEntity;

	public void initialize() {}
	
	public void setEntity(RentalEntity rentalEntity) {
		this.rentalEntity = rentalEntity;
	}
    
    @FXML protected void handleBillButtonAction(ActionEvent event) throws IOException {
        try {
        	Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1234);
        	BillInterface bill = (BillInterface) registry.lookup("bill");
        	System.out.println("Connected to RMI server");
        	RentalInterface rental = (RentalInterface) registry.lookup("rental");
        	System.out.println("Connected to RMI server");
        	
        	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
        	String issuedOnString = issuedOn.getValue().format(formatter);
        	String dueDateString = dueDate.getValue().format(formatter);
        	
        	BillEntity billEntity = new BillEntity();
        	billEntity.setRentalId(rentalEntity.getId());
        	billEntity.setCustomerId(rentalEntity.getCustomerId());
        	billEntity.setIssuedOn(issuedOnString);
        	billEntity.setDueDate(dueDateString);
        	billEntity.setAmount(Float.parseFloat(amount.getText()));
        	billEntity.setStatus("Unpaid");        	
        	rentalEntity.setStatus("Unpaid");
        	
        	int updateResult = rental.updateRental(rentalEntity);
        	int createResult = bill.createBill(billEntity);
        	
        	if (createResult == 0 || updateResult == 0) {
        		errorMessage.setText("Failed to issue the bill!");
        	} else {
            	Main main = new Main();
            	main.changeScene("RentalList.fxml"); 
        	}
        } catch(Exception e) {
            System.out.printf(e.toString());
        	Main main = new Main();
        	main.changeScene("RentalList.fxml"); 
        }
    }
    
    @FXML protected void handleBackButtonAction(ActionEvent event) throws IOException {
    	Main main = new Main();
    	main.changeScene("Customer.fxml");
    }
}
