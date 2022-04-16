package application;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class RegisterCustomerController {
    @FXML protected void handleRegisterButtonAction(ActionEvent event) throws IOException {

    }
    
    @FXML protected void handleBackButtonAction(ActionEvent event) throws IOException {
    	Main main = new Main();
    	main.changeScene("Customer.fxml");
    }
}
