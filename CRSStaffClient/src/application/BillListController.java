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

public class BillListController implements Initializable {
    @FXML
    private TableView billTable;
    @FXML
    private TableColumn<BillEntity, Integer> id;
    @FXML
    private TableColumn<BillEntity, Integer> rentalId;
    @FXML
    private TableColumn<BillEntity, Integer> customerId;
    @FXML
    private TableColumn<BillEntity, String> issuedOn;
    @FXML
    private TableColumn<BillEntity, String> dueDate;
    @FXML
    private TableColumn<BillEntity, Float> amount;
    @FXML
    private TableColumn<BillEntity, String> status;
    @FXML
    private Label errorMessage;

    
	private ObservableList<BillEntity> billList;
	
	
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	id.setCellValueFactory(new PropertyValueFactory<BillEntity, Integer>("id"));
    	rentalId.setCellValueFactory(new PropertyValueFactory<BillEntity, Integer>("rentalId"));
    	customerId.setCellValueFactory(new PropertyValueFactory<BillEntity, Integer>("customerId"));
    	issuedOn.setCellValueFactory(new PropertyValueFactory<BillEntity, String>("issuedOn"));
    	dueDate.setCellValueFactory(new PropertyValueFactory<BillEntity, String>("dueDate"));
    	amount.setCellValueFactory(new PropertyValueFactory<BillEntity, Float>("amount"));
    	status.setCellValueFactory(new PropertyValueFactory<BillEntity, String>("status"));
    	
        try {
        	Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1234);
        	BillInterface bill = (BillInterface) registry.lookup("bill");
        	System.out.println("Connected to RMI server");
        	List<BillEntity> resultList = bill.getBills();
        	
        	billList = FXCollections.observableArrayList();
        	for (BillEntity billEntity : resultList) {
        		billList.add(billEntity);
        	}
        	
        	billTable.setItems(billList);
        } catch(Exception e) {
            System.out.printf(e.toString());
        }
    }
    
    @FXML protected void handleTerminateRentButtonAction(ActionEvent event) throws IOException {
    	Main main = new Main();
    	BillEntity selectedBill = (BillEntity) billTable.getSelectionModel().getSelectedItem();
    	
    	if (selectedBill == null) {
    		errorMessage.setText("Please select a bill.");
    	} else {
            try {
            	Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1234);
            	RentalInterface rental = (RentalInterface) registry.lookup("rental");
            	System.out.println("Connected to RMI server");
            	CustomerInterface customer = (CustomerInterface) registry.lookup("customer");
            	System.out.println("Connected to RMI server");
            	
            	RentalEntity rentalEntity = rental.getRental(selectedBill.getCustomerId());
            	CustomerEntity customerEntity = customer.getCustomer(selectedBill.getCustomerId());
            	rentalEntity.setStatus("Unpaid");
            	customerEntity.setIsRenting(false);

            	int updateRentalResult = rental.updateRental(rentalEntity);
            	int updateCustomerResult = customer.updateCustomer(customerEntity);
            	
            	if (updateRentalResult == 0 || updateCustomerResult == 0) {
            		errorMessage.setText("Failed to update!");
            	} else {
                	main.changeScene("BillList.fxml"); 
            	}
            } catch(Exception e) {
                System.out.printf(e.toString());
            	main.changeScene("BillList.fxml"); 
            }
    	} 
    }

    @FXML protected void handleBackButtonAction(ActionEvent event) throws IOException {
    	Main main = new Main();
    	main.changeScene("Dashboard.fxml");
    }
}
