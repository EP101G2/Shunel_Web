package Bean;

import java.io.Serializable;
import java.sql.Timestamp;

public class History implements Serializable{
	
	private  String Account_ID;
	private int Product_ID;
	private Timestamp History_Time;
	
	
	

	public History(String account_ID, int product_ID, Timestamp history_Time) {
		super();
		Account_ID = account_ID;
		Product_ID = product_ID;
		History_Time = history_Time;
	}


	public String getAccount_ID() {
		return Account_ID;
	}


	public void setAccount_ID(String account_ID) {
		Account_ID = account_ID;
	}


	public int getProduct_ID() {
		return Product_ID;
	}


	public void setProduct_ID(int product_ID) {
		Product_ID = product_ID;
	}
	


	public Timestamp getHistory_Time() {
		return History_Time;
	}


	public void setHistory_Time(Timestamp history_Time) {
		History_Time = history_Time;
	}

	
	
	
	
	

}
