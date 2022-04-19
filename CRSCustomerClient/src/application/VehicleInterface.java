package application;


import java.rmi.*;
import java.util.List;

public interface VehicleInterface extends Remote {
	public List<VehicleEntity> getVehicles() throws RemoteException;
	public VehicleEntity getVehicle(int vehicleID) throws RemoteException;

}
