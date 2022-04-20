package application;

import java.rmi.*;
import java.util.List;

public interface RentalInterface extends Remote {
	public int createRental(RentalEntity rentalEntity) throws RemoteException;
	public List<RentalEntity> getRentals() throws RemoteException;
	public RentalEntity getRental(int rentalID) throws RemoteException;
	public int updateRental(RentalEntity rentalEntity) throws RemoteException;

}