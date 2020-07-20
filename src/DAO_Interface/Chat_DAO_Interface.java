package DAO_Interface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import Bean.Chat;
import DAO.Chat_DAO;
//import DAO.Chat_DAO;
import Servlet_Shunel.ServiceLocator;

public class Chat_DAO_Interface implements Chat_DAO{

	DataSource dataSource;

	 public Chat_DAO_Interface(){
		dataSource = ServiceLocator.getInstance().getDataSource();
		
	}

	@Override
	public int insert(Chat chat) {
		// TODO Auto-generated method stub
		
		int count = 0;
		String sql = "INSERT INTO CHAT (PRODUCT_ID, ACCOUNT_ID, BUY_CONTENT, SELL_CONTENT) VALUES (?, ?, ?, ?);";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);
				){
			ps.setInt(1, chat.getProduct_ID());
			ps.setString(2, chat.getAccount_ID());
			ps.setString(3, chat.getBuy_Content());
			ps.setString(4, chat.getSell_Content());
			count = ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return count;
	}

	@Override
	public int delete(int Chat_id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Chat findById(int chat_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Chat> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] getImage(int Chat_id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	


	

	

}
