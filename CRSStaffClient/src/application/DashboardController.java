package application;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class DashboardController {
    @FXML protected void handleCustomerButtonAction(ActionEvent event) throws IOException {
    	Main main = new Main();
    	main.changeScene("Customer.fxml");
    }
    
    @FXML protected void handleVehicleButtonAction(ActionEvent event) throws IOException {
    	Main main = new Main();
    	main.changeScene("Vehicle.fxml");
    }
    
    @FXML protected void handleRentalButtonAction(ActionEvent event) throws IOException {
    	Main main = new Main();
    	main.changeScene("RentalList.fxml");
    }
    
    @FXML protected void handleBillingHistoryButtonAction(ActionEvent event) throws IOException {
    	Main main = new Main();
    	main.changeScene("BillList.fxml");
    }
    
    @FXML protected void handlePaymentListButtonAction(ActionEvent event) throws IOException {
    	Main main = new Main();
    	main.changeScene("PaymentList.fxml");
    }
    
    @FXML protected void handleLogoutButtonAction(ActionEvent event) throws IOException {
    	Main main = new Main();
    	main.changeScene("StaffClient.fxml");
    }
}
