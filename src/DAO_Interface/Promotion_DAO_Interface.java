package DAO_Interface;

import javax.sql.DataSource;

import Servlet_Shunel.ServiceLocator;

public class Promotion_DAO_Interface {
	DataSource dataSource;

	public Promotion_DAO_Interface() {
		dataSource = ServiceLocator.getInstance().getDataSource();

	}
}
