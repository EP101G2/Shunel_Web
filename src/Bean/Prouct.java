package Bean;

import java.io.Serializable;
import java.sql.Timestamp;

public class Prouct implements Serializable{

	//宣告區
	private int prouct_ID;
	private String prouct_Name;
	private String prouct_Color;
	private int prouct_Price;
	private String prouct_Ditail;
	private int prouct_Category_ID;
	private int prouct_Status;
	private Timestamp prouct_MODIFY_DATE;
	
	
	//建構區
	
	
	
	public Prouct(int prouct_ID, String prouct_Name, String prouct_Color, int prouct_Price, String prouct_Ditail,
			int prouct_Category_ID, int prouct_Status, Timestamp prouct_MODIFY_DATE) {
		super();
		this.prouct_ID = prouct_ID;
		this.prouct_Name = prouct_Name;
		this.prouct_Color = prouct_Color;
		this.prouct_Price = prouct_Price;
		this.prouct_Ditail = prouct_Ditail;
		this.prouct_Category_ID = prouct_Category_ID;
		this.prouct_Status = prouct_Status;
		this.prouct_MODIFY_DATE = prouct_MODIFY_DATE;
	}
	
	

	public Prouct(int prouct_ID) {
		super();
		this.prouct_ID = prouct_ID;
	}



	//方法區
	public int getProuct_ID() {
		return prouct_ID;
	}

	public void setProuct_ID(int prouct_ID) {
		this.prouct_ID = prouct_ID;
	}

	public String getProuct_Name() {
		return prouct_Name;
	}

	public void setProuct_Name(String prouct_Name) {
		this.prouct_Name = prouct_Name;
	}

	public String getProuct_Color() {
		return prouct_Color;
	}

	public void setProuct_Color(String prouct_Color) {
		this.prouct_Color = prouct_Color;
	}

	public int getProuct_Price() {
		return prouct_Price;
	}

	public void setProuct_Price(int prouct_Price) {
		this.prouct_Price = prouct_Price;
	}

	public String getProuct_Ditail() {
		return prouct_Ditail;
	}

	public void setProuct_Ditail(String prouct_Ditail) {
		this.prouct_Ditail = prouct_Ditail;
	}

	public int getProuct_Category_ID() {
		return prouct_Category_ID;
	}

	public void setProuct_Category_ID(int prouct_Category_ID) {
		this.prouct_Category_ID = prouct_Category_ID;
	}

	public int getProuct_Status() {
		return prouct_Status;
	}

	public void setProuct_Status(int prouct_Status) {
		this.prouct_Status = prouct_Status;
	}

	public Timestamp getProuct_MODIFY_DATE() {
		return prouct_MODIFY_DATE;
	}

	public void setProuct_MODIFY_DATE(Timestamp prouct_MODIFY_DATE) {
		this.prouct_MODIFY_DATE = prouct_MODIFY_DATE;
	}
	
	
	
	
	
}
