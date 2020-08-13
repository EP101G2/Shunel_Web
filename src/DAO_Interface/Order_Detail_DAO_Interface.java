package DAO_Interface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import Bean.Order_Detail;
import Bean.Order_Main;
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
		
		int count = 0;
		int id = 0;
		String sql = "INSERT INTO ORDER_DETAIL (ORDER_ID, PRODUCT_ID, AMOUNT, COLOR, BUY_PRICE) VALUES (?, ?, ?, ?, ?);";
		
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
					
					ps.setInt(1, order_Detail.getOrder_ID());
					ps.setInt(2, order_Detail.getProduct_ID());
					ps.setInt(3, order_Detail.getOrder_Detail_Amount());
					ps.setString(4, order_Detail.getColor());
					ps.setInt(5, order_Detail.getOrder_Detail_Buy_Price());
					count = ps.executeUpdate();
					ResultSet generatedKeys = ps.getGeneratedKeys();
					
					while (generatedKeys.next()) {
						id = generatedKeys.getInt(1);
					}
					System.out.print("取得order_id :" + id);
			
					System.out.print("----------------testOrderDetail----------------------"+ps.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;

	}

	@Override
	public int update(Order_Detail order_Detail) {
		// TODO Auto-generated method stub
		int count = 0;
		String sql = "UPDATE ORDER_MAIN SET AMOUNT = ?, COLOR = ?,BUY_PRICE = ? WHERE (ORDER_ID = ?);";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {

			ps.setInt(1, order_Detail.getOrder_Detail_Amount());
			ps.setString(2, order_Detail.getColor());
			ps.setInt(3, order_Detail.getOrder_Detail_Buy_Price());
			ps.setInt(4, order_Detail.getOrder_ID());
			count = ps.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
			  e.printStackTrace();
		}
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
		String sql = "SELECT * FROM ORDER_DETAIL WHERE ORDER_ID = ?;";
		Order_Detail order_Detail = null;
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {
			ps.setInt(1, Order_ID);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				int  order_Detail_Amount= rs.getInt(1);
				int Product_ID = rs.getInt(2);
				int order_Detail_Buy_Price = rs.getInt(3);
				String color = rs.getString(4);
				order_Detail = new Order_Detail(Order_ID, order_Detail_Amount, Product_ID, order_Detail_Buy_Price, color); 
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return order_Detail;
	}

	@Override
	public List<Order_Detail> getAll() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM Shunel.ORDER_DETAIL;";
		List<Order_Detail> orderDetailsList = new ArrayList<Order_Detail>();
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
				int product_ID = rs.getInt("PRODUCT_ID");
				int order_Detail_Amount = rs.getInt("AMOUNT");
				String color = rs.getString("COLOR");
				int order_Detail_Buy_Price = rs.getInt("BUY_PRICE");
				
				Order_Detail orderDetails = new Order_Detail(order_ID, order_Detail_Amount, product_ID, order_Detail_Buy_Price, color);
				orderDetailsList.add(orderDetails);
				System.out.print(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("OrderMainDAO"+orderDetailsList);
		return orderDetailsList;
	}

	@Override
	public List<Order_Detail> getProductForOrders(int Order_ID) {
		// TODO Auto-generated method stub
		String sql = "SELECT PRODUCT_ID FROM Shunel.ORDER_DETAIL WHERE Shunel.ORDER_DETAIL.ORDER_ID = ?";
		
		List<Order_Detail> orderProductList = new ArrayList<>();
		Order_Detail orderProduct = null;
		System.out.println("--getProductForOrders--");
//		int product_ID = 0;
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {
			
			ps.setInt(1, Order_ID);
			
//			System.out.println(ps.isClosed());
			System.out.println("orderId: "+Order_ID);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				int product_ID = rs.getInt("PRODUCT_ID");
				System.out.println("productId: "+product_ID);
				
				orderProduct = new Order_Detail(product_ID);
				orderProductList.add(orderProduct);
				System.out.print("ID of Products in choosen order: "+orderProductList);
			}
			return orderProductList;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return orderProductList;
	}

	@Override
	public List<Order_Detail> getOrderedProducts(int Order_ID) {
		// TODO Auto-generated method stub
		String sql = "SELECT pd.PRODUCT_NAME, od.BUY_PRICE, od.PRODUCT_ID FROM ORDER_DETAIL od JOIN PRODUCT pd on od.PRODUCT_ID = pd.PRODUCT_ID WHERE od.ORDER_ID = ?;";
		List<Order_Detail> orderedProductList = new ArrayList<>();
		Order_Detail orderedProduct = null;
		System.out.println("--getOrderedProducts--");
		
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {
			ps.setInt(1, Order_ID);
//			System.out.println(ps.isClosed());
			System.out.println("OrderId: "+Order_ID);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				String product_Name = rs.getString("PRODUCT_NAME");
				int buy_Price = rs.getInt("BUY_PRICE");
				int Product_ID = rs.getInt("PRODUCT_ID");

//				problem: how to insert Product.product_name into List<Order_Detail>?
				orderedProduct = new Order_Detail(product_Name, buy_Price, Product_ID);
				orderedProductList.add(orderedProduct);
				System.out.print("Products name and buy price in choosen order: "+orderedProductList);
			}
			return orderedProductList;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return orderedProductList;
	}
}
