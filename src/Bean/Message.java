package Bean;

import java.io.Serializable;

public class Message implements Serializable{
	
	
	//宣告區
	private int Message_ID;
	private String Account_ID;
	private int Message_Status;
	private String Message_Content;
	
	
	//建構區
	public Message(int message_ID, String account_ID, int message_Status, String message_Content) {
		super();
		Message_ID = message_ID;
		Account_ID = account_ID;
		Message_Status = message_Status;
		Message_Content = message_Content;
	}


	//方法區
	public int getMessage_ID() {
		return Message_ID;
	}


	public void setMessage_ID(int message_ID) {
		Message_ID = message_ID;
	}


	public String getAccount_ID() {
		return Account_ID;
	}


	public void setAccount_ID(String account_ID) {
		Account_ID = account_ID;
	}


	public int getMessage_Status() {
		return Message_Status;
	}


	public void setMessage_Status(int message_Status) {
		Message_Status = message_Status;
	}


	public String getMessage_Content() {
		return Message_Content;
	}


	public void setMessage_Content(String message_Content) {
		Message_Content = message_Content;
	}
	
	
	
	

}
