package DAO;

import java.util.List;

import Bean.Like;
import Bean.Product;


public interface Like_DAO {
	int insert(Like  like);
	int delete(int id);
	void insert(int Account_ID, int Product_ID);
	
	void update(Like like);

	void delete(int Account_ID, int Product_ID);

	Like findById(int Account_ID, int Product_ID);

	List<Like> getAll(String id);
	
	Product searchLike(String account_id, int product_id );
	int insertLike(String account_id, int product_id );
	int deleteLike(String account_id, int product_id );
	
}
