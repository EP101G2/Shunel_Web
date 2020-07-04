package DAO_Interface;

import javax.sql.DataSource;

import Servlet_Shunel.ServiceLocator;

public class Order_Detail_DAO_Interface {

	DataSource dataSource;

	public Order_Detail_DAO_Interface() {
		dataSource = ServiceLocator.getInstance().getDataSource();

	}
}
