package Bean;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;

public class Chat_Record implements Serializable {

	int ID;
	int CHAT_NO;
	Timestamp CHAT_DATE;
	String CHAT_MSG;
	int FLAG;
	byte[] CHAT_IMAGE;
	String CHAT_SENDER;
	String CHAT_RECRIVER;
	
	
	public Chat_Record() {
		super();
	}


	public Chat_Record(int iD, int cHAT_NO, Timestamp cHAT_DATE, String cHAT_MSG, int fLAG, byte[] cHAT_IMAGE,
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


	public Timestamp getCHAT_DATE() {
		return CHAT_DATE;
	}


	public void setCHAT_DATE(Timestamp timestamp) {
		CHAT_DATE = timestamp;
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


	public byte[] getCHAT_IMAGE() {
		return CHAT_IMAGE;
	}


	public void setCHAT_IMAGE(byte[] bs) {
		CHAT_IMAGE = bs;
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
