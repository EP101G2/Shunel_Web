package Bean;

import java.io.Serializable;
import java.sql.Timestamp;

public class User_Account implements Serializable {
	
	private int Account_ID;
	private String Account_Phone;
	private String Account_Password;
	private String Account_Addres;
	private int Account_Total_Price;
	private int Account_Notice_Status;
	private int Account_Status;
	private Timestamp Account_Modify_Date;
	
	
	public User_Account(int account_ID, String account_Phone, String account_Password, String account_Addres,
			int account_Total_Price, int account_Notice_Status, int account_Status, Timestamp account_Modify_Date) {
		super();
		Account_ID = account_ID;
		Account_Phone = account_Phone;
		Account_Password = account_Password;
		Account_Addres = account_Addres;
		Account_Total_Price = account_Total_Price;
		Account_Notice_Status = account_Notice_Status;
		Account_Status = account_Status;
		Account_Modify_Date = account_Modify_Date;
	}


	public int getAccount_ID() {
		return Account_ID;
	}


	public void setAccount_ID(int account_ID) {
		Account_ID = account_ID;
	}


	public String getAccount_Phone() {
		return Account_Phone;
	}


	public void setAccount_Phone(String account_Phone) {
		Account_Phone = account_Phone;
	}


	public String getAccount_Password() {
		return Account_Password;
	}


	public void setAccount_Password(String account_Password) {
		Account_Password = account_Password;
	}


	public String getAccount_Addres() {
		return Account_Addres;
	}


	public void setAccount_Addres(String account_Addres) {
		Account_Addres = account_Addres;
	}


	public int getAccount_Total_Price() {
		return Account_Total_Price;
	}


	public void setAccount_Total_Price(int account_Total_Price) {
		Account_Total_Price = account_Total_Price;
	}


	public int getAccount_Notice_Status() {
		return Account_Notice_Status;
	}


	public void setAccount_Notice_Status(int account_Notice_Status) {
		Account_Notice_Status = account_Notice_Status;
	}


	public int getAccount_Status() {
		return Account_Status;
	}


	public void setAccount_Status(int account_Status) {
		Account_Status = account_Status;
	}


	public Timestamp getAccount_Modify_Date() {
		return Account_Modify_Date;
	}


	public void setAccount_Modify_Date(Timestamp account_Modify_Date) {
		Account_Modify_Date = account_Modify_Date;
	}
	
	
	
	
	

}
