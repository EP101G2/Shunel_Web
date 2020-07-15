package DAO_Interface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import Bean.Chat;
//import DAO.Chat_DAO;
import Servlet_Shunel.ServiceLocator;

public class Chat_DAO_Interface {

	DataSource dataSource;

	 public Chat_DAO_Interface(){
		dataSource = ServiceLocator.getInstance().getDataSource();
		
	}
	
	
	
//	@Override
//	public int insert(Chat chat) {
//		// TODO Auto-generated method stub
////		INSERT INTO CHAT (PRODUCT_ID, ACCOUNT_ID, BUY_CONTENT, SELL_CONTENT) VALUES ('?', '?', '?', '?');
//
//		int count =0;
//		int id = 0;
//		String sql = "INSERT INTO CHAT (PRODUCT_ID, ACCOUNT_ID, BUY_CONTENT, SELL_CONTENT) VALUES (?, ?, ?, ?);";
//		try (Connection connection = dataSource.getConnection();
//				PreparedStatement ps = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)
//				){
//			
//			
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		
//		
//		
//		return 0;
//	}
//
//	@Override
//	public int delete(int Chat_id) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public Chat findById(int chat_id) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<Chat> getAll() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public byte[] getImage(int Chat_id) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	

	

}
