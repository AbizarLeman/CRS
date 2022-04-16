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

	public static void main(String[] args) {
		try {        	
        	Registry registry = LocateRegistry.createRegistry(1234);
        	registry.rebind("customer", new Customer());
        	
        	//CustomerInterface customerInterface = (CustomerInterface) UnicastRemoteObject.exportObject(customer, 0);
        	
        	System.out.println("Server started");
        } catch ( Exception e ) {
            e.printStackTrace();
        }
	}

}
