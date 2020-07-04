package DAO;

import java.util.List;

import Bean.Shopping_Cart;

public interface Shopping_Cart_DAO {
	void insert(Shopping_Cart shopping_Cart);

	void update(Shopping_Cart shopping_Cart);

	void delete(int Account_ID, int Product_ID);

	Shopping_Cart findById(int Account_ID, int Product_ID);

	List<Shopping_Cart> getAll();

}
