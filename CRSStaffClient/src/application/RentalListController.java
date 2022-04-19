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

public class RentalListController implements Initializable {
    @FXML
    private TableView rentalTable;
    @FXML
    private TableColumn<RentalEntity, Integer> id;
    @FXML
    private TableColumn<RentalEntity, Integer> customerId;
    @FXML
    private TableColumn<RentalEntity, Integer> vehicleId;
    @FXML
    private TableColumn<RentalEntity, String> startDate;
    @FXML
    private TableColumn<RentalEntity, String> endDate;
    @FXML
    private TableColumn<RentalEntity, String> status;
    @FXML
    private Label errorMessage;

    
	private ObservableList<RentalEntity> rentalList;
	
	
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	id.setCellValueFactory(new PropertyValueFactory<RentalEntity, Integer>("id"));
    	customerId.setCellValueFactory(new PropertyValueFactory<RentalEntity, Integer>("customerId"));
    	vehicleId.setCellValueFactory(new PropertyValueFactory<RentalEntity, Integer>("vehicleId"));
    	startDate.setCellValueFactory(new PropertyValueFactory<RentalEntity, String>("startDate"));
    	endDate.setCellValueFactory(new PropertyValueFactory<RentalEntity, String>("endDate"));
    	status.setCellValueFactory(new PropertyValueFactory<RentalEntity, String>("status"));
 
       
    	
        try {
        	Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1234);
        	RentalInterface rental = (RentalInterface) registry.lookup("rental");
        	System.out.println("Connected to RMI server");
        	List<RentalEntity> resultList = rental.getRentals();
        	
        	rentalList = FXCollections.observableArrayList();
        	for (RentalEntity rentalEntity : resultList) {
        	    rentalList.add(rentalEntity);
        	}
        	
        	rentalTable.setItems(rentalList);
        } catch(Exception e) {
            System.out.printf(e.toString());
        }
    }
    
    @FXML protected void handleRentApprovalButtonAction(ActionEvent event) throws IOException {
    	Main main = new Main();
    	RentalEntity selectedRental = (RentalEntity) rentalTable.getSelectionModel().getSelectedItem();
    	
    	if (selectedRental == null) {
    		errorMessage.setText("Please select a rental.");
    	} else {
            try {
            	Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1234);
            	RentalInterface rental = (RentalInterface) registry.lookup("rental");
            	System.out.println("Connected to RMI server");
            	
            	selectedRental.setStatus("In Rent");

            	int updateResult = rental.updateRental(selectedRental);
            	
            	if (updateResult == 0) {
            		errorMessage.setText("Failed to update!");
            	} else {
                	main.changeScene("RentalList.fxml"); 
            	}
            } catch(Exception e) {
                System.out.printf(e.toString());
            	main.changeScene("RentalList.fxml"); 
            }
    	} 
    }
    
    @FXML protected void handleIssueBillButtonAction(ActionEvent event) throws IOException {
    	Main main = new Main();
    	RentalEntity selectedRental = (RentalEntity) rentalTable.getSelectionModel().getSelectedItem();
    	
    	if (selectedRental == null) {
    		errorMessage.setText("Please select a rental.");
    	} else {
        	Stage stage = main.getStage();
        	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("IssueBill.fxml"));
        	Parent root = (Parent) fxmlLoader.load();
        	IssueBillController issueBillController = fxmlLoader.getController();
        	issueBillController.setEntity(selectedRental);
            
        	stage.getScene().setRoot(root); 
    	} 
    }
    
    @FXML protected void handleBackButtonAction(ActionEvent event) throws IOException {
    	Main main = new Main();
    	main.changeScene("Dashboard.fxml");
    }
}
