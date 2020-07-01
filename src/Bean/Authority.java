package Bean;

import java.io.Serializable;

public class Authority implements Serializable{

	private int Admin_ID;
	private int Function_ID;
	
	
	public Authority(int admin_ID, int function_ID) {
		super();
		Admin_ID = admin_ID;
		Function_ID = function_ID;
	}


	public int getAdmin_ID() {
		return Admin_ID;
	}


	public void setAdmin_ID(int admin_ID) {
		Admin_ID = admin_ID;
	}


	public int getFunction_ID() {
		return Function_ID;
	}


	public void setFunction_ID(int function_ID) {
		Function_ID = function_ID;
	}
	
	
	
	
}
