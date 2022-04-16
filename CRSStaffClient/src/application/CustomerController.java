package application;
import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CustomerController {
    @FXML protected void handleRegisterCustomerButtonAction(ActionEvent event) throws IOException {
    	Main main = new Main();
    	main.changeScene("RegisterCustomer.fxml");
    }
    
    @FXML protected void handleViewCustomerListButtonAction(ActionEvent event) throws IOException {
        try {
        	Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1234);
        	CustomerInterface customer = (CustomerInterface) registry.lookup("customer");
        	System.out.println("Connected to RMI server");
        	List<CustomerEntity> resultList = customer.getCustomers();
        	
        	
            FXMLLoader loader = FXMLLoader.load(getClass().getResource("CustomerList.fxml"));
            CustomerListController customerListController = new CustomerListController(resultList);
            loader.setController(customerListController);
            
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch(Exception e) {
            System.out.printf(e.toString());
        }
    }
    
    @FXML protected void handleBackButtonAction(ActionEvent event) throws IOException {
    	Main main = new Main();
    	main.changeScene("Dashboard.fxml");
    }
}
