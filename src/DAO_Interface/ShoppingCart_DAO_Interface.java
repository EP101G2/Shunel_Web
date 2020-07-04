package DAO_Interface;

import javax.sql.DataSource;

import Servlet_Shunel.ServiceLocator;

public class ShoppingCart_DAO_Interface {
	DataSource dataSource;

	public ShoppingCart_DAO_Interface() {
		dataSource = ServiceLocator.getInstance().getDataSource();

	}
}
