package DAO_Interface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.catalina.valves.rewrite.InternalRewriteMap.Escape;

import Bean.User_Account;
import DAO.Uesr_Account_DAO;
import Servlet_Shunel.ServiceLocator;


public class Uesr_Account_DAO_Interface implements Uesr_Account_DAO {
	
	DataSource dataSource;
	
	

	public Uesr_Account_DAO_Interface() {
		dataSource = ServiceLocator.getInstance().getDataSource();
	}

	@Override
	public int insert(User_Account user_Account) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(User_Account user_Account) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int user_Account_ID) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public User_Account findById(int user_Account_ID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User_Account> getAll() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM USER_ACCOUNT;";
		
		List<User_Account> accounts = new ArrayList<>();
		try(Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);
				) {
			ResultSet rs =ps.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("ACCOUNT_ID");
				String phone= rs.getString("PHONE");
				String password =rs.getString("PASSWORD");
				String addres=rs.getString("ADDRESS");
				int price = rs.getInt("TOTAL_PRICE");
				int notoce_status = rs.getInt("NOTICE_STATUS");
				int account_status=rs.getInt("ACCOUNT_STATUS");
				Timestamp time =rs.getTimestamp("MODIFY_DATE");
				User_Account account = new User_Account(id, phone, password, addres, price, notoce_status, account_status, time);
				accounts.add(account);
			}
			return accounts;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return accounts;
	}

}
