package Bean;

import java.io.Serializable;
import java.sql.Time;

public class Chat_Record implements Serializable {

	int ID;
	int CHAT_NO;
	Time CHAT_DATE;
	String CHAT_MSG;
	int FLAG;
	int CHAT_IMAGE;
	String CHAT_SENDER;
	String CHAT_RECRIVER;
	
	
	public Chat_Record(int iD, int cHAT_NO, Time cHAT_DATE, String cHAT_MSG, int fLAG, int cHAT_IMAGE,
			String cHAT_SENDER, String cHAT_RECRIVER) {
		super();
		ID = iD;
		CHAT_NO = cHAT_NO;
		CHAT_DATE = cHAT_DATE;
		CHAT_MSG = cHAT_MSG;
		FLAG = fLAG;
		CHAT_IMAGE = cHAT_IMAGE;
		CHAT_SENDER = cHAT_SENDER;
		CHAT_RECRIVER = cHAT_RECRIVER;
	}


	public int getID() {
		return ID;
	}


	public void setID(int iD) {
		ID = iD;
	}


	public int getCHAT_NO() {
		return CHAT_NO;
	}


	public void setCHAT_NO(int cHAT_NO) {
		CHAT_NO = cHAT_NO;
	}


	public Time getCHAT_DATE() {
		return CHAT_DATE;
	}


	public void setCHAT_DATE(Time cHAT_DATE) {
		CHAT_DATE = cHAT_DATE;
	}


	public String getCHAT_MSG() {
		return CHAT_MSG;
	}


	public void setCHAT_MSG(String cHAT_MSG) {
		CHAT_MSG = cHAT_MSG;
	}


	public int getFLAG() {
		return FLAG;
	}


	public void setFLAG(int fLAG) {
		FLAG = fLAG;
	}


	public int getCHAT_IMAGE() {
		return CHAT_IMAGE;
	}


	public void setCHAT_IMAGE(int cHAT_IMAGE) {
		CHAT_IMAGE = cHAT_IMAGE;
	}


	public String getCHAT_SENDER() {
		return CHAT_SENDER;
	}


	public void setCHAT_SENDER(String cHAT_SENDER) {
		CHAT_SENDER = cHAT_SENDER;
	}


	public String getCHAT_RECRIVER() {
		return CHAT_RECRIVER;
	}


	public void setCHAT_RECRIVER(String cHAT_RECRIVER) {
		CHAT_RECRIVER = cHAT_RECRIVER;
	}
	
	
	
	
	
	
}
