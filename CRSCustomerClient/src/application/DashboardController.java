package application;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class DashboardController {
    private CustomerEntity customerEntity;
    
	public void setEntity(CustomerEntity customerEntity) {
		this.customerEntity = customerEntity;
	}
	
    @FXML protected void handleVehicleListButtonAction(ActionEvent event) throws IOException {
    	Main main = new Main();
    	Stage stage = main.getStage();
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("VehicleList.fxml"));
    	Parent root = (Parent) fxmlLoader.load();
    	VehicleListController vehicleListController = fxmlLoader.getController();
    	vehicleListController.setEntity(customerEntity);
        
    	stage.getScene().setRoot(root); 
    }
    
    @FXML protected void handleVehicleRentedButtonAction(ActionEvent event) throws IOException {
    	Main main = new Main();
    	Stage stage = main.getStage();
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("VehicleRented.fxml"));
    	Parent root = (Parent) fxmlLoader.load();
    	VehicleRentedController vehicleRentedController = fxmlLoader.getController();
    	vehicleRentedController.setEntity(customerEntity);
        
    	stage.getScene().setRoot(root); 
    }
    
    @FXML protected void handleLogoutButtonAction(ActionEvent event) throws IOException {
    	Main main = new Main();
    	main.changeScene("CustomerClient.fxml");
    }
}
