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
		String sql = "INSERT INTO vehicle (year, make, model, color, is_renting) "
    			+ "VALUES(?, ?, ?, ?, ?)";
		
		try {
        	Connection connection = dataSource.getConnection();
        	PreparedStatement statement = connection.prepareStatement(sql);
        	
        	statement.setInt(1, vehicleEntity.year);
        	statement.setString(2, vehicleEntity.make);
        	statement.setString(3, vehicleEntity.model);
        	statement.setString(4, vehicleEntity.color);
        	statement.setBoolean(5, vehicleEntity.isRenting);
        	
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
        		vehicleEntity.id = result.getInt("id");
        		vehicleEntity.make = result.getString("make");
        		vehicleEntity.model = result.getString("model");
        		vehicleEntity.color = result.getString("color");
        		vehicleEntity.year  = result.getInt("year");
        		vehicleEntity.isRenting = result.getBoolean("is_renting");
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
        		vehicleEntity.id = result.getInt("id");
        		vehicleEntity.make = result.getString("make");
        		vehicleEntity.model = result.getString("model");
        		vehicleEntity.color = result.getString("color");
        		vehicleEntity.year  = result.getInt("year");
        		vehicleEntity.isRenting = result.getBoolean("is_renting");
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
				+ "SET make = ?, model = ?, color = ?, year = ?, is_renting = ? "
				+ "WHERE id = ?";
		
		try {
        	Connection connection = dataSource.getConnection();
        	PreparedStatement statement = connection.prepareStatement(sql);
        	
        	statement.setInt(1, vehicleEntity.year);
        	statement.setString(2, vehicleEntity.make);
        	statement.setString(3, vehicleEntity.model);
        	statement.setString(4, vehicleEntity.color);
        	statement.setBoolean(5, vehicleEntity.isRenting);
        	statement.setInt(6, vehicleEntity.id);
        	
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
