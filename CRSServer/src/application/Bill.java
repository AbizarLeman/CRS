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

public class Bill extends UnicastRemoteObject implements BillInterface {
	protected SQLiteDataSource dataSource;
	
	public Bill () throws RemoteException {
		dataSource = new SQLiteDataSource();
		dataSource.setUrl("jdbc:sqlite:crs.db");
	}
	
	public int createBill(BillEntity billEntity) throws RemoteException {
		String sql = "INSERT INTO bill (rental_id, customer_id, issued_on, due_date, amount, status) "
    			+ "VALUES(?, ?, ?, ?, ?, ?)";
		
		try {
        	Connection connection = dataSource.getConnection();
        	PreparedStatement statement = connection.prepareStatement(sql);
        	
        	statement.setInt(1, billEntity.getRentalId());
        	statement.setInt(2, billEntity.getCustomerId());
        	statement.setString(3, billEntity.getIssuedOn());
        	statement.setString(4, billEntity.getDueDate());
        	statement.setFloat(5, billEntity.getAmount());
        	statement.setString(6, billEntity.getStatus());
        	
        	return statement.executeUpdate();
		} catch (Exception e) {
            e.printStackTrace();
			return 0;
		}
	}
    
    public List<BillEntity> getBills() throws RemoteException {
    	String sql = "SELECT * FROM bill";
    	
        try {
        	Connection connection = dataSource.getConnection();
        	Statement statement = connection.createStatement();
        	 
        	ResultSet result = statement.executeQuery(sql);
        	List<BillEntity> resultList = new ArrayList<BillEntity>();
        	
        	while (result.next()) {
        		BillEntity billEntity = new BillEntity();
        		billEntity.setId(result.getInt("id"));
        		billEntity.setRentalId(result.getInt("rental_id"));
        		billEntity.setCustomerId(result.getInt("customer_id"));
        		billEntity.setIssuedOn(result.getString("issued_on"));
        		billEntity.setDueDate(result.getString("due_date"));
        		billEntity.setAmount(result.getFloat("amount"));
        		billEntity.setStatus(result.getString("status"));
	            resultList.add(billEntity);
        	}
	         
        	statement.close();
        	connection.close();
	         
        	return resultList;
        } catch (Exception e) {
            e.printStackTrace();
    		return null;
        }
    }

	@Override
	public BillEntity getBill(int billID) throws RemoteException {
		String sql = "SELECT * FROM bill WHERE id = "+ billID +" LIMIT 1";
		
		try {
        	Connection connection = dataSource.getConnection();
        	Statement statement = connection.createStatement();
        	 
        	ResultSet result = statement.executeQuery(sql);
        	BillEntity billEntity = new BillEntity();
        	
        	while (result.next()) {
        		billEntity.setId(result.getInt("id"));
        		billEntity.setRentalId(result.getInt("rental_id"));
        		billEntity.setCustomerId(result.getInt("customer_id"));
        		billEntity.setIssuedOn(result.getString("issued_on"));
        		billEntity.setDueDate(result.getString("due_date"));
        		billEntity.setAmount(result.getFloat("amount"));
        		billEntity.setStatus(result.getString("status"));
        	}
        	
        	return billEntity;
		} catch (Exception e) {
            e.printStackTrace();
    		return null;
		}
	}

	@Override
	public int updateBill(BillEntity billEntity) throws RemoteException {
		String sql = "UPDATE bill "
				+ "SET rental_id = ?, customer_id = ?, issued_on = ?, due_date = ?, amount = ?, status = ? "
				+ "WHERE id = ?";
		
		try {
        	Connection connection = dataSource.getConnection();
        	PreparedStatement statement = connection.prepareStatement(sql);
        	
        	statement.setInt(1, billEntity.getRentalId());
        	statement.setInt(2, billEntity.getCustomerId());
        	statement.setString(3, billEntity.getIssuedOn());
        	statement.setString(4, billEntity.getDueDate());
        	statement.setFloat(5, billEntity.getAmount());
        	statement.setString(6, billEntity.getStatus());
        	statement.setInt(7, billEntity.getId());
        	
        	return statement.executeUpdate();
		} catch (Exception e) {
            e.printStackTrace();
    		return 0;
		}	
	}
}
