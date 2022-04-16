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

public class VehicleController {
    @FXML protected void handleRegisterVehicleButtonAction(ActionEvent event) throws IOException {
    	Main main = new Main();
    	main.changeScene("RegisterVehicle.fxml");
    }
    
    @FXML protected void handleVehicleListButtonAction(ActionEvent event) throws IOException {
    	Main main = new Main();
    	main.changeScene("VehicleList.fxml");
    }	

    @FXML protected void handleBackButtonAction(ActionEvent event) throws IOException {
    	Main main = new Main();
    	main.changeScene("Dashboard.fxml");
    }
}
    