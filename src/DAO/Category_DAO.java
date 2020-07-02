package DAO;

import java.util.List;

import Bean.Category;
//import Bean.Prouct;

public interface Category_DAO {
	
	
	int insert(Category category);
	int update(Category category);
	int delete(int category_id);
	Category findById(int category_id);
	List<Category> getAll();


}
