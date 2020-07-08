package DAO;

import java.util.List;

import Bean.Product;
import Bean.Shopping_Cart;
import Bean.User_Account;
import Bean.Product;


public interface Shopping_Card_DAO {

	int insert(User_Account user_Account,Product product,Shopping_Cart Shopping_Cart);
	int insert(Shopping_Cart shopping_Cart);
	int delete(int accoumt_ID, int product_ID);
	List<Shopping_Cart> getAll();
	byte[] getImage(int id);
	

	
	int insert(Product product);
	int delete(int product_ID);
	int delete(Shopping_Cart shopping_Cart);
	
}
