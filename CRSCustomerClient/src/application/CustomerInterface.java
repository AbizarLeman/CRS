package application;


import java.rmi.*;
import java.util.List;

public interface CustomerInterface extends Remote {
	public CustomerEntity authenticateCustomer(String email, String password) throws RemoteException;
	public int createCustomer(CustomerEntity customerEntity) throws RemoteException;
	public List<CustomerEntity> getCustomers() throws RemoteException;
	public CustomerEntity getCustomer(int customerID) throws RemoteException;
	public int updateCustomer(CustomerEntity customerEntity) throws RemoteException;
	public int deleteCustomer(int customerID) throws RemoteException;
}
