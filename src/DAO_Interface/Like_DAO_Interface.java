package DAO_Interface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import Bean.Like;
import Bean.Product;
import Bean.Shopping_Cart;
import DAO.Like_DAO;
import Servlet_Shunel.ServiceLocator;

public class Like_DAO_Interface implements Like_DAO {

	DataSource dataSource;

	@Override
	public int insert(Like like) {
		// TODO Auto-generated method stub
		int count = 0;

		return count;
	}

	public Like_DAO_Interface() {
		dataSource = ServiceLocator.getInstance().getDataSource();

	}

	@Override
	public void insert(int Account_ID, int Product_ID) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Like like) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int Account_ID, int Product_ID) {
		// TODO Auto-generated method stub

	}

	@Override
	public Like findById(int Account_ID, int Product_ID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Like> getAll(String id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM Shunel.LIKE WHERE ACCOUNT_ID = '?'";
		List<Like> likes = new ArrayList<Like>();
		Like like = null;
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
//			ps.setInt(1, rs.getInt(1));
				String account_ID = rs.getString(1);
				int product_ID = rs.getInt(2);
				like = new Like(account_ID, product_ID);
				likes.add(like);
			}
			return likes;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return likes;
	}

	@Override
	public int delete(int id) {
		int count = 0;
		String sql = "DELETE FROM Shunel.LIKE WHERE PRODUCT_ID = ?;";

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
	public Product searchLike(String account_id, int product_id) {
		// TODO Auto-generated method stub

		String sql = "SELECT * FROM Shunel.LIKE Where Shunel.LIKE.ACCOUNT_ID = ? AND Shunel.LIKE.PRODUCT_ID = ? ;";
		System.out.println("---------searchLike SQL--------");
		Product product = null;
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {
			ps.setString(1, account_id);
			ps.setInt(2, product_id);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int product_ID = rs.getInt("PRODUCT_ID");
				// System.out.println(product_ID+"");
				product = new Product(product_ID);
			}

			return product;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return product;
	}

	@Override
	public int insertLike(String account_id, int product_id) {
		// TODO Auto-generated method stub
		int count = 0 ;
		String sql = "INSERT INTO Shunel.LIKE (ACCOUNT_ID, PRODUCT_ID) VALUES ( ? , ? );";
		System.out.println("---------insertLike SQL--------");
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {
			ps.setString(1, account_id);
			ps.setInt(2, product_id);

			count=ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return count;
	}

	@Override
	public int deleteLike(String account_id, int product_id) {
		// TODO Auto-generated method stub
		int count = 0 ;
		String sql = "DELETE FROM Shunel.LIKE WHERE (ACCOUNT_ID = ?) and (PRODUCT_ID = ?);";
		System.out.println("---------deleteLike SQL--------");
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {
			ps.setString(1, account_id);
			ps.setInt(2, product_id);

			count=ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return count;
		
	}

}
