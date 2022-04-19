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

public class Vehicle extends UnicastRemoteObject implements VehicleInterface {
	protected SQLiteDataSource dataSource;
	
	public Vehicle () throws RemoteException {
		dataSource = new SQLiteDataSource();
		dataSource.setUrl("jdbc:sqlite:crs.db");
	}
	
	public int createVehicle(VehicleEntity vehicleEntity) throws RemoteException {
		String sql = "INSERT INTO vehicle (year, make, model, color, current_customer_id) "
    			+ "VALUES(?, ?, ?, ?, ?)";
		
		try {
        	Connection connection = dataSource.getConnection();
        	PreparedStatement statement = connection.prepareStatement(sql);
        	
        	statement.setInt(1, vehicleEntity.getYear());
        	statement.setString(2, vehicleEntity.getMake());
        	statement.setString(3, vehicleEntity.getModel());
        	statement.setString(4, vehicleEntity.getColor());
        	statement.setInt(5, vehicleEntity.getCurrentCustomerId());
        	
        	return statement.executeUpdate();
		} catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
			return 0;
		}
	}
    
    public List<VehicleEntity> getVehicles() throws RemoteException {
    	String sql = "SELECT * FROM vehicle";
    	
        try {
        	Connection connection = dataSource.getConnection();
        	Statement statement = connection.createStatement();
        	 
        	ResultSet result = statement.executeQuery(sql);
        	List<VehicleEntity> resultList = new ArrayList<VehicleEntity>();
        	
        	while (result.next()) {
        		VehicleEntity vehicleEntity = new VehicleEntity();
        		vehicleEntity.setId(result.getInt("id"));
        		vehicleEntity.setMake(result.getString("make"));
        		vehicleEntity.setModel(result.getString("model"));
        		vehicleEntity.setColor(result.getString("color"));
        		vehicleEntity.setYear(result.getInt("year"));
        		vehicleEntity.setCurrentCustomerId(result.getInt("current_customer_id"));
	            resultList.add(vehicleEntity);
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
    
	public VehicleEntity getVehicle(int vehicleID) throws RemoteException {
		String sql = "SELECT * FROM vehicle WHERE id = "+ vehicleID +" LIMIT 1";
		
		try {
        	Connection connection = dataSource.getConnection();
        	Statement statement = connection.createStatement();
        	 
        	ResultSet result = statement.executeQuery(sql);
        	VehicleEntity vehicleEntity = new VehicleEntity();
        	
        	while (result.next()) {
        		vehicleEntity.setId(result.getInt("id"));
        		vehicleEntity.setMake(result.getString("make"));
        		vehicleEntity.setModel(result.getString("model"));
        		vehicleEntity.setColor(result.getString("color"));
        		vehicleEntity.setYear(result.getInt("year"));
        		vehicleEntity.setCurrentCustomerId(result.getInt("current_customer_id"));
        	}
        	
        	return vehicleEntity;
		} catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
    		return null;
		}	
	}
	
	public int updateVehicle(VehicleEntity vehicleEntity) throws RemoteException {
		String sql = "UPDATE vehicle "
				+ "SET make = ?, model = ?, color = ?, year = ?, current_customer_id = ? "
				+ "WHERE id = ?";
		
		try {
        	Connection connection = dataSource.getConnection();
        	PreparedStatement statement = connection.prepareStatement(sql);
        	
        	statement.setInt(1, vehicleEntity.getYear());
        	statement.setString(2, vehicleEntity.getMake());
        	statement.setString(3, vehicleEntity.getModel());
        	statement.setString(4, vehicleEntity.getColor());
        	statement.setInt(5, vehicleEntity.getCurrentCustomerId());
        	statement.setInt(6, vehicleEntity.getId());
        	
        	return statement.executeUpdate();
		} catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
    		return 0;
		}	
	}
	
	public int deleteVehicle(int vehicleID) throws RemoteException {
		String sql = "DELETE FROM vehicle WHERE id = ?";
		
		try {
        	Connection connection = dataSource.getConnection();
        	PreparedStatement statement = connection.prepareStatement(sql);

        	statement.setInt(1, vehicleID);
        	
        	return statement.executeUpdate();
		} catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
    		return 0;
		}	
	}
}
