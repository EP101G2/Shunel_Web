package Bean;

import java.io.Serializable;

public class orderStatistics implements Serializable {
	
	private int PRODUCT_ID;
	private int CATEGORY_ID;
	private int countCATEGORY_ID;
	private int sumBUY_PRICE;
	public orderStatistics(int pRODUCT_ID, int cATEGORY_ID, int countCATEGORY_ID, int sumBUY_PRICE) {
		super();
		PRODUCT_ID = pRODUCT_ID;
		CATEGORY_ID = cATEGORY_ID;
		this.countCATEGORY_ID = countCATEGORY_ID;
		this.sumBUY_PRICE = sumBUY_PRICE;
	}
	public int getPRODUCT_ID() {
		return PRODUCT_ID;
	}
	public void setPRODUCT_ID(int pRODUCT_ID) {
		PRODUCT_ID = pRODUCT_ID;
	}
	public int getCATEGORY_ID() {
		return CATEGORY_ID;
	}
	public void setCATEGORY_ID(int cATEGORY_ID) {
		CATEGORY_ID = cATEGORY_ID;
	}
	public int getCountCATEGORY_ID() {
		return countCATEGORY_ID;
	}
	public void setCountCATEGORY_ID(int countCATEGORY_ID) {
		this.countCATEGORY_ID = countCATEGORY_ID;
	}
	public int getSumBUY_PRICE() {
		return sumBUY_PRICE;
	}
	public void setSumBUY_PRICE(int sumBUY_PRICE) {
		this.sumBUY_PRICE = sumBUY_PRICE;
	}
	
	
	
	
	

}
