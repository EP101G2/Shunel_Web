package DAO_Interface;

import java.util.List;

import javax.sql.DataSource;

import Bean.Order_Detail;
import DAO.Order_Detail_DAO;
import Servlet_Shunel.ServiceLocator;

public class Order_Detail_DAO_Interface implements Order_Detail_DAO{

	DataSource dataSource;

	public Order_Detail_DAO_Interface() {
		dataSource = ServiceLocator.getInstance().getDataSource();

	}

	@Override
	public int insert(Order_Detail order_Detail) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Order_Detail order_Detail) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int Order_ID, int Product_ID) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public byte[] getImage(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order_Detail findById(int Order_ID, int Product_ID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order_Detail> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
