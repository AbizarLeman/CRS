import java.rmi.*;

public interface CustomerInterface extends Remote {
   public String getCustomers() throws RemoteException;
}
