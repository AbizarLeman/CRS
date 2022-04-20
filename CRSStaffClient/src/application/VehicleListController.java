package application;
import java.io.IOException;
import java.net.URL;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import application.CustomerEntity;

public class VehicleListController implements Initializable {
    @FXML
    private TableView vehicleTable;
    @FXML
    private TableView<VehicleEntity> table;
    @FXML
    private TableColumn<VehicleEntity, Integer> year;
    @FXML
    private TableColumn<VehicleEntity, String> make;
    @FXML
    private TableColumn<VehicleEntity, String> model;
    @FXML
    private TableColumn<VehicleEntity, String> color;
    @FXML
    private TableColumn<VehicleEntity, Integer> currentCustomerId;
    @FXML
    private Label errorMessage;

    
	private ObservableList<VehicleEntity> vehicleList;
	
	
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        year.setCellValueFactory(new PropertyValueFactory<VehicleEntity, Integer>("year"));
        make.setCellValueFactory(new PropertyValueFactory<VehicleEntity, String>("make"));
        model.setCellValueFactory(new PropertyValueFactory<VehicleEntity, String>("model"));
        color.setCellValueFactory(new PropertyValueFactory<VehicleEntity, String>("color"));
        currentCustomerId.setCellValueFactory(new PropertyValueFactory<VehicleEntity, Integer>("currentCustomerId"));
    	
        try {
        	Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1234);
        	VehicleInterface vehicle = (VehicleInterface) registry.lookup("vehicle");
        	System.out.println("Connected to RMI server");
        	List<VehicleEntity> resultList = vehicle.getVehicles();
        	
        	vehicleList = FXCollections.observableArrayList();
        	for (VehicleEntity vehicleEntity : resultList) {
        	    vehicleList.add(vehicleEntity);
        	}
        	
        	vehicleTable.setItems(vehicleList);
        } catch(Exception e) {
            System.out.printf(e.toString());
        }
    }
    
    @FXML protected void handleEditButtonAction(ActionEvent event) throws IOException {
    	Main main = new Main();
    	VehicleEntity selectedVehicle = (VehicleEntity) vehicleTable.getSelectionModel().getSelectedItem();
    	
    	if (selectedVehicle == null) {
    		errorMessage.setText("Please select a vehicle.");
    	} else {
        	Stage stage = main.getStage();
        	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditVehicle.fxml"));
        	Parent root = (Parent) fxmlLoader.load();
        	EditVehicleController editVehicleController = fxmlLoader.getController();
        	editVehicleController.setEntity(selectedVehicle);
            
        	stage.getScene().setRoot(root); 
    	} 
    }
    
    @FXML protected void handleDeleteButtonAction(ActionEvent event) throws IOException {
    	Main main = new Main();
    	VehicleEntity selectedVehicle = (VehicleEntity) vehicleTable.getSelectionModel().getSelectedItem();
    	
    	if (selectedVehicle == null) {
    		errorMessage.setText("Please select a vehicle.");
    	} else {
            try {
            	Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1234);
            	VehicleInterface vehicle = (VehicleInterface) registry.lookup("vehicle");
            	System.out.println("Connected to RMI server");
            	
            	int deleteResult = vehicle.deleteVehicle(selectedVehicle.getId());
            	
            	if (deleteResult == 0) {
            		errorMessage.setText("Failed to delete!");
            	} else {
                	main.changeScene("VehicleList.fxml"); 
            	}
            } catch(Exception e) {
                System.out.printf(e.toString());
                errorMessage.setText("Failed to delete!");
            } 
    	} 
    }
    
    @FXML protected void handleBackButtonAction(ActionEvent event) throws IOException {
    	Main main = new Main();
    	main.changeScene("Vehicle.fxml");
    }
}
