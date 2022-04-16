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
	
	public int createCustomer(CustomerEntity customerEntity) throws RemoteException {
		String sql = "INSERT INTO customer (email, password, fullname, balance, is_renting) "
    			+ "VALUES(?, ?, ?, ?, ?)";
		
		try {
        	Connection connection = dataSource.getConnection();
        	PreparedStatement statement = connection.prepareStatement(sql);
        	
        	statement.setString(1, customerEntity.email);
        	statement.setString(2, customerEntity.password);
        	statement.setString(3, customerEntity.fullname);
        	statement.setDouble(4, customerEntity.balance);
        	statement.setBoolean(5, customerEntity.isRenting);
        	
        	return statement.executeUpdate();
		} catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
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
        		customerEntity.id = result.getInt("id");
        		customerEntity.email = result.getString("email");
        		customerEntity.password = result.getString("password");
        		customerEntity.fullname = result.getString("fullname");
        		customerEntity.balance  = result.getFloat("balance");
        		customerEntity.isRenting = result.getBoolean("is_renting");
	            resultList.add(customerEntity);
        	}
	         
        	statement.close();
        	connection.close();
	         
        	return resultList;
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
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
        		customerEntity.id = result.getInt("id");
        		customerEntity.email = result.getString("email");
        		customerEntity.password = result.getString("password");
        		customerEntity.fullname = result.getString("fullname");
        		customerEntity.balance  = result.getFloat("balance");
        		customerEntity.isRenting = result.getBoolean("is_renting");
        	}
        	
        	return customerEntity;
		} catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
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
        	
        	statement.setString(1, customerEntity.email);
        	statement.setString(2, customerEntity.password);
        	statement.setString(3, customerEntity.fullname);
        	statement.setDouble(4, customerEntity.balance);
        	statement.setBoolean(5, customerEntity.isRenting);
        	statement.setInt(6, customerEntity.id);
        	
        	return statement.executeUpdate();
		} catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
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
            System.exit(0);
    		return 0;
		}	
	}
}
