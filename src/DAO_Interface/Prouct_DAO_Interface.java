package DAO_Interface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import Bean.Order_Detail;
import Bean.Order_Main;
import Bean.Product;
import DAO.Product_DAO;
import Servlet_Shunel.ServiceLocator;

public class Prouct_DAO_Interface implements Product_DAO {

	DataSource dataSource;

	 public Prouct_DAO_Interface(){
		dataSource = ServiceLocator.getInstance().getDataSource();
		
	}

	@Override
	public int delete(int id) {
		return id;
		// TODO Auto-generated method stub

	}

	@Override
	public Product findById(int prouct_id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	//拿只有上架的商品
	@Override
	public List<Product> getSaleProduct() {
		// TODO Auto-generated method stub
		
		//String sql = "Select * From CATEGORY join PRODUCT on PRODUCT.CATEGORY_ID  = CATEGORY.CATEGORY_ID Where CATEGORY.CATEGORY_ID = 3;";
		String sql = "SELECT * FROM PRODUCT WHERE PRODUCT_STATUS = 1;";

		List<Product> prouctList = new ArrayList<Product>();
		System.out.println("333");
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
//				Timestamp prouct_Time = rs.getTimestamp("MODIFY_DATE");

				Product prouct = new Product(id, prouct_Name, prouct_Color, prouct_Price, prouct_Dital,
						prouct_Category_ID, prouct_Status);
				prouctList.add(prouct);
			}
			
			return prouctList;

		} catch (Exception e) {

			e.printStackTrace();
			// TODO: handle exception
		}

		return prouctList;
	}
	
	
	
	
	
	//拿所有商品照片 包含下架商品
	@Override
	public List<Product> getAll() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM PRODUCT;";
		List<Product> prouctList = new ArrayList<Product>();
		System.out.println("333");
		try (Connection connection = dataSource.getConnection();
			
				PreparedStatement ps = connection.prepareStatement(sql);) {

	
			System.out.println(connection.isClosed());
			System.out.println(ps.isClosed());


			ResultSet rs = ps.executeQuery();
//			System.out.println("test"+rs);
			while (rs.next()) {
				System.out.println("--------");
				int id = rs.getInt("PRODUCT_ID");
				String prouct_Name = rs.getString("PRODUCT_NAME");
				String prouct_Color = rs.getString("COLOR");
				int prouct_Price = rs.getInt("PRICE");
				String prouct_Dital = rs.getString("DITAL");
				int prouct_Category_ID = rs.getInt("CATEGORY_ID");
				int prouct_Status = rs.getInt("PRODUCT_STATUS");
//				Timestamp prouct_Time = rs.getTimestamp("MODIFY_DATE");

				Product prouct = new Product(id, prouct_Name, prouct_Color, prouct_Price, prouct_Dital,
						prouct_Category_ID, prouct_Status);
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

	@Override
	public  int insert(Product prouct, byte[] image, byte[] image2, byte[] image3) {
		int count = 0;
		String sql= "INSERT INTO PRODUCT";
		
		if (image!=null && image2==null && image3==null) {
			sql+="(PRODUCT_NAME,COLOR,PRICE,DITAL,CATEGORY_ID,PRODUCT_STATUS,PRODUCT_IMG1)";
			sql+="VALUES(?,?,?,?,?,?,?);";
		}else if (image!=null && image2!=null && image3 ==null) {
			sql+="(PRODUCT_NAME,COLOR,PRICE,DITAL,CATEGORY_ID,PRODUCT_STATUS,PRODUCT_IMG1,PRODUCT_IMG2)";
			sql+="VALUES(?,?,?,?,?,?,?,?);";
		}else if (image!=null && image2!=null && image3 !=null) {
			sql+="(PRODUCT_NAME,COLOR,PRICE,DITAL,CATEGORY_ID,PRODUCT_STATUS,PRODUCT_IMG1,PRODUCT_IMG2,PRODUCT_IMG3)";
			sql+="VALUES(?,?,?,?,?,?,?,?,?);";
		}else if (image!=null && image2!=null && image3 !=null) {
			sql+="(PRODUCT_NAME,COLOR,PRICE,DITAL,CATEGORY_ID,PRODUCT_STATUS)";
			sql+="VALUES(?,?,?,?,?,?);";
		}
		
		try(Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);
				) {
			ps.setString(1, prouct.getProduct_Name());
			ps.setString(2, prouct.getProduct_Color());
			ps.setInt(3, prouct.getProduct_Price());
			ps.setString(4, prouct.getProduct_Ditail());
			ps.setInt(5, prouct.getProduct_Category_ID());
			ps.setInt(6, prouct.getProduct_Status());
			if (image!=null) {
				ps.setBytes(7, image);
				
				if (image2!=null) {
					ps.setBytes(8, image2);
					if (image3!=null) {
						ps.setBytes(9, image3);
					}
				}
				count=ps.executeUpdate();
			}else {
				count=ps.executeUpdate();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return count;

	}

	@Override
	public int update(Product prouct, byte[] image, byte[] image2, byte[] image3) {
		// TODO Auto-generated method stub

		int count = 0;
		String sql = "UPDATE PRODUCT SET PRODUCT_NAME= ?,COLOR=?,PRICE=?,DITAL=?,CATEGORY_ID=?,PRODUCT_STATUS=?,MODIFY_DATE=?";
		if (image != null) {
//			
			sql += ",PRODUCT_IMG1=?";
			if (image2 != null) {
				sql += ",PRODUCT_IMG2=?";
				if (image3 != null) {
					sql += ",PRODUCT_IMG3=?";
				}
			}

		}
		sql += "WHERE PRODUCT_ID=?";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {
			ps.setString(1, prouct.getProduct_Name());
			ps.setString(2, prouct.getProduct_Color());
			ps.setInt(3, prouct.getProduct_Price());
			ps.setString(4, prouct.getProduct_Ditail());
			ps.setInt(5, prouct.getProduct_Category_ID());
			ps.setInt(6, prouct.getProduct_Status());
			ps.setTimestamp(7, prouct.getProduct_MODIFY_DATE());

			if (image != null) {
				ps.setBytes(8, image);
				ps.setInt(9, prouct.getProduct_ID());
				if (image2 != null) {
					ps.setBytes(9, image2);
					ps.setInt(10, prouct.getProduct_ID());
					if (image3 != null) {
						ps.setBytes(10, image3);
						ps.setInt(11, prouct.getProduct_ID());
					}
				}

			} else {
				ps.setInt(8, prouct.getProduct_ID());
			}
			count = ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return count;
	}

	

	
	
}
