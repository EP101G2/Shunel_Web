package DAO_Interface;

import javax.sql.DataSource;

import DAO.Fuction_DAO;
import Servlet_Shunel.ServiceLocator;

public class Fuction_DAO_Interface {

	DataSource dataSource;

	public Fuction_DAO_Interface() {
		dataSource = ServiceLocator.getInstance().getDataSource();
		
	}
	
	
}
