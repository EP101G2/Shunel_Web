package Bean;

import java.io.Serializable;
import java.sql.Timestamp;

public class Shopping_Cart implements Serializable {

	// 宣告區
//	private String Account_ID; // PK FK
//	private String Product_ID; // PK FK
	
	private int Account_ID; // PK FK
	private int Product_ID; // PK FK
	
	private int Shopping_Cart_Amount;
	private Timestamp Shopping_Cart_Modify_Date;

	// 建構子

//	public Shopping_Cart(String account_ID, String product_ID, int shopping_Cart_Amount,
//			Timestamp shopping_Cart_Modify_Date) {
//		super();
//		Account_ID = account_ID;
//		Product_ID = product_ID;
//		Shopping_Cart_Amount = shopping_Cart_Amount;
//		Shopping_Cart_Modify_Date = shopping_Cart_Modify_Date;
//	}

	
	public Shopping_Cart(int tAccount_ID, int tProduct_ID, int shopping_Cart_Amount) {
		super();
		Account_ID = tAccount_ID;
		Product_ID = tProduct_ID;
		Shopping_Cart_Amount = shopping_Cart_Amount;
	}

//	public Shopping_Cart(String account_ID2, String product_ID2, int shopping_Cart_Amount) {
//		super();
//		Account_ID = account_ID2;
//		Product_ID = product_ID2;
//		Shopping_Cart_Amount = shopping_Cart_Amount;
//
//	}

	// 方法

	

	public int getShopping_Cart_Amount() {
		return Shopping_Cart_Amount;
	}

//	public String getAccount_ID() {
//		return Account_ID;
//	}
//
//	public void setAccount_ID(String account_ID) {
//		Account_ID = account_ID;
//	}
//
//	public String getProduct_ID() {
//		return Product_ID;
//	}
//
//	public void setProduct_ID(String product_ID) {
//		Product_ID = product_ID;
//	}

	public void setShopping_Cart_Amount(int shopping_Cart_Amount) {
		Shopping_Cart_Amount = shopping_Cart_Amount;
	}

	public Timestamp getShopping_Cart_Modify_Date() {
		return Shopping_Cart_Modify_Date;
	}

	public void setShopping_Cart_Modify_Date(Timestamp shopping_Cart_Modify_Date) {
		Shopping_Cart_Modify_Date = shopping_Cart_Modify_Date;
	}

	public int getAccount_ID() {
		return Account_ID;
	}

	public void setAccount_ID(int account_ID) {
		Account_ID = account_ID;
	}

	public int getProduct_ID() {
		return Product_ID;
	}

	public void setProduct_ID(int product_ID) {
		Product_ID = product_ID;
	}

	
//	public int getTAccount_ID() {
//		return TAccount_ID;
//	}
//
//	public void setTAccount_ID(int tAccount_ID) {
//		TAccount_ID = tAccount_ID;
//	}
//
//	public int getTProduct_ID() {
//		return TProduct_ID;
//	}
//
//	public void setTProduct_ID(int tProduct_ID) {
//		TProduct_ID = tProduct_ID;
//	}

	
	
}
