import java.rmi.*;

public interface VehicleInterface extends Remote {
   public String getVehicles() throws RemoteException;
}
