package Bean;

public class ORDER_DETAIL {

	private int order_Detail_ID;
	private int order_Detail_Amount;
	private int order_Detail_Prount_ID;
	private int order_Detail_Buy_Price;
	public ORDER_DETAIL(int order_Detail_ID, int order_Detail_Amount, int order_Detail_Prount_ID,
			int order_Detail_Buy_Price) {
		super();
		this.order_Detail_ID = order_Detail_ID;
		this.order_Detail_Amount = order_Detail_Amount;
		this.order_Detail_Prount_ID = order_Detail_Prount_ID;
		this.order_Detail_Buy_Price = order_Detail_Buy_Price;
	}
	public int getOrder_Detail_ID() {
		return order_Detail_ID;
	}
	public void setOrder_Detail_ID(int order_Detail_ID) {
		this.order_Detail_ID = order_Detail_ID;
	}
	public int getOrder_Detail_Amount() {
		return order_Detail_Amount;
	}
	public void setOrder_Detail_Amount(int order_Detail_Amount) {
		this.order_Detail_Amount = order_Detail_Amount;
	}
	public int getOrder_Detail_Prount_ID() {
		return order_Detail_Prount_ID;
	}
	public void setOrder_Detail_Prount_ID(int order_Detail_Prount_ID) {
		this.order_Detail_Prount_ID = order_Detail_Prount_ID;
	}
	public int getOrder_Detail_Buy_Price() {
		return order_Detail_Buy_Price;
	}
	public void setOrder_Detail_Buy_Price(int order_Detail_Buy_Price) {
		this.order_Detail_Buy_Price = order_Detail_Buy_Price;
	}
	
	
	
}
