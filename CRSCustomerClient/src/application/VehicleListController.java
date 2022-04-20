package application;
import java.io.IOException;
import java.net.URL;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.time.format.DateTimeFormatter;

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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


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
    private DatePicker startDate;
    @FXML
    private DatePicker endDate;
    @FXML
    private Label errorMessage;
    
    private CustomerEntity customerEntity;
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
    
	public void setEntity(CustomerEntity customerEntity) {
		this.customerEntity = customerEntity;
	}
    
    @FXML protected void handleRentButtonAction(ActionEvent event) throws IOException {
    	VehicleEntity selectedVehicle = (VehicleEntity) vehicleTable.getSelectionModel().getSelectedItem();
    	
    	if (selectedVehicle == null || startDate.getValue() == null || endDate.getValue() == null) {
    		errorMessage.setText("Please select a vehicle and fill in the start and end date.");
    	} else {
        	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
        	String startDateString = startDate.getValue().format(formatter);
        	String endDateString = endDate.getValue().format(formatter);
        	
    		if (customerEntity.getId() == selectedVehicle.getCurrentCustomerId()) { 
        		errorMessage.setText("You already rented this vehicle.");
        	} else {
                try {
                	Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1234);
                	RentalInterface rental = (RentalInterface) registry.lookup("rental");
                	System.out.println("Connected to RMI server");
                	VehicleInterface vehicle = (VehicleInterface) registry.lookup("vehicle");
                	System.out.println("Connected to RMI server");
                	
                	RentalEntity rentalEntity = new RentalEntity();
                	rentalEntity.setCustomerId(customerEntity.getId());
                	rentalEntity.setVehicleId(selectedVehicle.getId());
                	rentalEntity.setStartDate(startDateString);
                	rentalEntity.setEndDate(endDateString);
                	rentalEntity.setStatus("Pending Approval");
                	
                	selectedVehicle.setCurrentCustomerId(customerEntity.getId());
                	
                	int createRentalResult = rental.createRental(rentalEntity);
                	int updateVehicleResult = vehicle.updateVehicle(selectedVehicle);
                	
                	if (createRentalResult == 0 || updateVehicleResult == 0) {
                		errorMessage.setText("Failed to request for rent!");
                	} else {
                    	Main main = new Main();
                    	Stage stage = main.getStage();
                    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("VehicleList.fxml"));
                    	Parent root = (Parent) fxmlLoader.load();
                    	VehicleListController vehicleListController = fxmlLoader.getController();
                    	vehicleListController.setEntity(customerEntity);
                        
                    	stage.getScene().setRoot(root); 
                	}
                } catch(Exception e) {
                    System.out.printf(e.toString());
                    errorMessage.setText("Failed to request for rent!");
                }
        	}
    	}
    }
    
    @FXML protected void handleBackButtonAction(ActionEvent event) throws IOException {
    	Main main = new Main();
    	Stage stage = main.getStage();
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
    	Parent root = (Parent) fxmlLoader.load();
    	VehicleRentedController vehicleRentedController = fxmlLoader.getController();
    	vehicleRentedController.setEntity(customerEntity);
        
    	stage.getScene().setRoot(root); 
    }
}
