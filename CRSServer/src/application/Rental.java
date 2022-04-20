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

public class Rental extends UnicastRemoteObject implements RentalInterface {
	protected SQLiteDataSource dataSource;
	
	public Rental () throws RemoteException {
		dataSource = new SQLiteDataSource();
		dataSource.setUrl("jdbc:sqlite:crs.db");
	}
	
	public int createRental(RentalEntity rentalEntity) throws RemoteException {
		String sql = "INSERT INTO rental (customer_id, vehicle_id, start_date, end_date, status) "
    			+ "VALUES(?, ?, ?, ?, ?)";
		
		try {
        	Connection connection = dataSource.getConnection();
        	PreparedStatement statement = connection.prepareStatement(sql);
        	
        	statement.setInt(1, rentalEntity.getCustomerId());
        	statement.setInt(2, rentalEntity.getVehicleId());
        	statement.setString(3, rentalEntity.getStartDate());
        	statement.setString(4, rentalEntity.getEndDate());
        	statement.setString(5, rentalEntity.getStatus());
        	
        	return statement.executeUpdate();
		} catch (Exception e) {
            e.printStackTrace();
			return 0;
		}
	}
    
    public List<RentalEntity> getRentals() throws RemoteException {
    	String sql = "SELECT * FROM rental";
    	
        try {
        	Connection connection = dataSource.getConnection();
        	Statement statement = connection.createStatement();
        	 
        	ResultSet result = statement.executeQuery(sql);
        	List<RentalEntity> resultList = new ArrayList<RentalEntity>();
        	
        	while (result.next()) {
        		RentalEntity rentalEntity = new RentalEntity();
        		rentalEntity.setId(result.getInt("id"));
        		rentalEntity.setCustomerId(result.getInt("customer_id"));
        		rentalEntity.setVehicleId(result.getInt("vehicle_id"));
        		rentalEntity.setStartDate(result.getString("start_date"));
        		rentalEntity.setEndDate(result.getString("end_date"));
        		rentalEntity.setStatus(result.getString("status"));
	            resultList.add(rentalEntity);
        	}
	         
        	statement.close();
        	connection.close();
	         
        	return resultList;
        } catch (Exception e) {
            e.printStackTrace();
    		return null;
        }
    }
    
	public RentalEntity getRental(int rentalID) throws RemoteException {
		String sql = "SELECT * FROM rental WHERE id = "+ rentalID +" LIMIT 1";
		
		try {
        	Connection connection = dataSource.getConnection();
        	Statement statement = connection.createStatement();
        	 
        	ResultSet result = statement.executeQuery(sql);
        	RentalEntity rentalEntity = new RentalEntity();
        	
        	while (result.next()) {
        		rentalEntity.setId(result.getInt("id"));
        		rentalEntity.setCustomerId(result.getInt("customer_id"));
        		rentalEntity.setVehicleId(result.getInt("vehicle_id"));
        		rentalEntity.setStartDate(result.getString("start_date"));
        		rentalEntity.setEndDate(result.getString("end_date"));
        		rentalEntity.setStatus(result.getString("status"));
        	}
        	
        	return rentalEntity;
		} catch (Exception e) {
            e.printStackTrace();
    		return null;
		}	
	}
	
	public int updateRental(RentalEntity rentalEntity) throws RemoteException {
		String sql = "UPDATE rental "
				+ "SET customer_id = ?, vehicle_id = ?, start_date = ?, end_date = ?, status = ? "
				+ "WHERE id = ?";
		
		try {
        	Connection connection = dataSource.getConnection();
        	PreparedStatement statement = connection.prepareStatement(sql);
        	
        	statement.setInt(1, rentalEntity.getCustomerId());
        	statement.setInt(2, rentalEntity.getVehicleId());
        	statement.setString(3, rentalEntity.getStartDate());
        	statement.setString(4, rentalEntity.getEndDate());
        	statement.setString(5, rentalEntity.getStatus());
        	statement.setInt(6, rentalEntity.getId());
        	
        	return statement.executeUpdate();
		} catch (Exception e) {
            e.printStackTrace();
    		return 0;
		}	
	}
	
	public int deleteRental(int rentalID) throws RemoteException {
		String sql = "DELETE FROM vehicle WHERE id = ?";
		
		try {
        	Connection connection = dataSource.getConnection();
        	PreparedStatement statement = connection.prepareStatement(sql);

        	statement.setInt(1, rentalID);
        	
        	return statement.executeUpdate();
		} catch (Exception e) {
            e.printStackTrace();
    		return 0;
		}	
	}
}
