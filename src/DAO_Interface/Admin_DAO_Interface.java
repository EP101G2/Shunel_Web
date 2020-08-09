package DAO_Interface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import Bean.Admin;
import Bean.User_Account;
import DAO.Admin_DAO;
import Servlet_Shunel.ServiceLocator;


public class Admin_DAO_Interface implements Admin_DAO {
	DataSource dataSource;
	private String admin_UserName_ID;

	public Admin_DAO_Interface() {
		dataSource = ServiceLocator.getInstance().getDataSource();

	}

	@Override
	public Admin login(String adminUserName_ID) {
		String sql = "SELECT * FROM ADMIN WHERE ADMIN_USERNAME = ?;";
		Admin admin= null;
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {  //準備ＳＱＬ指令
			ps.setString(1, adminUserName_ID);
			/*
			 * 當Statement關閉，ResultSet也會自動關閉， 可以不需要將ResultSet宣告置入try with
			 * resources小括號內，參看ResultSet說明
			 */
			ResultSet rs = ps.executeQuery();   //做查詢
			if (rs.next()) {
				String admin_Name=rs.getString("ADMIN_NAME");
				int admin_id = rs.getInt("ADMIN_ID");   //前面自己取，後面對應資料庫欄位名稱
				String admin_Password = rs.getString("ADMIN_PASSWORD");
				String admin_Position = rs.getString("ADMIN_POSITION");
				
				admin = new Admin(admin_id,admin_Name,adminUserName_ID,admin_Password,admin_Position);
			}   //user_Account是我自己創建的物件（空容器），裡面塞我ＲＳ出來的東西（查的資料）
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return admin;
	}

	@Override
	public int insert(Admin admin) {
		// TODO Auto-generated method stub

		int count = 0;

		String sql = "INSERT INTO ADMIN" + "(ADMIN_ID,ADMIN_NAME,ADMIN_USERNAME,ADMIN_PASSWORD,ADMIN_POSITION) "
				+ " VALUES(?,?,?,?,?);";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {
			ps.setInt(1, 0);
			ps.setString(2, admin.getAdmin_Name());
			ps.setString(3, admin.getAdmin_User_Name());
			ps.setString(4, admin.getAdmin_User_Password());
			ps.setString(5, admin.getAdmin_User_Position());

			count = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public int update(Admin admin) {
		// TODO Auto-generated method stub
		int count = 0;
		String sql = "";
		sql = "UPDATE ADMIN SET ADMIN_NAME = ?, ADMIN_USERNAME = ?, ADMIN_PASSWORD = ?, ADMIN_POSITION = ? where ADMIN_ID= ? ";

		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {
			ps.setString(1, admin.getAdmin_Name());
			ps.setString(2, admin.getAdmin_User_Name());
			ps.setString(3, admin.getAdmin_User_Password());
			ps.setString(4, admin.getAdmin_User_Position());
			ps.setInt(5, admin.getAdmin_ID());
			count = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public int delete(int admin_id) {
		// TODO Auto-generated method stub

		int count = 0;
		String sql = "DELETE FROM ADMIN WHERE ADMIN_ID = ?;";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {
			ps.setInt(1, admin_id);
			count = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public Admin findById(int admin_id) {
		String sql = "SELECT ADMIN_NAME, ADMIN_USERNAME, ADMIN_PASSWORD,ADMIN_POSITION FROM ADMIN WHERE ADMIN_ID = ?;";
		Admin admins = null;
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {
			ps.setInt(1, admin_id);
			/*
			 * 當Statement關閉，ResultSet也會自動關閉， 可以不需要將ResultSet宣告置入try with
			 * resources小括號內，參看ResultSet說明
			 */
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String ADMIN_NAME = rs.getString("ADMIN_NAME");
				String ADMIN_USERNAME = rs.getString("ADMIN_USERNAME");
				String ADMIN_PASSWORD = rs.getString("ADMIN_PASSWORD");
				String ADMIN_POSITION = rs.getString("ADMIN_POSITION");

				admins = new Admin(admin_id, ADMIN_NAME,ADMIN_USERNAME,ADMIN_PASSWORD,ADMIN_POSITION);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return admins;
	}

	@Override
	public List<Admin> getAll() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM ADMIN;";
		List<Admin> admins = new ArrayList<>(); // ＣＵＺ recycle view 需要用ＬＩＳＴ，所以把物件丟到ＬＩＳＴ在送回去ＡＮＤＲＯＩＤ
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				int admin_ID = rs.getInt("ADMIN_ID");
				String admin_Name = rs.getString("ADMIN_NAME");
				String admin_User_Name = rs.getString("ADMIN_USERNAME");
				String admin_User_Password = rs.getString("ADMIN_PASSWORD");
				String admin_User_Position = rs.getString("ADMIN_POSITION");
				Admin admin = new Admin(admin_ID, admin_Name, admin_User_Name, admin_User_Password,
						admin_User_Position);
				admins.add(admin);
			}
			return admins;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return admins;
	}
}