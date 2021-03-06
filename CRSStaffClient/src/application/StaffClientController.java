package application;

import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class StaffClientController {
    @FXML
    private Button button;
    @FXML
    private Label errorMessage;
    @FXML
    private TextField loginID;
    @FXML
    private PasswordField password;
    
    @FXML protected void handleLoginButtonAction(ActionEvent event) throws IOException {
    	Main main = new Main();
    	
        try {
            if(loginID.getText().toString().equals("staff") && password.getText().toString().equals("123")) {
            	errorMessage.setText("Success!");

            	main.changeScene("Dashboard.fxml");
            } else if(loginID.getText().isEmpty() && password.getText().isEmpty()) {
            	errorMessage.setText("Please enter your data.");
            } else {
            	errorMessage.setText("Wrong username or password!");
            }
        } catch(Exception e) {
            System.out.printf(e.toString());
        }
    }
}
