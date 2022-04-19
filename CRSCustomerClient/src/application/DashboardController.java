package application;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class DashboardController {
    @FXML protected void handleVehicleListButtonAction(ActionEvent event) throws IOException {
    	Main main = new Main();
    	main.changeScene("VehicleList.fxml");
    }
    
    @FXML protected void handleLogoutButtonAction(ActionEvent event) throws IOException {
    	Main main = new Main();
    	main.changeScene("CustomerClient.fxml");
    }
    @FXML protected void handleRentButtonAction(ActionEvent event) throws IOException {
    	Main main = new Main();
    	main.changeScene("Rental.fxml");
    }
}
