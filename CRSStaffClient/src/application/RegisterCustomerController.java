package application;
import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegisterCustomerController {
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private TextField fullname;
    @FXML
    private Label errorMessage;
    
    @FXML protected void handleRegisterButtonAction(ActionEvent event) throws IOException {
        try {
        	Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1234);
        	CustomerInterface customer = (CustomerInterface) registry.lookup("customer");
        	System.out.println("Connected to RMI server");
        	
        	CustomerEntity customerEntity = new CustomerEntity();
        	customerEntity.setEmail(email.getText());
        	customerEntity.setPassword(password.getText());
        	customerEntity.setFullname(fullname.getText());
        	customerEntity.setBalance((float) 0.0);
        	customerEntity.setIsRenting(false);
        	
        	int createResult = customer.createCustomer(customerEntity);
        	
        	if (createResult == 0) {
        		errorMessage.setText("Registration failed!");
        	} else {
            	Main main = new Main();
            	main.changeScene("CustomerList.fxml"); 
        	}
        } catch(Exception e) {
            System.out.printf(e.toString());
        	Main main = new Main();
        	main.changeScene("CustomerList.fxml"); 
        }
    }
    
    @FXML protected void handleBackButtonAction(ActionEvent event) throws IOException {
    	Main main = new Main();
    	main.changeScene("Customer.fxml");
    }
}
