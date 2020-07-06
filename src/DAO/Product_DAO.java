package DAO;

import java.awt.Image;
import java.util.List;

import Bean.Order_Detail;
import Bean.Order_Main;
import Bean.Product;
import Bean.Shopping_Cart;



public interface Product_DAO {

	int insert(Product product, byte[] image,byte[] image2,byte[] image3);
	int update(Product product, byte[] image,byte[] image2,byte[] image3);
	int delete(int id);
	Product findById(int prouct_id);
	List<Product> getAll();
	

	byte[] getImage(int id);
	
	
	
	
	
	
	

	
	
}