package Bean;

import java.io.Serializable;

public class Fuction implements Serializable{
	
	//宣告區
	private int Fuction_ID;
	private String Fuction_title;
	
	//建構區
	public Fuction(int fuction_ID, String fuction_title) {
		super();
		Fuction_ID = fuction_ID;
		Fuction_title = fuction_title;
	}

	
	//方法
	public int getFuction_ID() {
		return Fuction_ID;
	}

	public void setFuction_ID(int fuction_ID) {
		Fuction_ID = fuction_ID;
	}

	public String getFuction_title() {
		return Fuction_title;
	}

	public void setFuction_title(String fuction_title) {
		Fuction_title = fuction_title;
	}
	
	
	

}
