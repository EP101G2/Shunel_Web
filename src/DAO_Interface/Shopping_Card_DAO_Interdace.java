package DAO_Interface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

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

//		INSERT INTO SHOPPING_CART (ACCOUNT_ID, PRODUCT_ID,AMOUNT) VALUES (?,?,?);

		String sql = "INSERT INTO SHOPPING_CART (ACCOUNT_ID, PRODUCT_ID,AMOUNT) VALUES (?,?,?);";
//		String sql = "INSERT INTO SHOPPING_CART" + "(ACCOUNT_ID,PRODUCT_ID,AMOUNT,MODIFY_DATE) "
//				+ "VALUES(?, ?, ?, ?);";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {
			ps.setString(1, user_Account.getAccount_ID());
			ps.setInt(2, product.getProduct_ID());
			ps.setInt(3, shopping_cart.getShopping_Cart_Amount());
			ps.setTimestamp(4, product.getProduct_MODIFY_DATE());
			count = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;

	}

	@Override
	public int insert(Shopping_Cart shopping_Cart) {
		// TODO Auto-generated method stub
		int count = 0;
		String sql = "INSERT INTO SHOPPING_CART" + "(ACCOUNT_ID,PRODUCT_ID,AMOUNT,MODIFY_DATE) "
				+ "VALUES(?, ?, ?, ?);";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {
//			ps.setString(1, shopping_Cart.getAccount_ID());
//			ps.setString(2, shopping_Cart.getProduct_ID());
			ps.setInt(1, shopping_Cart.getTAccount_ID());
			ps.setInt(2, shopping_Cart.getTProduct_ID());
			ps.setInt(3, shopping_Cart.getShopping_Cart_Amount());
			ps.setTimestamp(4, null);
			count = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public int delete(int accoumt_ID, int product_ID) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Shopping_Cart> getAll() {
		// TODO Auto-generated method stub
		
		String sql = "SELECT * FROM SHOPPING_CART;";
		List<Shopping_Cart> shopping_Carts = new ArrayList<Shopping_Cart>();
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
//				String account_ID = rs.getString(1);
//				String product_ID = rs.getString(2);
				int account_ID = rs.getInt(1);
				int product_ID = rs.getInt(2);
				int amunt = rs.getInt(3);
//				Timestamp time = rs.getString(4);
				
				Shopping_Cart shopping_Cart = new Shopping_Cart(account_ID, product_ID, amunt);
				shopping_Carts.add(shopping_Cart);
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
	public int delete(int product_ID) {
		// TODO Auto-generated method stub
		return 0;
	}

}
