package Bean;

import java.io.Serializable;

public class Like implements Serializable{
	
	
	private int Account_ID;
	private int Product_ID;
	
	
	public Like(int account_ID, int product_ID) {
		super();
		Account_ID = account_ID;
		Product_ID = product_ID;
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
	
	

}
