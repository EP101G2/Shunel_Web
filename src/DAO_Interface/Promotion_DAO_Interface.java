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

		int count = 0;

		String sql = "INSERT INTO `Shunel`.`PROMOTION` (`PROMOTION_ID`,`PRODUCT_ID`, `PROMOTION_PRICE`, `DATE_START`, `DATE_END`) VALUES (? ,?, ?, ?, ?) " + 
				"ON DUPLICATE KEY UPDATE  " + 
				"`PRODUCT_ID` = values (PRODUCT_ID)," + 
				"`PROMOTION_PRICE` = values (PROMOTION_PRICE)," + 
				"`DATE_START` = values (DATE_START)," + 
				" `DATE_END` = values (DATE_END)" + 
				";";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {
			ps.setInt(1, promotion.getPromotion_ID());
			ps.setInt(2, promotion.getProduct_ID());
			ps.setInt(3, promotion.getPromotion_Price());
			ps.setTimestamp(4, promotion.getDate_Start());
			ps.setTimestamp(5, promotion.getDate_End());
			
			System.out.println(ps.toString());
			count = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;

		
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
				PreparedStatement ps = connection.prepareStatement(sql);) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println("--------");
				int promotion_ID = rs.getInt("PROMOTION_ID");
				String product_Name = rs.getString("PRODUCT_NAME");
				int product_ID = rs.getInt("PRODUCT_ID");
				int promotion_Price = rs.getInt("PROMOTION_PRICE");
				int product_Price = rs.getInt("PRICE");
				Timestamp date_Start = rs.getTimestamp("DATE_START");
				Timestamp date_End = rs.getTimestamp("DATE_END");
				String color = rs.getString("COLOR");
				String dital = rs.getString("DITAL");
				Promotion promotion = new Promotion(promotion_ID, product_Name, product_ID, promotion_Price, product_Price, color, dital, date_Start, date_End);
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

		String sql = "SELECT * from PROMOTION join PRODUCT on  PROMOTION.PRODUCT_ID = PRODUCT.PRODUCT_ID  where now() between DATE_START and DATE_END and PRODUCT_STATUS = 2 limit 6 ;";
		List<Promotion> promotionList = new ArrayList<Promotion>();
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println("--------");
				int PROMOTION_ID = rs.getInt("PRODUCT_ID");
				int PRODUCT_ID = rs.getInt("PRODUCT_ID");
				String PRODUCT_NAME = rs.getString("PRODUCT_NAME");
				int PROMOTION_PRICE = rs.getInt("PROMOTION_PRICE");
//				int Product_Price = rs.getInt("PRICE");
				Timestamp DATE_START = rs.getTimestamp("DATE_START");
				Timestamp DATE_END = rs.getTimestamp("DATE_END");
				Promotion promotion = new Promotion(PROMOTION_ID, PRODUCT_NAME, PRODUCT_ID, PROMOTION_PRICE, DATE_START,
						DATE_END);
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

		String sql = "select pd.PRODUCT_ID,PRODUCT_IMG1\n" + "		from Shunel.PRODUCT  pd\n"
				+ "		join Shunel.PROMOTION  pm on pd.PRODUCT_ID = pm.PRODUCT_ID\n"
				+ "		order by pm.PRODUCT_ID= ? ;	";

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
