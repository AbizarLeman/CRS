package application;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class DashboardController {
    @FXML protected void handleCustomerButtonAction(ActionEvent event) throws IOException {
    	Main main = new Main();
    	main.changeScene("Customer.fxml");
    }
    
    @FXML protected void handleLogoutButtonAction(ActionEvent event) throws IOException {
    	Main main = new Main();
    	main.changeScene("StaffClient.fxml");
    }
}
