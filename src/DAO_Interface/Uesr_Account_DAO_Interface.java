package DAO_Interface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	public User_Account findById(String user_Account_ID) {
		String sql = "SELECT * FROM USER_ACCOUNT WHERE ACCOUNT_ID = ?;";
		User_Account user_Account= null;
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {
			ps.setString(1, user_Account_ID);
			/*
			 * 當Statement關閉，ResultSet也會自動關閉， 可以不需要將ResultSet宣告置入try with
			 * resources小括號內，參看ResultSet說明
			 */
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				
				String account_ID = rs.getString("ACCOUNT_ID");
				String account_Phone = rs.getString("PHONE");
				String account_Password = rs.getString("PASSWORD");
				String account_Address = rs.getString("ADDRESS");
				int account_Total_Price = rs.getInt("TOTAL_PRICE");
				int account_Notice_Status = rs.getInt("NOTICE_STATUS");
				int account_Status = rs.getInt("ACCOUNT_STATUS");
//				Timestamp account_Modify_Date=rs.getTimestamp("MODIFY_DATE");
				user_Account = new User_Account(account_ID,account_Phone,  account_Password, account_Address,
						 account_Total_Price,  account_Notice_Status,  account_Status);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user_Account;
	}


	@Override
	public List<User_Account> getAll() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM USER_ACCOUNT;";

		List<User_Account> accounts = new ArrayList<>();
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String id = rs.getString("ACCOUNT_ID");
				String phone = rs.getString("PHONE");
				String password = rs.getString("PASSWORD");
				String address = rs.getString("ADDRESS");
				int price = rs.getInt("TOTAL_PRICE");
				int notoce_status = rs.getInt("NOTICE_STATUS");
				int account_status = rs.getInt("ACCOUNT_STATUS");
				Timestamp time = rs.getTimestamp("MODIFY_DATE");
				User_Account account = new User_Account(id, phone, password, address, price, notoce_status, account_status, time);
				accounts.add(account);
			}
			return accounts;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return accounts;
	}

	@Override
	public List<User_Account> login(User_Account user_Account) {
		// TODO Auto-generated method stub
		String sql = "select * from USER_ACCOUNT where ACCOUNT_ID = ? and PASSWORD = ? ;";
		List<User_Account> accountList = null;

		try (Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql);) {

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				
				
//				preparedStatement.setString(1, user_Account.getAccount_Phone());
//				preparedStatement.setString(2, user_Account.getAccount_Password());
				
				
                String id = rs.getString("ACCOUNT_ID");
                String password = rs.getString("PASSWORD");
				
				//				int id = rs.getInt("ACCOUNT_ID");
//				String phone = rs.getString("PHONE");
//				String password = rs.getString("PASSWORD");
//				String addres = rs.getString("ADDRESS");
//				int price = rs.getInt("TOTAL_PRICE");
//				int notoce_status = rs.getInt("NOTICE_STATUS");
//				int account_status = rs.getInt("ACCOUNT_STATUS");
//				Timestamp time = rs.getTimestamp("MODIFY_DATE");
//				User_Account account = new User_Account(id, phone, password, addres, price, notoce_status,
//						account_status, time);
				User_Account account = new User_Account(id, password);
//				
				accountList.add(account);
			}
			return accountList;

		} catch (Exception e) {
			// TODO: handle exception
		}
		return accountList;
	}

}
