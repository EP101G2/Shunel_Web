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
import Bean.orderStatistics;
import DAO.Order_Main_DAO;
//import DAO.Order_Main_Short;
import Servlet_Shunel.ServiceLocator;
//import idv.ron.server.spots.Spot;

public class Oder_Main_DAO_Interface implements Order_Main_DAO {

	private static final int orderMainID = 0;
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

				PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			
			
			ps.setString(1, oM.getAccount_ID());
			ps.setInt(2, oM.getOrder_Main_Total_Price());
			ps.setString(3, oM.getOrder_Main_Receiver());
			ps.setString(4, oM.getOrder_Main_Address());
			ps.setString(5, oM.getOrder_Main_Phone());
			System.out.println("---insertOrders---"+ps.toString());
			
//			ps.setInt(6, oM.getOrder_Main_Order_Status());
			count = ps.executeUpdate();
			ResultSet generatedKeys = ps.getGeneratedKeys();

			while (generatedKeys.next()) {
				id = generatedKeys.getInt(1);
			}
//			System.out.print("取得order_id :" + id);


			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	
	@Override
	public int update(Order_Main oM) {
		// TODO Auto-generated method stub
//		UPDacATE ORDER_MAIN SET RECRIVER = ?, ADDRESS = ?,PHONE = ? WHERE (ORDER_ID = ?);
		System.out.print("---OrderMainDao: update---");
		int count = 0;
		String sql = "UPDATE ORDER_MAIN SET RECRIVER = ?, ADDRESS = ?,PHONE = ? WHERE (ORDER_ID = ?);";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {

			ps.setString(1, oM.getOrder_Main_Receiver());
			ps.setString(2, oM.getOrder_Main_Address());
			ps.setString(3, oM.getOrder_Main_Phone());
			ps.setInt(4, oM.getOrder_ID());
			count = ps.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return count;
	}
	
	@Override
	public int updateStatus(int orderId, int status) {
		
		System.out.println("---OrderMainDao: updateStatus---");
		int count = 0;
		String sql = "UPDATE ORDER_MAIN SET ORDER_STATUS = ? WHERE ORDER_ID = ?;";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {

			ps.setInt(1, status);
			ps.setInt(2, orderId);
			count = ps.executeUpdate();
		} catch (Exception e){
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
			while (rs.next()) {
				int order_ID = rs.getInt("ORDER_ID");
				String account_ID = rs.getString("ACCOUNT_ID");
				int total_Price = rs.getInt("TOTAL_PRICE");
				String recriver = rs.getString("RECRIVER");
				String address = rs.getString("ADDRESS");
				String phone = rs.getString("PHONE");
				Timestamp order_Date = rs.getTimestamp("ORDER_DATE");
				int status = rs.getInt("ORDER_STATUS");
				Timestamp modify_Date = rs.getTimestamp("MODIFY_DATE");
				
				
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
	public Order_Main getOrderMain(int orderID) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM ORDER_MAIN WHERE ORDER_ID = ?;";
		Order_Main order_Main = null;
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)) {
//			ps.setString(1, account_ID);
			ps.setInt(1, orderID);
			/*
			 * 當Statement關閉，ResultSet也會自動關閉， 可以不需要將ResultSet宣告置入try with
			 * resources小括號內，參看ResultSet說明
			 */
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
//				int orderID = rs.getInt("ORDER_ID");
				String account_ID = rs.getString("ACCOUNT_ID");
				int order_Main_Total_Price = rs.getInt("TOTAL_PRICE");
				String order_Main_Receiver = rs.getString("RECRIVER");
				String order_Main_Address = rs.getString("ADDRESS");
				String order_Main_Phone = rs.getString("PHONE");
				Timestamp Order_Main_Order_Date = rs.getTimestamp("ORDER_DATE");
				int order_Status = rs.getInt("ORDER_STATUS");
				Timestamp Order_Main_Modify_Date = rs.getTimestamp("MODIFY_DATE");
				order_Main = new Order_Main(orderID, account_ID, order_Main_Total_Price, order_Main_Receiver, order_Main_Address, order_Main_Phone, Order_Main_Order_Date,
						order_Status, Order_Main_Modify_Date); 
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return order_Main;
	}

	@Override
	public Order_Main findById(String account_ID) {
//	public Order_Main findById(int Order_Id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM ORDER_MAIN WHERE (ACCOUNT_ID = ?) and (ORDER_ID = ?);";
		Order_Main order_Main = null;
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setString(1, account_ID);
//			ps.setInt(1, Order_Id);
			/*
			 * 當Statement關閉，ResultSet也會自動關閉， 可以不需要將ResultSet宣告置入try with
			 * resources小括號內，參看ResultSet說明
			 */
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int order_ID = rs.getInt("ORDER_ID");
				int order_Main_Total_Price = rs.getInt("TOTAL_PRICE");
				String order_Main_Receiver = rs.getString("RECRIVER");
				String order_Main_Address = rs.getString("ADDRESS");
				String order_Main_Phone = rs.getString("PHONE");
				Timestamp Order_Main_Order_Date = rs.getTimestamp("ORDER_DATE");
				int order_Status = rs.getInt("ORDER_STATUS");
				Timestamp Order_Main_Modify_Date = rs.getTimestamp("MODIFY_DATE");
				order_Main = new Order_Main(order_ID, account_ID, order_Main_Total_Price, order_Main_Receiver, order_Main_Address, order_Main_Phone, Order_Main_Order_Date,
						order_Status, Order_Main_Modify_Date); 
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
		String sql = "SELECT ORDER_STATUS FROM ORDER_MAIN WHERE (ORDER_ID = ?);";
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
		System.out.println("===================1==================");
		int Count = 0;
		String sql = "UPDATE ORDER_MAIN SET ORDER_STATUS = 1 WHERE ORDER_ID = ?;";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);
				
				){
			ps.setInt(1, oM_ID);
			System.out.println("===================1=================="+ps.toString());
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
	
//	get short orderlist
	@Override
	public List<Order_Main> getShortOrderMains(String user_id) {
		String sql = "SELECT ORDER_ID, ORDER_STATUS FROM Shunel.ORDER_MAIN WHERE Shunel.ORDER_MAIN.ACCOUNT_ID = ?;";
		
		List<Order_Main> orderMainShortList = new ArrayList<>();
		Order_Main orderMainShort = null;
		System.out.println("-----orderMainDao.getShortOrderMains-----");
		
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {
			ps.setString(1, user_id);
			System.out.println(connection.isClosed());
//			System.out.println(ps.isClosed());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
//				System.out.print("-----for get short OrderMains in Order Main Dao-----");
				int order_ID = rs.getInt("ORDER_ID");
				int status = rs.getInt("ORDER_STATUS");
				orderMainShort = new Order_Main(order_ID, status);
				orderMainShortList.add(orderMainShort);
			}	
			return orderMainShortList;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderMainShortList;
	}//ok
	
//	get short order details
	public List<Order_Main> getShortOrderDetails(int order_ID){
		String sql = "SELECT ORDER_STATUS, TOTAL_PRICE, RECRIVER, ADDRESS, PHONE FROM Shunel.ORDER_MAIN WHERE Shunel.ORDER_MAIN.ORDER_ID = ?;";
		List<Order_Main> orderDetShortList = new ArrayList<>();
		
		Order_Main orderDetShort = null;
		System.out.println("-----orderMainDao.getShortOrderDet-----");
		
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {
			ps.setInt(1, order_ID);
			System.out.println(ps.isClosed());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.print("-----for get short OrderMains in Order Main Dao-----");
				int status = rs.getInt("ORDER_STATUS");
				int totalPrice = rs.getInt("TOTAL_PRICE");
				String receiver = rs.getString("RECRIVER");
				String address = rs.getString("ADDRESS");
				String phone = rs.getString("PHONE");
				
				orderDetShort = new Order_Main(order_ID, status, totalPrice, receiver, address, phone);
				orderDetShortList.add(orderDetShort);
			}	
			return orderDetShortList;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return orderDetShortList;
	}
	
//	get data for order management fragment (main) 
	@Override
	public List<Order_Main> getOrdersForManage(){
		String sql = "SELECT ORDER_ID, ACCOUNT_ID, TOTAL_PRICE, ORDER_DATE, ORDER_STATUS, MODIFY_DATE, RECRIVER, ADDRESS, PHONE FROM Shunel.ORDER_MAIN;";
		
		List<Order_Main> orderManageList = new ArrayList<>();
		System.out.println("-----orderMainDao.getOrdersForManage-----");
		
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {
			System.out.println(ps.isClosed());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int order_ID = rs.getInt("ORDER_ID");
				String account_ID = rs.getString("ACCOUNT_ID");
				int order_Main_Total_Price = rs.getInt("TOTAL_PRICE");
				Timestamp order_Main_Order_Date = rs.getTimestamp("ORDER_DATE");
				int order_Main_Order_Status = rs.getInt("ORDER_STATUS");
				Timestamp order_Main_Modify_Date = rs.getTimestamp("MODIFY_DATE");
				String recNameString = rs.getString("RECRIVER");
				String recAddressString = rs.getString("ADDRESS");
				String recPhoneString = rs.getString("PHONE");
				
				Order_Main orderMainManage = new Order_Main(order_ID, account_ID, order_Main_Total_Price, recNameString, recAddressString, recPhoneString, 
						order_Main_Order_Date, order_Main_Order_Status, order_Main_Modify_Date);
				orderManageList.add(orderMainManage);
				System.out.print(rs);
			}	
			return orderManageList;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderManageList;
	}

	
	
	/*取得銷售統計數量-----------------------------------------------------------------------------------------------------------------------------------------*/
	@Override
	public List<orderStatistics> getStatistics(Timestamp date1, Timestamp date2) {
		// TODO Auto-generated method stub
		
		String sql = "SELECT od.PRODUCT_ID ,pd.CATEGORY_ID,count(pd.CATEGORY_ID) AS countCATEGORY_ID,sum(od.BUY_PRICE) AS sumBUY_PRICE FROM  ORDER_DETAIL od";
		sql+="JOIN PRODUCT pd on od.PRODUCT_ID = pd.PRODUCT_ID";
		sql+="JOIN ORDER_MAIN om on od.ORDER_ID = om.ORDER_ID";
		sql+="WHERE om.ORDER_DATE BETWEEN ? AND ?";
		sql+="group by od.PRODUCT_ID,pd.CATEGORY_ID;";
		
		List<orderStatistics> oList = new ArrayList<orderStatistics>();
		orderStatistics oStatistics =null;
		
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);){
			ps.setTimestamp(1, date1);
			ps.setTimestamp(2, date2);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int product_ID = rs.getInt("PRODUCT_ID");
				int CATEGORY_ID = rs.getInt("CATEGORY_ID");
				int countCATEGORY_ID = rs.getInt("CATEGORY_ID");
				int sumBUY_PRICE = rs.getInt("sumBUY_PRICE");
				oStatistics = new orderStatistics(product_ID, CATEGORY_ID, countCATEGORY_ID, sumBUY_PRICE);
				oList.add(oStatistics);
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return oList;
	}
	
	
	
}
