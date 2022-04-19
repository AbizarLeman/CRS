package application;

import java.io.Serializable;

public class PaymentEntity implements Serializable {
	private int id;
	private int billId;
	private String paymentDate;
	private float amount;
	
	public PaymentEntity () {
		this.id = 0;
		this.billId = 0;
		this.paymentDate = "";
		this.amount = ((float) 0.0);
	}
	
	public PaymentEntity (int id, int billId, String paymentDate, float amount) {
		this.id = id;
		this.billId = billId;
		this.paymentDate = paymentDate;
		this.amount = amount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBillId() {
		return billId;
	}

	public void setBillId(int billId) {
		this.billId = billId;
	}

	public String getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}
}