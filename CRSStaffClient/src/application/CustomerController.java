package application;
import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
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
    	Main main = new Main();
    	main.changeScene("CustomerList.fxml");
    }
    
    @FXML protected void handleBackButtonAction(ActionEvent event) throws IOException {
    	Main main = new Main();
    	main.changeScene("Dashboard.fxml");
    }
}
