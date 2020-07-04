package DAO_Interface;

import javax.sql.DataSource;

import Servlet_Shunel.ServiceLocator;

public class History_DAO_Interface {
	DataSource dataSource;

	public History_DAO_Interface() {
		dataSource = ServiceLocator.getInstance().getDataSource();

}

}