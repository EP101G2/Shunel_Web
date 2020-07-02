package Bean;

import java.io.Serializable;

public class Order_Detail implements Serializable {
	
	//宣告區
	private int order_ID;
	private int order_Detail_Amount;
	private int Prount_ID;
	private int order_Detail_Buy_Price;
	
	//建構子
	public Order_Detail(int order_ID, int order_Detail_Amount, int Prount_ID,
			int order_Detail_Buy_Price) {
		super();
		this.order_ID = order_ID;
		this.order_Detail_Amount = order_Detail_Amount;
		this.Prount_ID = Prount_ID;
		this.order_Detail_Buy_Price = order_Detail_Buy_Price;
	}
	public int getOrder_Detail_ID() {
		return order_ID;
	}
	public void setOrder_Detail_ID(int order_ID) {
		this.order_ID = order_ID;
	}
	public int getOrder_Detail_Amount() {
		return order_Detail_Amount;
	}
	public void setOrder_Detail_Amount(int order_Detail_Amount) {
		this.order_Detail_Amount = order_Detail_Amount;
	}
	public int getOrder_Detail_Prount_ID() {
		return Prount_ID;
	}
	public void setOrder_Detail_Prount_ID(int Prount_ID) {
		this.Prount_ID = Prount_ID;
	}
	public int getOrder_Detail_Buy_Price() {
		return order_Detail_Buy_Price;
	}
	public void setOrder_Detail_Buy_Price(int order_Detail_Buy_Price) {
		this.order_Detail_Buy_Price = order_Detail_Buy_Price;
	}
	
	
	
}
