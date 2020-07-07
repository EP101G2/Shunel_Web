package DAO_Interface;

import java.util.List;

import javax.sql.DataSource;

import Bean.Order_Detail;
import DAO.Oder_Main_DAO;
import Servlet_Shunel.ServiceLocator;


public class Oder_Main_DAO_Interface implements Oder_Main_DAO{
	
	DataSource dataSource;

	public Oder_Main_DAO_Interface() {
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
