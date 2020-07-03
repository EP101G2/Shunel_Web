package DAO_Interface;

import java.util.List;

import javax.sql.DataSource;

import Bean.Category;
import DAO.Category_DAO;
import Servlet_Shunel.ServiceLocator;

public class Category_DAO_Interface implements Category_DAO {
	
	DataSource dataSource;
	public Category_DAO_Interface() {
		dataSource = ServiceLocator.getInstance().getDataSource();
		
		
	}
	@Override
	public int insert(Category category) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int update(Category category) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int delete(int category_id) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public Category findById(int category_id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Category> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
