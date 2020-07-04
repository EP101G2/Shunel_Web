package DAO_Interface;

import javax.sql.DataSource;

import Servlet_Shunel.ServiceLocator;

public class Like_DAO_Interface {

	DataSource dataSource;

	public Like_DAO_Interface() {
		dataSource = ServiceLocator.getInstance().getDataSource();

	}
}
