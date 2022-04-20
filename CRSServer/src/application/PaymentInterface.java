package application;

import java.rmi.*;
import java.util.List;

public interface PaymentInterface extends Remote {
	public List<PaymentEntity> getPayments() throws RemoteException;
	public int updatePayment(PaymentEntity paymentEntity) throws RemoteException;
}