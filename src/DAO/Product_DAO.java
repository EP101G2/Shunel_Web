package DAO;

import java.awt.Image;
import java.util.List;

import Bean.Product;
import Bean.Product;



public interface Product_DAO {

	void insert(Product product, byte[] image);
	void update(Product product, byte[] image);
	void delete(int id);
	Product findById(int prouct_id);
	List<Product> getAll();

	byte[] getImage(int id);

	
	
}
