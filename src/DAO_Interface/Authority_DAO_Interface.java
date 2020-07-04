package DAO_Interface;

import java.util.List;

import javax.sql.DataSource;

import Bean.Authority;
import DAO.Authority_DAO;
import Servlet_Shunel.ServiceLocator;


public class Authority_DAO_Interface implements Authority_DAO {

	DataSource dataSource;

	public Authority_DAO_Interface() {
		dataSource = ServiceLocator.getInstance().getDataSource();
		
	}

	@Override
	public void insert(Authority authority) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Authority authority) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int admin_ID, int function_ID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Authority findById(int admin_ID, int function_ID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Authority> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
