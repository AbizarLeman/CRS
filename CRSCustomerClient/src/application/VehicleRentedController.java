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


public class VehicleRentedController implements Initializable {
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
    }
    
	public void setEntity(CustomerEntity customerEntity) {
		this.customerEntity = customerEntity;
		
        year.setCellValueFactory(new PropertyValueFactory<VehicleEntity, Integer>("year"));
        make.setCellValueFactory(new PropertyValueFactory<VehicleEntity, String>("make"));
        model.setCellValueFactory(new PropertyValueFactory<VehicleEntity, String>("model"));
        color.setCellValueFactory(new PropertyValueFactory<VehicleEntity, String>("color"));
        currentCustomerId.setCellValueFactory(new PropertyValueFactory<VehicleEntity, Integer>("currentCustomerId"));
 
    	
        try {
        	Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1234);
        	VehicleInterface vehicle = (VehicleInterface) registry.lookup("vehicle");
        	System.out.println("Connected to RMI server");
        	List<VehicleEntity> resultList = vehicle.getCustomerVehicles(customerEntity.getId());
        	
        	vehicleList = FXCollections.observableArrayList();
        	for (VehicleEntity vehicleEntity : resultList) {
        	    vehicleList.add(vehicleEntity);
        	}
        	
        	vehicleTable.setItems(vehicleList);
        } catch(Exception e) {
            System.out.printf(e.toString());
        }
	}
   
    @FXML protected void handleBackButtonAction(ActionEvent event) throws IOException {
    	Main main = new Main();
    	Stage stage = main.getStage();
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
    	Parent root = (Parent) fxmlLoader.load();
    	DashboardController dashboardController = fxmlLoader.getController();
    	dashboardController.setEntity(customerEntity);
        
    	stage.getScene().setRoot(root); 
    }
}
