package Bean;

import java.io.Serializable;
import java.sql.Timestamp;

public class Notice implements Serializable{

	private int Notice_ID;
	private String Notice_Content;
	private Timestamp Notice_time;
	
	
	public Notice(int notice_ID, String notice_Content, Timestamp notice_time) {
		super();
		Notice_ID = notice_ID;
		Notice_Content = notice_Content;
		Notice_time = notice_time;
	}


	public int getNotice_ID() {
		return Notice_ID;
	}


	public void setNotice_ID(int notice_ID) {
		Notice_ID = notice_ID;
	}


	public String getNotice_Content() {
		return Notice_Content;
	}


	public void setNotice_Content(String notice_Content) {
		Notice_Content = notice_Content;
	}


	public Timestamp getNotice_time() {
		return Notice_time;
	}


	public void setNotice_time(Timestamp notice_time) {
		Notice_time = notice_time;
	}
	
	
	
	
	
}
