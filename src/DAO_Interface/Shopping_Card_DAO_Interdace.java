package DAO_Interface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.mysql.cj.jdbc.exceptions.SQLError;

import Bean.Product;
import Bean.Shopping_Cart;
import Bean.User_Account;
import DAO.Shopping_Card_DAO;
import Servlet_Shunel.ServiceLocator;

public abstract class Shopping_Card_DAO_Interdace implements Shopping_Card_DAO {

	DataSource dataSource;

	public Shopping_Card_DAO_Interdace() {
		dataSource = ServiceLocator.getInstance().getDataSource();
	}

	@Override
	public int insert(User_Account user_Account, Product product, Shopping_Cart shopping_cart) {
		// TODO Auto-generated method stub
		int count = 0;

////		INSERT INTO SHOPPING_CART (ACCOUNT_ID, PRODUCT_ID,AMOUNT) VALUES (?,?,?);
//
//		String sql = "INSERT INTO SHOPPING_CART (ACCOUNT_ID, PRODUCT_ID,AMOUNT) VALUES (?,?,?);";
////		String sql = "INSERT INTO SHOPPING_CART" + "(ACCOUNT_ID,PRODUCT_ID,AMOUNT,MODIFY_DATE) "
////				+ "VALUES(?, ?, ?, ?);";
//		try (Connection connection = dataSource.getConnection();
//				PreparedStatement ps = connection.prepareStatement(sql);) {
//			ps.setString(1, user_Account.getAccount_ID());
//			ps.setInt(2, product.getProduct_ID());
//			ps.setInt(3, shopping_cart.getShopping_Cart_Amount());
//			ps.setTimestamp(4, product.getProduct_MODIFY_DATE());
//			System.out.println("1111" + ps.toString());
//			count = ps.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		return count;

	}

	@Override
	public int insert(Shopping_Cart shopping_Cart) {
		// TODO Auto-generated method stub
		int count = 0;
//		ACCOUNT_ID`, `PRODUCT_ID`, `PRODUCT_NAME`, `AMOUNT`, `COLOR`, `PRICE`
		String sql = "INSERT INTO SHOPPING_CART (ACCOUNT_ID, PRODUCT_ID, PRODUCT_NAME, AMOUNT, COLOR,PRICE) VALUES (?, ?, ?, ?, ?,?);";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {
			ps.setString(1, shopping_Cart.getAccount_ID());
			ps.setInt(2, shopping_Cart.getProduct_ID());
			ps.setString(3, shopping_Cart.getProduct_Name());
			ps.setInt(4, shopping_Cart.getAmount());
			ps.setString(5, shopping_Cart.getColor());
			ps.setInt(6, shopping_Cart.getPrice());
			System.out.println("3333" + ps.toString());
			count = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public int delete(int accoumt_ID, int product_ID) {
		// TODO Auto-generated method stub
//		int count = 0;
//		String sql="DELETE FROM SHOPPING_CART WHERE (ACCOUNT_ID = ?) and (PRODUCT_ID = ?);";
//		
//		try (Connection connection = dataSource.getConnection();
//				PreparedStatement ps = connection.prepareStatement(sql);) {
//			ps.setInt(1, accoumt_ID);
//			ps.setInt(2, product_ID);
//			count = ps.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} 
		return 0;
	}

	@Override
	public List<Shopping_Cart> getAll() {
		// TODO Auto-generated method stub
//SELECT * FROM SHOPPING_CART WHERE ACCOUNT_ID = 'S';
		String sql = "SELECT * FROM SHOPPING_CART;";
		List<Shopping_Cart> shopping_Carts = new ArrayList<Shopping_Cart>();
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
//				ps.setInt(1, shopping_Carts.g);
				String account_ID = rs.getString(1);
				int product_ID = rs.getInt(2);
				String product_Name = rs.getString(3);
				int amount = rs.getInt(4);
				String color = rs.getString(5);
				int price = rs.getInt(6);
//				Timestamp time = rs.getTimestamp(7);
//
//				Shopping_Cart shopping_Cart = new Shopping_Cart(account_ID, product_ID, product_Name, amount, color,price, time);
				Shopping_Cart shopping_Cart = new Shopping_Cart(account_ID, product_ID, product_Name, amount, color,
						price);
				shopping_Carts.add(shopping_Cart);
			}
			return shopping_Carts;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return shopping_Carts;
	}

	@Override
	public List<Shopping_Cart> getAll(String id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM SHOPPING_CART WHERE ACCOUNT_ID = ?;";
		List<Shopping_Cart> shopping_Carts = new ArrayList<Shopping_Cart>();
		Shopping_Cart shopping = null;
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
//				ps.setInt(1, rs.getInt(1));
				String account_ID = rs.getString(1);
				int product_ID = rs.getInt(2);
				String product_Name = rs.getString(3);
				int amount = rs.getInt(4);
				String color = rs.getString(5);
				int price = rs.getInt(6);
				shopping = new Shopping_Cart(account_ID, product_ID, product_Name, amount, color, price);
				shopping_Carts.add(shopping);
			}
			return shopping_Carts;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return shopping_Carts;
	}

	@Override
	public byte[] getImage(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(Product product) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		int count = 0;
		String sql = "DELETE FROM SHOPPING_CART WHERE PRODUCT_ID = ?;";

		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {
//			ps.setString(1, shopping_Cart.getAccount_ID());
			ps.setInt(1, id);

			count = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public int delete(Shopping_Cart shopping_Cart) {
		// TODO Auto-generated method stub

		int count = 0;
		String sql = "DELETE FROM SHOPPING_CART WHERE PRODUCT_ID = ?;";

		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {
//			ps.setString(1, shopping_Cart.getAccount_ID());
			ps.setInt(1, shopping_Cart.getProduct_ID());

			count = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

}
