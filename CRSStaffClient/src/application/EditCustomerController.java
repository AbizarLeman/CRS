package application;
import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditCustomerController {
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private TextField fullname;
    @FXML
    private Label errorMessage;
    
    private CustomerEntity customerEntity;

	public void initialize() {}
	
	public void setEntity(CustomerEntity customerEntity) {
		this.customerEntity = customerEntity;
	    email.setText(customerEntity.getEmail());
	    password.setText(customerEntity.getPassword());
	    fullname.setText(customerEntity.getFullname());
	}
	
    @FXML protected void handleSaveButtonAction(ActionEvent event) throws IOException {
        try {
        	Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1234);
        	CustomerInterface customer = (CustomerInterface) registry.lookup("customer");
        	System.out.println("Connected to RMI server");
        	
        	customerEntity.setEmail(email.getText());
        	customerEntity.setPassword(password.getText());
        	customerEntity.setFullname(fullname.getText());
        	
        	int updateResult = customer.updateCustomer(customerEntity);
        	
        	if (updateResult == 0) {
        		errorMessage.setText("Failed to update!");
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
    	main.changeScene("CustomerList.fxml");
    }
}
