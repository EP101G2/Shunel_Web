package Bean;

import java.io.Serializable;
import java.sql.Timestamp;

public class Promotion implements Serializable {
	
	private int Promotion_ID;
	private String Promotion_Name;
	private int Product_ID;
	private int Promotion_Price;
	private Timestamp Date_Start;
	private Timestamp Date_End;
	
	
	public Promotion(int promotion_ID, String promotion_Name, int product_ID, int promotion_Price,
			Timestamp Date_Start, Timestamp Date_End) {
		super();
		Promotion_ID = promotion_ID;
		Promotion_Name = promotion_Name;
		Product_ID = product_ID;
		Promotion_Price = promotion_Price;
		Date_Start = Date_Start;
		Date_End = Date_End;
	}


	public int getPromotion_ID() {
		return Promotion_ID;
	}


	public void setPromotion_ID(int promotion_ID) {
		Promotion_ID = promotion_ID;
	}


	public String getPromotion_Name() {
		return Promotion_Name;
	}


	public void setPromotion_Name(String promotion_Name) {
		Promotion_Name = promotion_Name;
	}


	public int getProuct_ID() {
		return Product_ID;
	}


	public void setProuct_ID(int prouct_ID) {
		Product_ID = prouct_ID;
	}


	public int getPromotion_Price() {
		return Promotion_Price;
	}


	public void setPromotion_Price(int promotion_Price) {
		Promotion_Price = promotion_Price;
	}


	public Timestamp getDate_Start() {
		return Date_Start;
	}


	public void setDate_Start(Timestamp promotion_Date_Start) {
		Date_Start = Date_Start;
	}


	public Timestamp getDate_End() {
		return Date_End;
	}


	public void setDate_End(Timestamp Date_End) {
		Date_End = Date_End;
	}
	
	
	
	
	

}
