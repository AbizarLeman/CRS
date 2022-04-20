package application;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.List;

import org.sqlite.SQLiteDataSource;

public class CRSServer extends UnicastRemoteObject {
	public CRSServer () throws RemoteException {
	}
	
	public List<CustomerEntity> getCustomers() throws RemoteException {
    	Customer customer = new Customer();
    	List<CustomerEntity> customerList = customer.getCustomers();
    	
		return customerList;
	}
	
	public List<VehicleEntity> getVehicles() throws RemoteException {
    	Vehicle vehicle = new Vehicle();
    	List<VehicleEntity> vehicleList = vehicle.getVehicles();
    	
		return vehicleList;
	}
	

	public static void main(String[] args) {
		try {        	
        	Registry registry = LocateRegistry.createRegistry(1234);
        	registry.rebind("customer", new Customer());
        	registry.rebind("vehicle", new Vehicle());
        	registry.rebind("rental", new Rental());
        	registry.rebind("bill", new Bill());
        	registry.rebind("payment", new Payment());
        	
        	System.out.println("Server started");
        } catch ( Exception e ) {
            e.printStackTrace();
        }
	}

}
