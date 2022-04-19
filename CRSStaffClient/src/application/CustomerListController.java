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

public class CustomerListController implements Initializable {
    @FXML
    private TableView customerTable;
    @FXML
    private TableView<CustomerEntity> table;
    @FXML
    private TableColumn<CustomerEntity, Integer> id;
    @FXML
    private TableColumn<CustomerEntity, String> email;
    @FXML
    private TableColumn<CustomerEntity, String> fullname;
    @FXML
    private TableColumn<CustomerEntity, Float> balance;
    @FXML
    private TableColumn<CustomerEntity, Boolean> isRenting;
    @FXML
    private Label errorMessage;

    
	private ObservableList<CustomerEntity> customerList;
	
	
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        id.setCellValueFactory(new PropertyValueFactory<CustomerEntity, Integer>("id"));
        email.setCellValueFactory(new PropertyValueFactory<CustomerEntity, String>("email"));
        fullname.setCellValueFactory(new PropertyValueFactory<CustomerEntity, String>("fullname"));
        balance.setCellValueFactory(new PropertyValueFactory<CustomerEntity, Float>("balance"));
        isRenting.setCellValueFactory(new PropertyValueFactory<CustomerEntity, Boolean>("isRenting"));
        
		//CustomerEntity customerEntity1 = new CustomerEntity();
		//CustomerEntity customerEntity2 = new CustomerEntity();
    	//customerEntity1.setId(1);
    	//customerEntity1.setEmail("testing1@testing.com");
    	//customerEntity1.setFullname("Test Account1");
    	//customerEntity1.setBalance((float) 0.0);
    	//customerEntity1.setIsRenting(false);
    	//customerEntity2.setId(2);
    	//customerEntity2.setEmail("testing2@testing.com");
    	//customerEntity2.setFullname("Test Account2");
    	//customerEntity2.setBalance((float) 0.0);
    	//customerEntity2.setIsRenting(false);
    	
        try {
        	Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1234);
        	CustomerInterface customer = (CustomerInterface) registry.lookup("customer");
        	System.out.println("Connected to RMI server");
        	List<CustomerEntity> resultList = customer.getCustomers();
        	
        	customerList = FXCollections.observableArrayList();
        	for (CustomerEntity customerEntity : resultList) {
        	    customerList.add(customerEntity);
        	}
        	
        	//customerList = FXCollections.observableArrayList(customerEntity1, customerEntity2);
        	customerTable.setItems(customerList);
        } catch(Exception e) {
            System.out.printf(e.toString());
        }
    }
    
    @FXML protected void handleEditButtonAction(ActionEvent event) throws IOException {
    	Main main = new Main();
    	CustomerEntity selectedCustomer = (CustomerEntity) customerTable.getSelectionModel().getSelectedItem();
    	
    	if (selectedCustomer == null) {
    		errorMessage.setText("Please select a customer.");
    	} else {
        	Stage stage = main.getStage();
        	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditCustomer.fxml"));
        	Parent root = (Parent) fxmlLoader.load();
        	EditCustomerController editCustomerController = fxmlLoader.getController();
        	editCustomerController.setEntity(selectedCustomer);
            
        	stage.getScene().setRoot(root); 
    	} 
    }
    
    @FXML protected void handleDeleteButtonAction(ActionEvent event) throws IOException {
    	Main main = new Main();
    	CustomerEntity selectedCustomer = (CustomerEntity) customerTable.getSelectionModel().getSelectedItem();
    	
    	if (selectedCustomer == null) {
    		errorMessage.setText("Please select a customer.");
    	} else {
            try {
            	Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1234);
            	CustomerInterface customer = (CustomerInterface) registry.lookup("customer");
            	System.out.println("Connected to RMI server");
            	
            	int deleteResult = customer.deleteCustomer(selectedCustomer.getId());
            	
            	if (deleteResult == 0) {
            		errorMessage.setText("Failed to delete!");
            	} else {
                	main.changeScene("CustomerList.fxml"); 
            	}
            } catch(Exception e) {
                System.out.printf(e.toString());
                errorMessage.setText("Failed to delete!");
            } 
    	} 
    }
    
    @FXML protected void handleBackButtonAction(ActionEvent event) throws IOException {
    	Main main = new Main();
    	main.changeScene("Customer.fxml");
    }
}
