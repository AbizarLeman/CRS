package application;

import java.rmi.*;
import java.util.List;

public interface BillInterface extends Remote {
	public int createBill(BillEntity billEntity) throws RemoteException;
	public List<BillEntity> getBills() throws RemoteException;
	public BillEntity getBill(int billID) throws RemoteException;
	public int updateBill(BillEntity billEntity) throws RemoteException;
}