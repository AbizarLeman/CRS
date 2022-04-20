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

public class Payment extends UnicastRemoteObject implements PaymentInterface {
	protected SQLiteDataSource dataSource;
	
	public Payment () throws RemoteException {
		dataSource = new SQLiteDataSource();
		dataSource.setUrl("jdbc:sqlite:crs.db");
	}

	@Override
	public List<PaymentEntity> getPayments() throws RemoteException {
    	String sql = "SELECT * FROM payment";
    	
        try {
        	Connection connection = dataSource.getConnection();
        	Statement statement = connection.createStatement();
        	 
        	ResultSet result = statement.executeQuery(sql);
        	List<PaymentEntity> resultList = new ArrayList<PaymentEntity>();
        	
        	while (result.next()) {
        		PaymentEntity paymentEntity = new PaymentEntity();
        		paymentEntity.setId(result.getInt("id"));
        		paymentEntity.setBillId(result.getInt("bill_id"));
        		paymentEntity.setPaymentDate(result.getString("payment_date"));
        		paymentEntity.setAmount(result.getFloat("amount"));
	            resultList.add(paymentEntity);
        	}
	         
        	statement.close();
        	connection.close();
	         
        	return resultList;
        } catch (Exception e) {
            e.printStackTrace();
    		return null;
        }
	}

	@Override
	public int updatePayment(PaymentEntity paymentEntity) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}
}
