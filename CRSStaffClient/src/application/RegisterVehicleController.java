package application;
import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class RegisterVehicleController {
    @FXML
    private TextField year;
    @FXML
    private TextField make;
    @FXML
    private TextField model;
    @FXML
    private TextField color;
    @FXML
    private Label errorMessage;
    
    @FXML protected void handleRegisterButtonAction(ActionEvent event) throws IOException {
        try {
        	Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1234);
        	VehicleInterface vehicle = (VehicleInterface) registry.lookup("vehicle");
        	System.out.println("Connected to RMI server");
        	
        	VehicleEntity vehicleEntity = new VehicleEntity();
        	vehicleEntity.setYear(Integer.parseInt(year.getText()));
        	vehicleEntity.setMake(make.getText());
        	vehicleEntity.setModel(model.getText());
        	vehicleEntity.setColor(color.getText());
        	
        	int createResult = vehicle.createVehicle(vehicleEntity);
        	
        	if (createResult == 0) {
        		errorMessage.setText("Registration failed!");
        		
        		Alert alert = new Alert(AlertType.INFORMATION);
        		alert.setTitle("Failure");
        		alert.setHeaderText(null);
        		alert.setContentText("Registration failed!");
        	} else {
        		Alert alert = new Alert(AlertType.INFORMATION);
        		alert.setTitle("Success");
        		alert.setHeaderText(null);
        		alert.setContentText("Successfully registered!");
        		
            	Main main = new Main();
            	main.changeScene("VehicleList.fxml"); 
        	}
        } catch(Exception e) {
            System.out.printf(e.toString());
        	Main main = new Main();
        	main.changeScene("VehicleList.fxml"); 
        }
    }
    
    @FXML protected void handleBackButtonAction(ActionEvent event) throws IOException {
    	Main main = new Main();
    	main.changeScene("Vehicle.fxml");
    }
}
