package Bean;

import java.io.Serializable;

public class Order_Detail implements Serializable {
	
	//宣告區
	private int order_ID;
	private int order_Detail_Amount;
	private int Product_ID;
	private int order_Detail_Buy_Price;
	private String color;
	
	public Order_Detail(int order_ID, int order_Detail_Amount, int prount_ID, int order_Detail_Buy_Price,
			String color) {
		super();
		this.order_ID = order_ID;
		this.order_Detail_Amount = order_Detail_Amount;
		this.Product_ID = prount_ID;
		this.order_Detail_Buy_Price = order_Detail_Buy_Price;
		this.color = color;
	}
	//建構子
//	public Order_Detail(int order_ID, int order_Detail_Amount, int Product_ID,
//			int order_Detail_Buy_Price) {
//		super();
//		this.order_ID = order_ID;
//		this.order_Detail_Amount = order_Detail_Amount;
//		this.Product_ID = Prount_ID;
//		this.order_Detail_Buy_Price = order_Detail_Buy_Price;
//	}

	public int getOrder_ID() {
		return order_ID;
	}

	public void setOrder_ID(int order_ID) {
		this.order_ID = order_ID;
	}

	public int getOrder_Detail_Amount() {
		return order_Detail_Amount;
	}

	public void setOrder_Detail_Amount(int order_Detail_Amount) {
		this.order_Detail_Amount = order_Detail_Amount;
	}

	public int getProduct_ID() {
		return Product_ID;
	}

	public void setProduct_ID(int product_ID) {
		Product_ID = product_ID;
	}

	public int getOrder_Detail_Buy_Price() {
		return order_Detail_Buy_Price;
	}

	public void setOrder_Detail_Buy_Price(int order_Detail_Buy_Price) {
		this.order_Detail_Buy_Price = order_Detail_Buy_Price;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	
	
	
}
