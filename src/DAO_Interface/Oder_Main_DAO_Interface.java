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

import DAO.Order_Main_DAO;
//import DAO.Order_Main_Short;
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
		int id = 0;
		String sql = "INSERT INTO ORDER_MAIN (ACCOUNT_ID, TOTAL_PRICE, RECRIVER, ADDRESS, PHONE) VALUES ( ?, ?, ?, ?, ?);";

		try (Connection connection = dataSource.getConnection();
<<<<<<< HEAD
				PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, oM.getAccount_ID());
			ps.setInt(2, oM.getOrder_Main_Total_Price());
			ps.setString(3, oM.getOrder_Main_Recriver());
			ps.setString(4, oM.getOrder_Main_Address());
			ps.setString(5, oM.getOrder_Main_Phone());
//			ps.setInt(6, oM.getOrder_Main_Order_Status());
			count = ps.executeUpdate();
			ResultSet generatedKeys = ps.getGeneratedKeys();

			while (generatedKeys.next()) {
				id = generatedKeys.getInt(1);
			}
			System.out.print("取得order_id :" + id);
=======
			PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
				ps.setString(1, oM.getAccount_ID());
				ps.setInt(2, oM.getOrder_Main_Total_Price());
				ps.setString(3, oM.getOrder_Main_Recriver());
				ps.setString(4, oM.getOrder_Main_Address());
				ps.setString(5, oM.getOrder_Main_Phone());
				ps.setInt(6, oM.getOrder_Main_Order_Status());
				count = ps.executeUpdate();
				ResultSet generatedKeys = ps.getGeneratedKeys();

				while (generatedKeys.next()) {
					id = generatedKeys.getInt(1);
				}
				System.out.print("取得order_id :" + id);
>>>>>>> 36a4484aea2b9a3226f57832225a483d6f5179f6

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public int update(Order_Main oM) {
		// TODO Auto-generated method stub
//		UPDacATE ORDER_MAIN SET RECRIVER = ?, ADDRESS = ?,PHONE = ? WHERE (ORDER_ID = ?);
		int count = 0;
		String sql = "UPDATE ORDER_MAIN SET RECRIVER = ?, ADDRESS = ?,PHONE = ? WHERE (ORDER_ID = ?);";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {

			ps.setString(1, oM.getOrder_Main_Recriver());
			ps.setString(2, oM.getOrder_Main_Address());
			ps.setString(3, oM.getOrder_Main_Phone());
			ps.setInt(4, oM.getOrder_ID());
//			ps.setString(1, oM.getOrder_Main_Recriver());
			count = ps.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return count;
	}

	@Override
	public int delete(int oM_ID) {
		// TODO Auto-generated method stub
		int count = 0;
		String sql = "DELETE FROM ORDER_DETAIL WHERE (ORDER_ID = ?) and (PRODUCT_ID = ?);";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {
			ps.setInt(1, oM_ID);
			count = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public List<Order_Main> getAll() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM Shunel.ORDER_MAIN;";
		List<Order_Main> oMList = new ArrayList<Order_Main>();
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {
//			ps.setString(1, oMList.);
			/*
			 * 當Statement關閉，ResultSet也會自動關閉， 可以不需要將ResultSet宣告置入try with
			 * resources小括號內，參看ResultSet說明
			 */
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int order_ID = rs.getInt("ORDER_ID");
				String account_ID = rs.getString("ACCOUNT_ID");
				int total_Price = rs.getInt("TOTAL_PRICE");
				String recriver = rs.getString("RECRIVER");
				String address = rs.getString("ADDRESS");
				String phone = rs.getString("PHONE");
				Timestamp order_Date = rs.getTimestamp("ORDER_DATE");
				int status = rs.getInt("ORDER_STATUS");
				Timestamp modify_Date = rs.getTimestamp("MODIFY_DATE");
				
				
//				Order_Main oM = new Order_Main(order_ID);
//				Order_Main oM = new Order_Main(order_ID, account_ID, total_Price, recriver, address, phone,
//						status);
				Order_Main oM = new Order_Main(order_ID, account_ID, total_Price, recriver, address, phone, order_Date,
						status, modify_Date);
				oMList.add(oM);
				System.out.print(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("OrderMainDAO"+oMList);
		return oMList;
	}

	@Override
	public Order_Main findById(String Account_ID) {
//	public Order_Main findById(int Order_Id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM ORDER_MAIN WHERE ACCOUNT_ID = ?;";
		Order_Main order_Main = null;
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setString(1, Account_ID);
//			ps.setInt(1, Order_Id);
			/*
			 * 當Statement關閉，ResultSet也會自動關閉， 可以不需要將ResultSet宣告置入try with
			 * resources小括號內，參看ResultSet說明
			 */
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int Order_ID = rs.getInt(1);
				int TOTAL_PRICE = rs.getInt(2);
				String RECRIVER = rs.getString(3);
				String ADDRESS = rs.getString(4);
				String PHONE = rs.getString(5);
				Timestamp ORDER_DATE = rs.getTimestamp(6);
				int ORDER_STATUS = rs.getInt(7);
				Timestamp MODIFY_DATE = rs.getTimestamp(8);
				order_Main = new Order_Main(Order_ID, Account_ID, TOTAL_PRICE, RECRIVER, ADDRESS, PHONE, ORDER_DATE,
						ORDER_STATUS, MODIFY_DATE); 
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return order_Main;
	}

	@Override
	public int getStatus(int Order_Id) {
		// TODO Auto-generated method stub
		String sql = "SELECT STATUS FROM ORDER_MAIN WHERE (ORDER_ID = ?);";
//		List<Order_Main> orderMainList = new ArrayList<Order_Main>();
		int status = 0;
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)){
			ps.setInt(1, Order_Id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int ORDER_STATUS = rs.getInt("ORDER_STATUS");
				status = ORDER_STATUS;
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}
//update order status
	@Override
	public int updataOrder(int oM_ID) {
		// TODO Auto-generated method stub
		
		int Count = 0;
		String sql = "UPDATE ORDER_MAIN SET ORDER_STATUS = 1 WHERE (ORDER_ID = ?);";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);
				){
			ps.setInt(1, oM_ID);
			
			Count = ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return Count;
	}
	
	@Override
	public byte[] getImage(int id) {
		String sql = "SELECT PRODUCT_IMG1 FROM PRODUCT WHERE PRODUCT_ID = ?;";
		byte[] image = null;
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				image = rs.getBytes(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return image;
	}
	
////	get short orderlist
//	@Override
//	public List<Order_Main> getShortOrderMains(){
//		String sql = "SELECT ORDER_ID, ORDER_STATUS FROM ORDER_MAIN WHERE ACCOUNT_ID = ?;";
//		List<Order_Main> orderMainShortList = new ArrayList<>();
//		try (Connection connection = dataSource.getConnection();
//				PreparedStatement ps = connection.prepareStatement(sql);) {
//			ResultSet rs = ps.executeQuery();
//			if (rs.next()) {
//				int order_ID = rs.getInt("ORDER_ID");
//				int status = rs.getInt("ORDER_STATUS");
////				Order_Main orderMainShort = new Order_Main(order_ID, status);
////				orderMainShort.add(orderMainShortList);
//				System.out.print(rs);
//			}	
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return orderMainShortList;
//	}

}
