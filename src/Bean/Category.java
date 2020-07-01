package Bean;

import java.io.Serializable;

public class Category implements Serializable {

	
	private int Category_ID;
	private String Category_Name;
	private int Proudct_ID;
	
	public Category(int category_ID, String category_Name, int proudct_ID) {
		super();
		Category_ID = category_ID;
		Category_Name = category_Name;
		Proudct_ID = proudct_ID;
	}

	public int getCategory_ID() {
		return Category_ID;
	}

	public void setCategory_ID(int category_ID) {
		Category_ID = category_ID;
	}

	public String getCategory_Name() {
		return Category_Name;
	}

	public void setCategory_Name(String category_Name) {
		Category_Name = category_Name;
	}

	public int getProudct_ID() {
		return Proudct_ID;
	}

	public void setProudct_ID(int proudct_ID) {
		Proudct_ID = proudct_ID;
	}
	
	
	
	
	
}
