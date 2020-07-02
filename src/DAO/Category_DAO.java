package DAO;

import java.util.List;

import Bean.Category;
//import Bean.Prouct;

public interface Category_DAO {
	
	
	void insert(Category category);
	void update(Category category);
	void delete(int category_id);
	Category findById(int category_id);
	List<Category> getAll();


}
