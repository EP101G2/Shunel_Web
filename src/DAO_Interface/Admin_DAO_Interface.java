package DAO_Interface;

import java.util.List;

import javax.sql.DataSource;

import Bean.Admin;
import DAO.Admin_DAO;
import Servlet_Shunel.ServiceLocator;

public class Admin_DAO_Interface  implements Admin_DAO{
	DataSource dataSource;

	public Admin_DAO_Interface() {
		dataSource = ServiceLocator.getInstance().getDataSource();
		
		
	}

	@Override
	public void insert(Admin admin) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Admin admin) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int admin_id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Admin findById(int admin_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Admin> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
