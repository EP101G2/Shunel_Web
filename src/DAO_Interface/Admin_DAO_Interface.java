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

public class Admin_DAO_Interface  implements Admin_DAO{
	DataSource dataSource;

	public Admin_DAO_Interface() {
		dataSource = ServiceLocator.getInstance().getDataSource();
		
		
	}

	@Override
	public int insert(Admin admin) {
		// TODO Auto-generated method stub
		
		int count = 0;
		
		String sql = "INSERT INTO ADMIN" + "(ADMIN_ID,ADMIN_NAME,ADMIN_USERNAME,ADMIN_PASSWORD,ADMIN_POSITION) " + " VALUES(?,?,?,?,?);";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {
			ps.setInt(1, admin.getAdmin_ID());
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
		sql = "UPDATE ADMIN SET ADMIN_NAME = ?, ADMIN_USERNAME = ?, ADMIN_PASSWORD = ?, ADMIN_POSITION = ?";
		
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {
			ps.setString(1, admin.getAdmin_Name());
			ps.setString(2, admin.getAdmin_User_Name());
			ps.setString(3, admin.getAdmin_User_Password());
			ps.setString(4, admin.getAdmin_User_Position());
		
			
			
			count = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
}

	@Override
	public int delete(int admin_id) {
		// TODO Auto-generated method stub
		return 0;
			
	}

	@Override
	public Admin findById(int admin_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Admin> getAll() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM ADMIN;";
		List<Admin> admins = new ArrayList<>();   //ＣＵＺ recycle view 需要用ＬＩＳＴ，所以把物件丟到ＬＩＳＴ在送回去ＡＮＤＲＯＩＤ
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
		
				int  admin_ID= rs.getInt("ADMIN_ID");
				String admin_Name=rs.getString("ADMIN_NAME");
				String admin_User_Name = rs.getString("ADMIN_USERNAME");
				String admin_User_Password = rs.getString("ADMIN_PASSWORD");
				String admin_User_Position = rs.getString("ADMIN_POSITION");
				Admin admin = new Admin(admin_ID, admin_Name, admin_User_Name, admin_User_Password, admin_User_Position);
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