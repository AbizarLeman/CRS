package application;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.sqlite.SQLiteDataSource;

public class Customer extends UnicastRemoteObject implements CustomerInterface {
	protected SQLiteDataSource dataSource;
	
	public Customer () throws RemoteException {
		dataSource = new SQLiteDataSource();
		dataSource.setUrl("jdbc:sqlite:crs.db");
	}
	
	public CustomerEntity authenticateCustomer(String email, String password) throws RemoteException {
		String sql = "SELECT * FROM customer WHERE (email = ? AND password = ?)";
		
		try {
        	Connection connection = dataSource.getConnection();
        	PreparedStatement statement = connection.prepareStatement(sql);
        	statement.setString(1, email);
        	statement.setString(2, password);
        	 
        	ResultSet result = statement.executeQuery();
        	CustomerEntity customerEntity = new CustomerEntity();
        	
        	
        	while (result.next()) {
        		customerEntity.setId(result.getInt("id"));
        		customerEntity.setEmail(result.getString("email"));
        		customerEntity.setPassword(result.getString("password"));
        		customerEntity.setFullname(result.getString("fullname"));
        		customerEntity.setBalance(result.getFloat("balance"));
        		customerEntity.setIsRenting(result.getBoolean("is_renting"));
        	}
        	
        	if (customerEntity.getId() == 0) {
        		return null;
        	} else {
            	return customerEntity;
        	}
		} catch (Exception e) {
            e.printStackTrace();
    		return null;
		}	
	}
	
	public int createCustomer(CustomerEntity customerEntity) throws RemoteException {
		String sql = "INSERT INTO customer (email, password, fullname, balance, is_renting) "
    			+ "VALUES(?, ?, ?, ?, ?)";
		
		try {
        	Connection connection = dataSource.getConnection();
        	PreparedStatement statement = connection.prepareStatement(sql);
        	
        	statement.setString(1, customerEntity.getEmail());
        	statement.setString(2, customerEntity.getPassword());
        	statement.setString(3, customerEntity.getFullname());
        	statement.setDouble(4, customerEntity.getBalance());
        	statement.setBoolean(5, customerEntity.getIsRenting());
        	
        	return statement.executeUpdate();
		} catch (Exception e) {
            e.printStackTrace();
			return 0;
		}
	}
    
    public List<CustomerEntity> getCustomers() throws RemoteException {
    	String sql = "SELECT * FROM customer";
    	
        try {
        	Connection connection = dataSource.getConnection();
        	Statement statement = connection.createStatement();
        	 
        	ResultSet result = statement.executeQuery(sql);
        	List<CustomerEntity> resultList = new ArrayList<CustomerEntity>();
        	
        	while (result.next()) {
        		CustomerEntity customerEntity = new CustomerEntity();
        		customerEntity.setId(result.getInt("id"));
        		customerEntity.setEmail(result.getString("email"));
        		customerEntity.setPassword(result.getString("password"));
        		customerEntity.setFullname(result.getString("fullname"));
        		customerEntity.setBalance(result.getFloat("balance"));
        		customerEntity.setIsRenting(result.getBoolean("is_renting"));
	            resultList.add(customerEntity);
        	}
	         
        	statement.close();
        	connection.close();
	         
        	return resultList;
        } catch (Exception e) {
            e.printStackTrace();
    		return null;
        }
    }
    
	public CustomerEntity getCustomer(int customerID) throws RemoteException {
		String sql = "SELECT * FROM customer WHERE id = "+ customerID +" LIMIT 1";
		
		try {
        	Connection connection = dataSource.getConnection();
        	Statement statement = connection.createStatement();
        	 
        	ResultSet result = statement.executeQuery(sql);
        	CustomerEntity customerEntity = new CustomerEntity();
        	
        	while (result.next()) {
        		customerEntity.setId(result.getInt("id"));
        		customerEntity.setEmail(result.getString("email"));
        		customerEntity.setPassword(result.getString("password"));
        		customerEntity.setFullname(result.getString("fullname"));
        		customerEntity.setBalance(result.getFloat("balance"));
        		customerEntity.setIsRenting(result.getBoolean("is_renting"));
        	}
        	
        	return customerEntity;
		} catch (Exception e) {
            e.printStackTrace();
    		return null;
		}	
	}
	
	public int updateCustomer(CustomerEntity customerEntity) throws RemoteException {
		String sql = "UPDATE customer "
				+ "SET email = ?, password = ?, fullname = ?, balance = ?, is_renting = ? "
				+ "WHERE id = ?";
		
		try {
        	Connection connection = dataSource.getConnection();
        	PreparedStatement statement = connection.prepareStatement(sql);
        	
        	statement.setString(1, customerEntity.getEmail());
        	statement.setString(2, customerEntity.getPassword());
        	statement.setString(3, customerEntity.getFullname());
        	statement.setDouble(4, customerEntity.getBalance());
        	statement.setBoolean(5, customerEntity.getIsRenting());
        	statement.setInt(6, customerEntity.getId());
        	
        	return statement.executeUpdate();
		} catch (Exception e) {
            e.printStackTrace();
    		return 0;
		}	
	}
	
	public int deleteCustomer(int customerID) throws RemoteException {
		String sql = "DELETE FROM customer WHERE id = ?";
		
		try {
        	Connection connection = dataSource.getConnection();
        	PreparedStatement statement = connection.prepareStatement(sql);

        	statement.setInt(1, customerID);
        	
        	return statement.executeUpdate();
		} catch (Exception e) {
            e.printStackTrace();
    		return 0;
		}	
	}
}
