package DAO_Interface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;



//import org.apache.catalina.valves.rewrite.InternalRewriteMap.Escape;

import Bean.User_Account;
import DAO.Uesr_Account_DAO;
import Servlet_Shunel.ServiceLocator;



public class Uesr_Account_DAO_Interface implements Uesr_Account_DAO {

	DataSource dataSource;       //管理連線（對應context.xml）

	public Uesr_Account_DAO_Interface() {
		dataSource = ServiceLocator.getInstance().getDataSource();
	}

	@Override
	public int insert(User_Account user_Account) {
		// TODO Auto-generated method stub
		int count = 0;
		
		String sql = "INSERT INTO User_Account" + "(USER_NAME,ACCOUNT_ID,PHONE,PASSWORD,ADDRESS," + 
				"TOTAL_PRICE, NOTICE_STATUS,ACCOUNT_STATUS,TOKEN) " + " VALUES(?,?,?,?,?,?,?,?,?);";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {
			ps.setString(1, user_Account.getAccount_User_Name());
			ps.setString(2, user_Account.getAccount_ID());
			ps.setString(3, user_Account.getAccount_Phone());
			ps.setString(4, user_Account.getAccount_Password());
			ps.setString(5, user_Account.getAccount_Address());
			ps.setInt(6, 0);
			ps.setInt(7, 1);
			ps.setInt(8, 1);
			ps.setString(9, user_Account.getTOKEN());
			System.out.println(ps.toString());
			count = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	

	@Override
	public int update(User_Account user_Account, byte[] image) {
		// TODO Auto-generated method stub
			int count = 0;
			String sql = "";
			// image為null就不更新image欄位內容
			if (image != null) {
				sql = "UPDATE User_Account SET USER_NAME = ?, ADDRESS = ?, PHONE = ?, "
						+ "PHOTO = ? WHERE ACCOUNT_ID = ?;";
			} else {
				sql = "UPDATE User_Account SET USER_NAME = ?, ADDRESS = ?,PHONE = ? WHERE ACCOUNT_ID = ?;";
			}
			try (Connection connection = dataSource.getConnection();
					PreparedStatement ps = connection.prepareStatement(sql);) {
				ps.setString(1, user_Account.getAccount_User_Name());
				ps.setString(2, user_Account.getAccount_Address());
				ps.setString(3, user_Account.getAccount_Phone());
			
				if (image != null) {
					ps.setBytes(4, image);
					ps.setString(5, user_Account.getAccount_ID());
				} else {
					ps.setString(4, user_Account.getAccount_ID());
				}
				count = ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return count;
	}

	@Override
	public int update(User_Account user_Account) {
		// TODO Auto-generated method stub
		int count = 0;
		String sql = "";
		sql = "UPDATE User_Account SET PASSWORD = ? WHERE ACCOUNT_ID = ?;";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {
			ps.setString(1, user_Account.getAccount_Password());
			ps.setString(2, user_Account.getAccount_ID());
			count = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
}
	public int updateToken (String account_id,String token ) {
		int count = 0;
		String sql = "";
		sql = "UPDATE User_Account SET TOKEN = ? WHERE ACCOUNT_ID = ?;";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {
			ps.setString(1, token);
			ps.setString(2, account_id);
			count = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.print("token更新:"+count);
		return count;
		
	}
	
	
	
	@Override
	public int update(String phone, String password) {
		// TODO Auto-generated method stub
		int count = 0;
		String sql = "";
		System.out.println("11111111111111111111111");
		sql = "UPDATE User_Account SET PASSWORD = ? WHERE PHONE = ?;";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {
			ps.setString(1, password);
			ps.setString(2, phone);
			count = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("22222222222222222222222222");
		return count;
}
	@Override
	public User_Account login(String user_Account_ID) {
		String sql = "SELECT * FROM USER_ACCOUNT WHERE ACCOUNT_ID = ?;";
		User_Account user_Account= null;
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {  //準備ＳＱＬ指令
			ps.setString(1, user_Account_ID);
			/*
			 * 當Statement關閉，ResultSet也會自動關閉， 可以不需要將ResultSet宣告置入try with
			 * resources小括號內，參看ResultSet說明
			 */
			ResultSet rs = ps.executeQuery();   //做查詢
			if (rs.next()) {
				String account_User_Name=rs.getString("USER_NAME");
				String account_ID = rs.getString("ACCOUNT_ID");   //前面自己取，後面對應資料庫欄位名稱
				String account_Phone = rs.getString("PHONE");
				String account_Password = rs.getString("PASSWORD");
				String account_Address = rs.getString("ADDRESS");
				int account_Total_Price = rs.getInt("TOTAL_PRICE");
				int account_Notice_Status = rs.getInt("NOTICE_STATUS");
				int account_Status = rs.getInt("ACCOUNT_STATUS");
				String token = rs.getString("TOKEN");
//				Timestamp account_Modify_Date=rs.getTimestamp("MODIFY_DATE");
				user_Account = new User_Account(account_User_Name,account_ID,account_Phone,  account_Password, account_Address,
						 account_Total_Price,  account_Notice_Status,  account_Status,token);
			}   //user_Account是我自己創建的物件（空容器），裡面塞我ＲＳ出來的東西（查的資料）
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("========="+user_Account);
		return user_Account;
	}


	@Override
	public List<User_Account> getAll() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM Shunel.USER_ACCOUNT;";

		List<User_Account> accounts = new ArrayList<User_Account>();
		System.out.println("333");
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String username=rs.getString("USER_NAME");
				String id = rs.getString("ACCOUNT_ID");
				String phone = rs.getString("PHONE");
				String password = rs.getString("PASSWORD");
				String address = rs.getString("ADDRESS");
				int price = rs.getInt("TOTAL_PRICE");
				int notoce_status = rs.getInt("NOTICE_STATUS");
				int account_status = rs.getInt("ACCOUNT_STATUS");
				Timestamp time = rs.getTimestamp("MODIFY_DATE");
				String token=rs.getString("TOKEN");
				User_Account account = new User_Account(username,id, phone, password, address, price, notoce_status, account_status, time,token);
				accounts.add(account);
				System.out.println("123456789");
				
			}
			return accounts;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return accounts;
	}
	@Override
	public byte[] getImage(String id) {
		String sql = "SELECT PHOTO FROM USER_ACCOUNT WHERE ACCOUNT_ID = ?;";
		byte[] image = null;
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {
			ps.setString(1, id);
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
