package application;

import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CustomerClientController {
    @FXML
    private Button button;
    @FXML
    private Label errorMessage;
    @FXML
    private TextField loginID;
    @FXML
    private PasswordField password;
    
    @FXML protected void handleLoginButtonAction(ActionEvent event) throws IOException {
    	Main main = new Main();
    	
        if(loginID.getText().isEmpty() || password.getText().isEmpty()) {
        	errorMessage.setText("Please enter your login details.");
        } else {
            try {
            	Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1234);
            	CustomerInterface customer = (CustomerInterface) registry.lookup("customer");
            	System.out.println("Connected to RMI server");
            	CustomerEntity customerEntity = customer.authenticateCustomer(loginID.getText().toString(), password.getText().toString());
                
                if(customerEntity != null) {
                	errorMessage.setText("Success!");
                	
                	Stage stage = main.getStage();
                	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
                	Parent root = (Parent) fxmlLoader.load();
                	DashboardController dashboardController = fxmlLoader.getController();
                	dashboardController.setEntity(customerEntity);
                    
                	stage.getScene().setRoot(root); 
                } else {
                	errorMessage.setText("Wrong username or password!");
                }
            } catch(Exception e) {
                System.out.printf(e.toString());
            }
        }
    }
}
