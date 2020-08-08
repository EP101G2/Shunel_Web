package DAO_Interface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import Bean.Promotion;
import DAO.Promotion_DAO;
import Servlet_Shunel.ServiceLocator;

public class Promotion_DAO_Interface implements Promotion_DAO {
	
	DataSource dataSource;

	public Promotion_DAO_Interface() {
		dataSource = ServiceLocator.getInstance().getDataSource();
	}
	
	@Override
	public int insert(Promotion promotion) {
		
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Promotion promotion) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int Promotion_ID) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Promotion findById(int Promotion_ID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Promotion> getPromotionAll() {
		String sql = "SELECT * from PROMOTION join PRODUCT on  PROMOTION.PRODUCT_ID = PRODUCT.PRODUCT_ID  where now() between DATE_START and DATE_END and PRODUCT_STATUS = 2;";
		List<Promotion> promotionList = new ArrayList<Promotion>();
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);){
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println("--------");
				int PROMOTION_ID = rs.getInt("PROMOTION_ID");
				String PRODUCT_NAME  = rs.getString("PRODUCT_NAME");
				int PRODUCT_ID = rs.getInt("PRODUCT_ID");
				int  PROMOTION_PRICE = rs.getInt("PROMOTION_PRICE");
				int Product_Price = rs.getInt("PRICE");
				Timestamp DATE_START = rs.getTimestamp("DATE_START");
				Timestamp DATE_END = rs.getTimestamp("DATE_END");
				Promotion promotion = new Promotion(PROMOTION_ID, PRODUCT_NAME, PRODUCT_ID, PROMOTION_PRICE,Product_Price, DATE_START, DATE_END);
				promotionList.add(promotion);				
			}
			return promotionList;
				
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		return promotionList;	
	}

	@Override
	public List<Promotion> getPromotionForNotice() {
		System.out.println("-----3-----");
		
		String sql = "SELECT * FROM Shunel.PROMOTION limit 6 ;";
		List<Promotion> promotionList = new ArrayList<Promotion>();
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);){
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println("--------");
				int PRODUCT_ID = rs.getInt("PRODUCT_ID");
				String PROMOTION_NAME  = rs.getString("PROMOTION_NAME");
				int PROMOTION_ID = rs.getInt("PROMOTION_ID");
				int  PROMOTION_PRICE = rs.getInt("PROMOTION_PRICE");
//				int Product_Price = rs.getInt("PRICE");
				Timestamp DATE_START = rs.getTimestamp("DATE_START");
				Timestamp DATE_END = rs.getTimestamp("DATE_END");
				Promotion promotion = new Promotion(PROMOTION_ID, PROMOTION_NAME,PRODUCT_ID , PROMOTION_PRICE, DATE_START, DATE_END);
				promotionList.add(promotion);				
			}
			return promotionList;
				
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		return promotionList;	
	}

	@Override
	public byte[] getImage(int id) {
		
		String sql = "select pd.PRODUCT_ID,PRODUCT_IMG1\n" + 
				"		from Shunel.PRODUCT  pd\n" + 
				"		join Shunel.PROMOTION  pm on pd.PRODUCT_ID = pm.PRODUCT_ID\n" + 
				"		order by pm.PRODUCT_ID= ? ;	";

//		String sql = "SELECT PRODUCT_IMG1 FROM PRODUCT WHERE PRODUCT_ID = ?;";
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
}
