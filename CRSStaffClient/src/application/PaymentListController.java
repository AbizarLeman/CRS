package application;
import java.io.IOException;
import java.net.URL;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class PaymentListController implements Initializable {
    @FXML
    private TableView paymentTable;
    @FXML
    private TableColumn<PaymentEntity, Integer> id;
    @FXML
    private TableColumn<PaymentEntity, Integer> billId;
    @FXML
    private TableColumn<PaymentEntity, String> paymentDate;
    @FXML
    private TableColumn<PaymentEntity, Float> amount;
    @FXML
    private Label errorMessage;

    
	private ObservableList<PaymentEntity> paymentList;
	
	
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	id.setCellValueFactory(new PropertyValueFactory<PaymentEntity, Integer>("id"));
    	billId.setCellValueFactory(new PropertyValueFactory<PaymentEntity, Integer>("billId"));
    	paymentDate.setCellValueFactory(new PropertyValueFactory<PaymentEntity, String>("paymentDate"));
    	amount.setCellValueFactory(new PropertyValueFactory<PaymentEntity, Float>("amount"));
    	
        try {
        	Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1234);
        	PaymentInterface payment = (PaymentInterface) registry.lookup("payment");
        	System.out.println("Connected to RMI server");
        	List<PaymentEntity> resultList = payment.getPayments();
        	
        	paymentList = FXCollections.observableArrayList();
        	for (PaymentEntity paymentEntity : resultList) {
        		paymentList.add(paymentEntity);
        	}
        	
        	paymentTable.setItems(paymentList);
        } catch(Exception e) {
            System.out.printf(e.toString());
        }
    }
    
    @FXML protected void handleMarkAsPaidButtonAction(ActionEvent event) throws IOException {
    	Main main = new Main();
    	PaymentEntity selectedPayment = (PaymentEntity) paymentTable.getSelectionModel().getSelectedItem();
    	
    	if (selectedPayment == null) {
    		errorMessage.setText("Please select a payment.");
    	} else {
            try {
            	Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1234);
            	BillInterface bill = (BillInterface) registry.lookup("bill");
            	System.out.println("Connected to RMI server");
            	RentalInterface rental = (RentalInterface) registry.lookup("rental");
            	System.out.println("Connected to RMI server");
            	
            	BillEntity billEntity = bill.getBill(selectedPayment.getBillId());
            	RentalEntity rentalEntity = rental.getRental(billEntity.getRentalId());
            	billEntity.setStatus("Paid");
            	rentalEntity.setStatus("In Rent");

            	int updateBillResult = bill.updateBill(billEntity);
            	int updateRentalResult = rental.updateRental(rentalEntity);
            	
            	if (updateBillResult == 0 || updateRentalResult == 0) {
            		errorMessage.setText("Failed to update!");
            	} else {
                	main.changeScene("Dashboard.fxml"); 
            	}
            } catch(Exception e) {
                System.out.printf(e.toString());
            	main.changeScene("Dashboard.fxml"); 
            }
    	} 
    }

    @FXML protected void handleBackButtonAction(ActionEvent event) throws IOException {
    	Main main = new Main();
    	main.changeScene("Dashboard.fxml");
    }
}
