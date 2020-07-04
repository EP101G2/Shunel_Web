package DAO_Interface;

import javax.sql.DataSource;

import Servlet_Shunel.ServiceLocator;

public class Message_DAO_Interface {

	DataSource dataSource;

	public Message_DAO_Interface() {
			dataSource = ServiceLocator.getInstance().getDataSource();

}
}
