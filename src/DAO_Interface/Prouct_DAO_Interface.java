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
import DAO.Product_DAO;
import Servlet_Shunel.ServiceLocator;

public class Prouct_DAO_Interface implements Product_DAO {

	DataSource dataSource;

	public Prouct_DAO_Interface() {
		dataSource = ServiceLocator.getInstance().getDataSource();
	}

	

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
		
		

	}

	@Override
	public Product findById(int prouct_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getAll() {
		// TODO Auto-generated method stub

		String sql = "SELECT * FROM PRODUCT;";

		List<Product> prouctList = new ArrayList<Product>();
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {

			System.out.println(connection.isClosed());
			System.out.println(ps.isClosed());

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				System.out.println("--------");
				int id = rs.getInt("PRODUCT_ID");
				String prouct_Name = rs.getString("PRODUCT_NAME");
				String prouct_Color = rs.getString("COLOR");
				int prouct_Price = rs.getInt("PRICE");
				String prouct_Dital = rs.getString("DITAL");
				int prouct_Category_ID = rs.getInt("CATEGORY_ID");
				int prouct_Status = rs.getInt("PRODUCT_STATUS");
				Timestamp prouct_Time = rs.getTimestamp("MODIFY_DATE");

				Product prouct = new Product(id, prouct_Name, prouct_Color, prouct_Price, prouct_Dital,
						prouct_Category_ID, prouct_Status, prouct_Time);
				prouctList.add(prouct);
			}
			return prouctList;

		} catch (Exception e) {

			e.printStackTrace();
			// TODO: handle exception
		}

		return prouctList;
	}

	@Override
	public byte[] getImage(int id) {
		String sql = "SELECT image FROM PRODUCT WHERE PRODUCT_ID = ?;";
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



	@Override
	public void insert(Product prouct, byte[] image) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void update(Product prouct, byte[] image) {
		// TODO Auto-generated method stub
		
		int count = 0;
		String sql="";
		if (image!=null) {
			sql="UPDATE PRODUCT SET PRODUCT_NAME= ?,COLOR=?";
			
			
		}else {
			
		}
		
	}

}
