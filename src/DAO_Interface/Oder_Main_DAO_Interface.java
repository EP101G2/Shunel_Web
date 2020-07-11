package DAO_Interface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

//import com.mysql.cj.xdevapi.Statement;

import Bean.Order_Detail;
import Bean.Order_Main;
import DAO.Oder_Main_DAO;
import DAO.Order_Main_DAO;
import Servlet_Shunel.ServiceLocator;
//import idv.ron.server.spots.Spot;

public class Oder_Main_DAO_Interface implements Order_Main_DAO {

	DataSource dataSource;

	public Oder_Main_DAO_Interface() {
		dataSource = ServiceLocator.getInstance().getDataSource();
	}

	@Override
	public int insert(Order_Main oM) {
		// TODO Auto-generated method stub

		int count = 0;
		int	id = 0;
		String sql = "INSERT INTO ORDER_MAIN (ACCOUNT_ID, TOTAL_PRICE, RECRIVER, ADDRESS) VALUES (?, ?, ?, ?);";

		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, oM.getAccount_ID());
			ps.setInt(2, oM.getOrder_Main_Total_Price());
			ps.setString(3, oM.getOrder_Main_Recriver());
			ps.setString(4, oM.getOrder_Main_Address());
			count = ps.executeUpdate();
			ResultSet generatedKeys = ps.getGeneratedKeys();
//			if(count!= 0) {
//				ResultSet rs=ps.getGeneratedKeys();
//						if(rs.next()) {
//							id = rs.getInt(1);
//							System.out.print("回傳PK"+rs);
//						}
//			}
//			ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
			while (generatedKeys.next()) {
			    id = generatedKeys.getInt(1);
			    
			}
			System.out.print("id"+id);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public int update(Order_Main oM) {
		// TODO Auto-generated method stub
		
		int count = 0;
		String sql = "";
		return 0;
	}

	@Override
	public int delete(int oM_ID) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Order_Main> getAll() {
		// TODO Auto-generated method stub
		String sql = "SELECT ACCOUNT_ID, TOTAL_PRICE, RECRIVER, ADDRESS, PHONE, ORDER_STATUS,ORDER_DATE,MODIFY_DATE FROM ORDER_MAIN WHERE ORDER_ID = ?;";
		List<Order_Main> oMList = new ArrayList<Order_Main>();
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {
			/*
			 * 當Statement關閉，ResultSet也會自動關閉， 可以不需要將ResultSet宣告置入try with
			 * resources小括號內，參看ResultSet說明
			 */
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String account_ID = rs.getString(1);
				int total_Price = rs.getInt(2);
				String recriver = rs.getString(3);
				String address = rs.getString(4);
				String phone = rs.getString(5);
				int status = rs.getInt(6);
				Timestamp order_Date = rs.getTimestamp(7);
				Timestamp modify_Date = rs.getTimestamp(8);
				int order_ID = rs.getInt(9);
//				Order_Main oM = new Order_Main(order_ID, account_ID, total_Price, recriver, address, phone, order_Date,
//						status, modify_Date);
//				oMList.add(oM);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return oMList;
	}

	@Override
	public Order_Main findById(int Order_ID) {
		// TODO Auto-generated method stub
		return null;
	}

}
