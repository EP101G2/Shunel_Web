package DAO_Interface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import Bean.Notice;
import Bean.Order_Detail;
import Bean.Order_Main;
import Bean.Product;
import DAO.Product_DAO;
import Servlet_Shunel.ServiceLocator;

public class Product_DAO_Interface implements Product_DAO {

	DataSource dataSource;

	public Product_DAO_Interface() {
		dataSource = ServiceLocator.getInstance().getDataSource();

	}

	@Override
	public int delete(int id) {
		return id;
		// TODO Auto-generated method stub

	}

	@Override
	public Product findById(int prouct_id) {
		String sql = " SELECT PRODUCT_NAME, COLOR, DITAL FROM Shunel.PRODUCT WHERE PRODUCT_ID = ?;";
		Product product = null;
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {
			ps.setInt(1, prouct_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println("--------");
				String PRODUCT_NAME = rs.getString("PRODUCT_NAME");
				String COLOR = rs.getString("COLOR");
				String DITAL = rs.getString("DITAL");
				product = new Product(PRODUCT_NAME, COLOR, DITAL);
			}
			return product;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return product;
	}

	@Override
	public List<Product> getLikeProduct(String user_id) {
		String sql = "Select * From Shunel.LIKE join Shunel.PRODUCT on Shunel.LIKE.PRODUCT_ID  = Shunel.PRODUCT.PRODUCT_ID Where Shunel.LIKE.ACCOUNT_ID = ?";

		List<Product> prouctList = new ArrayList<Product>();
		Product product = null;
		System.out.println("333");
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {
			ps.setString(1, user_id);
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

				product = new Product(id, prouct_Name, prouct_Color, prouct_Price, prouct_Dital, prouct_Category_ID,
						prouct_Status);
				prouctList.add(product);
			}
			return prouctList;
		} catch (Exception e) {

			e.printStackTrace();
			// TODO: handle exception
		}

		return prouctList;
	}

	// 拿只有上架的商品
	@Override
	public List<Product> getSaleProduct() {
		// TODO Auto-generated method stub

		// String sql = "Select * From CATEGORY join PRODUCT on PRODUCT.CATEGORY_ID =
		// CATEGORY.CATEGORY_ID Where CATEGORY.CATEGORY_ID = 3;";
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

	// 拿所有商品照片 包含下架商品
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
	public int insert(Product prouct, byte[] image, byte[] image2, byte[] image3) {
		int count = 0;
		String sql = "INSERT INTO PRODUCT";

		if (image == null && image2 == null && image3 == null) {        //沒有上傳任何一張照片
			sql += "(PRODUCT_NAME,COLOR,PRICE,DITAL,CATEGORY_ID,PRODUCT_STATUS)";
			sql += "VALUES(?,?,?,?,?,?);";
		} else if (image != null && image2 == null && image3 == null) {
			sql += "(PRODUCT_NAME,COLOR,PRICE,DITAL,CATEGORY_ID,PRODUCT_STATUS,PRODUCT_IMG1)";
			sql += "VALUES(?,?,?,?,?,?,?);";
		} else if (image != null && image2 != null && image3 == null) {
			sql += "(PRODUCT_NAME,COLOR,PRICE,DITAL,CATEGORY_ID,PRODUCT_STATUS,PRODUCT_IMG1,PRODUCT_IMG2)";
			sql += "VALUES(?,?,?,?,?,?,?,?);";
		} else if (image != null && image2 != null && image3 != null) {
			sql += "(PRODUCT_NAME,COLOR,PRICE,DITAL,CATEGORY_ID,PRODUCT_STATUS,PRODUCT_IMG1,PRODUCT_IMG2,PRODUCT_IMG3)";
			sql += "VALUES(?,?,?,?,?,?,?,?,?);";
		} else if (image != null && image2 != null && image3 != null) {
			sql += "(PRODUCT_NAME,COLOR,PRICE,DITAL,CATEGORY_ID,PRODUCT_STATUS)";
			sql += "VALUES(?,?,?,?,?,?);";
		}

		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {
			ps.setString(1, prouct.getProduct_Name());
			ps.setString(2, prouct.getProduct_Color());
			ps.setInt(3, prouct.getProduct_Price());
			ps.setString(4, prouct.getProduct_Ditail());
			ps.setInt(5, prouct.getProduct_Category_ID());
			ps.setInt(6, prouct.getProduct_Status());
			if (image != null) {
				ps.setBytes(7, image);
				System.out.println("ps指令"+ps);
				if (image2 != null) {
					ps.setBytes(8, image2);
					if (image3 != null) {
						ps.setBytes(9, image3);
					}
				}
				count = ps.executeUpdate();
			} else {
				count = ps.executeUpdate();
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
		String sql = "UPDATE PRODUCT SET PRODUCT_NAME= ?,COLOR=?,PRICE=?,DITAL=?,CATEGORY_ID=?,PRODUCT_STATUS=?";  //6個問號
		if (image != null) {
			sql += ",PRODUCT_IMG1=?";
			if (image2 != null) {
				sql += ",PRODUCT_IMG2=?";
				if (image3 != null) {
					sql += ",PRODUCT_IMG3=?";
				}
			}

		}
		sql += " WHERE PRODUCT_ID= ? ;";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {
			ps.setString(1, prouct.getProduct_Name());
			ps.setString(2, prouct.getProduct_Color());
			ps.setInt(3, prouct.getProduct_Price());
			ps.setString(4, prouct.getProduct_Ditail());
			ps.setInt(5, prouct.getProduct_Category_ID());
			ps.setInt(6, prouct.getProduct_Status());
			
			
			if (image != null) {
				ps.setBytes(7, image);
				ps.setInt(8, prouct.getProduct_ID());
				if (image2 != null) {
					ps.setBytes(8, image2);
					ps.setInt(9, prouct.getProduct_ID());
					if (image3 != null) {
						ps.setBytes(9, image3);
						ps.setInt(10, prouct.getProduct_ID());
					}
				}

			} else {
				ps.setInt(7, prouct.getProduct_ID());
				
				System.out.println("sql========"+ps );
			}
			count = ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public List<Product> getCategoryProduct(int category_id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM PRODUCT WHERE CATEGORY_ID = ? and PRODUCT_STATUS = 1";

		List<Product> prouctList = new ArrayList<Product>();
		Product product = null;
		System.out.println("333");
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {
			ps.setInt(1, category_id);
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

				product = new Product(id, prouct_Name, prouct_Color, prouct_Price, prouct_Dital, prouct_Category_ID,
						prouct_Status);
				prouctList.add(product);
			}
			return prouctList;
		} catch (Exception e) {

			e.printStackTrace();
			// TODO: handle exception
		}

		return prouctList;
	}

	@Override
	public List<Product> getTOP5Product() {
		// TODO Auto-generated method stub
		String sql = "select  PRODUCT.PRODUCT_ID, PRODUCT_NAME, PRODUCT.COLOR , PRODUCT.PRICE, PRODUCT.DITAL , PRODUCT.CATEGORY_ID , PRODUCT.PRODUCT_STATUS , sum(AMOUNT) as total_qty  from ORDER_DETAIL  join PRODUCT on  ORDER_DETAIL.PRODUCT_ID = PRODUCT.PRODUCT_ID and PRODUCT_STATUS = 1  group by PRODUCT_ID    ORDER BY total_qty DESC  		limit 5  ";
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

}
