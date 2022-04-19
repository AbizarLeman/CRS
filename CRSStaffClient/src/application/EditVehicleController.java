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

public class EditVehicleController {
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
    
    private VehicleEntity vehicleEntity;

	public void initialize() {}
	
	public void setEntity(VehicleEntity vehicleEntity) {
		this.vehicleEntity = vehicleEntity;
	    year.setText(String.valueOf(vehicleEntity.getYear()));
	    make.setText(vehicleEntity.getMake());
	    model.setText(vehicleEntity.getModel());
	    color.setText(vehicleEntity.getColor());
	}
	
    @FXML protected void handleSaveButtonAction(ActionEvent event) throws IOException {
        try {
        	Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1234);
        	VehicleInterface vehicle = (VehicleInterface) registry.lookup("vehicle");
        	System.out.println("Connected to RMI server");
        	
        	vehicleEntity.setYear(Integer.parseInt(year.getText()));
        	vehicleEntity.setMake(make.getText());
        	vehicleEntity.setModel(model.getText());
        	vehicleEntity.setColor(color.getText());
        	
        	int updateResult = vehicle.updateVehicle(vehicleEntity);
        	
        	if (updateResult == 0) {
        		errorMessage.setText("Failed to update!");
        	} else {
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
    	main.changeScene("VehicleList.fxml");
    }
}
