package application;

import java.rmi.*;
import java.util.List;

public interface VehicleInterface extends Remote {
	public int createVehicle(VehicleEntity vehicleEntity) throws RemoteException;
	public List<VehicleEntity> getVehicles() throws RemoteException;
	public List<VehicleEntity> getCustomerVehicles(int customerId) throws RemoteException;
	public VehicleEntity getVehicle(int vehicleID) throws RemoteException;
	public int updateVehicle(VehicleEntity vehicleEntity) throws RemoteException;
	public int deleteVehicle(int vehicleID) throws RemoteException;
}