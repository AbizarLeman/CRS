module CRSStaffClient {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires java.rmi;
	
	opens application to javafx.graphics, javafx.fxml, javafx.base;
}
