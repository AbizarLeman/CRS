package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
    private static Stage stage;
    
	@Override
	public void start(Stage primaryStage) {
		try {
			Pane root = (Pane)FXMLLoader.load(getClass().getResource("StaffClient.fxml"));
			stage = primaryStage;
			
			Scene scene = new Scene(root, 600, 600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			primaryStage.setTitle("Car Rental System - Staff Login");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
    public void changeScene(String fxml) throws IOException {
        Parent box = FXMLLoader.load(getClass().getResource(fxml));
        stage.getScene().setRoot(box);
    }
	
	public static void main(String[] args) {
		launch(args);
	}
}
