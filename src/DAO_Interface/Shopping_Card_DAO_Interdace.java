package DAO_Interface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
	public int insert(User_Account user_Account, Product product,Shopping_Cart shopping_cart) {
		// TODO Auto-generated method stub
		int count = 0;
		String sql = "INSERT INTO SHOPPING_CART" + "(ACCOUNT_ID,PRODUCT_ID,AMOUNT,MODIFY_DATE) "
				+ "VALUES(?, ?, ?, ?);";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {
			ps.setInt(1, user_Account.getAccount_ID());
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
			ps.setInt(1, shopping_Cart.getAccount_ID());
			ps.setInt(2, shopping_Cart.getProduct_ID());
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
		return null;
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
