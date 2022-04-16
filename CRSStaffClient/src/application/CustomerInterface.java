package application;

import java.rmi.*;
import java.util.List;

public interface CustomerInterface extends Remote {
	public int createCustomer(CustomerEntity customerEntity) throws RemoteException;
	public List<CustomerEntity> getCustomers() throws RemoteException;
	public CustomerEntity getCustomer(int customerID) throws RemoteException;
	public int updateCustomer(CustomerEntity customerEntity) throws RemoteException;
	public int deleteCustomer(int customerID) throws RemoteException;
}