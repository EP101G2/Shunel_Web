package Bean;

import java.io.Serializable;
import java.sql.Timestamp;

public class Chat implements Serializable{
	
	//宣告區
	private int Chat_ID;
	private int Product_ID;
	private int Account_ID;
	private String Buy_Content;
	private String Sell_Content;
	private int Chat_Status;
	private Timestamp Chat_Time;
	
	//建構區
	public Chat(int chat_ID, int product_ID, int account_ID, String buy_Content, String sell_Content, int chat_Status,
			Timestamp chat_Time) {
		super();
		Chat_ID = chat_ID;
		Product_ID = product_ID;
		Account_ID = account_ID;
		Buy_Content = buy_Content;
		Sell_Content = sell_Content;
		Chat_Status = chat_Status;
		Chat_Time = chat_Time;
	}


	//方法區
	public int getChat_ID() {
		return Chat_ID;
	}

	public void setChat_ID(int chat_ID) {
		Chat_ID = chat_ID;
	}

	public int getProduct_ID() {
		return Product_ID;
	}

	public void setProduct_ID(int product_ID) {
		Product_ID = product_ID;
	}

	public int getAccount_ID() {
		return Account_ID;
	}

	public void setAccount_ID(int account_ID) {
		Account_ID = account_ID;
	}

	public String getBuy_Content() {
		return Buy_Content;
	}

	public void setBuy_Content(String buy_Content) {
		Buy_Content = buy_Content;
	}

	public String getSell_Content() {
		return Sell_Content;
	}

	public void setSell_Content(String sell_Content) {
		Sell_Content = sell_Content;
	}

	public int getChat_Status() {
		return Chat_Status;
	}

	public void setChat_Status(int chat_Status) {
		Chat_Status = chat_Status;
	}

	public Timestamp getChat_Time() {
		return Chat_Time;
	}

	public void setChat_Time(Timestamp chat_Time) {
		Chat_Time = chat_Time;
	}
	
	

}
