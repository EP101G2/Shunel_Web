package DAO_Interface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import Bean.Order_Detail;
import Bean.Product;
import DAO.Order_Detail_DAO;
import Servlet_Shunel.ServiceLocator;

public class Order_Detail_DAO_Interface implements Order_Detail_DAO {

	DataSource dataSource;

	public Order_Detail_DAO_Interface() {
		dataSource = ServiceLocator.getInstance().getDataSource();

	}

	@Override
	public int insert(Order_Detail order_Detail) {
		// TODO Auto-generated method stub
//		System.out.print("----------------test099192389123----------------------");
		int count = 0;
		//INSERT INTO ORDER_MAIN (ACCOUNT_ID, TOTAL_PRICE, RECRIVER, ADDRESS) VALUES (?, ?, ?, ?);
//		INSERT INTO ORDER_DETAIL (ORDER_ID, PRODUCT_ID, AMOUNT, COLOR, BUY_PRICE) VALUES (?, ?, ?, ?, ?);
//		INSERT INTO ORDER_DETAIL (ORDER_ID, PRODUCT_ID, AMOUNT, COLOR) VALUES (?, ?, ?, ?);

		String sql = "INSERT INTO ORDER_DETAIL (ORDER_ID, PRODUCT_ID, AMOUNT, COLOR, BUY_PRICE) VALUES (?, ?, ?, ?, ?);";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {
			ps.setInt(1, order_Detail.getOrder_ID());
			ps.setInt(2, order_Detail.getProduct_ID());
			ps.setInt(3, order_Detail.getOrder_Detail_Amount());
			ps.setString(4, order_Detail.getColor());
			ps.setInt(5, order_Detail.getOrder_Detail_Buy_Price());
			count = ps.executeUpdate();
			
			System.out.print("----------------test11111112222222----------------------"+ps.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;

	}

	@Override
	public int update(Order_Detail order_Detail) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int Order_ID) {
		// TODO Auto-generated method stub
		int count = 0;
		String sql = "DELETE FROM ORDER_DETAIL WHERE (ORDER_ID = ?) and (PRODUCT_ID = ?);";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {
			ps.setInt(1, Order_ID);
//			ps.setInt(2, Product_ID);
			count = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public byte[] getImage(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order_Detail findById(int Order_ID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order_Detail> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
