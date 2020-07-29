package DAO_Interface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import Bean.Chat_Record;
import Bean.Chat_Room;
import DAO.Chat_DAO;
import Servlet_Shunel.ServiceLocator;

public class Chat_DAO_InterFace implements Chat_DAO {

	private DataSource dataSource;

	private static final String GET_ALL = "SELECT * from CHAT_RECORD WHERE CHAT_NO = ?";
	private static final String INSERT_STMT = "INSERT INTO CHAT_RECORD (CHAT_NO, CHAT_MSG, FLAG,CHAT_RECEIVER,CHAT_SENDER,CHAT_IMAGE) VALUES(?, ?, ?, ?, ?, ?) ";
	private static final String SELECT_LAST_ID = "SELECT LAST_INSERT_ID()";
	private static final String GET_IMAGE_STMT = "SELECT CHAT_IMAGE FROM CHAT_RECORD WHERE ID = ?;";
	private static final String UPDATE_FLAG = "UPDATE CHAT_RECORD SET FLAG = 0 WHERE CHAT_NO = ? AND CHAT_RECEIVER = ?";
	private static final String SELECT_ROOM = "SELECT CHAT_NO from CHAT_ROOM Where USER_NO = ? AND CHEF_NO = ?";
	private static final String INSERT_ROOM = "INSERT INTO CHAT_ROOM (USER_NO,CHEF_NO,USER_NAME,CHEF_NAME) VALUES (? ,? ,? ,?)";
	private static final String SELECT_ROOM_LIST = "SELECT * from CHAT_ROOM where USER_NO = ? OR CHEF_no = ?";

	public Chat_DAO_InterFace() {
		dataSource = ServiceLocator.getInstance().getDataSource();
	}

	@Override
	public int createRoom(String chef_no, String user_no, String user_name, String chef_name) {
		// TODO Auto-generated method stub
		int updateCount = 0;
		int gid = 0;

		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(INSERT_ROOM, Statement.RETURN_GENERATED_KEYS)) {

			ps.setString(1, user_no);
			ps.setString(2, chef_no);
			ps.setString(3, user_name);
			ps.setString(4, chef_name);

			updateCount = ps.executeUpdate();

			if (updateCount != 0) {
				ResultSet generatedKeys = ps.getGeneratedKeys();
				if (generatedKeys.next()) {
					gid = generatedKeys.getInt(1);// here is your generated Id
				}
			}
		} catch (SQLException se) {
			se.printStackTrace(System.err);
		}
		return gid;
	}

	@Override
	public long insert(Chat_Record cRecord, byte[] image) {
		// TODO Auto-generated method stub
		int updateCount = 0;
		  long gid = 0;

		  try (Connection connection = dataSource.getConnection();
		    PreparedStatement ps = connection.prepareStatement(INSERT_STMT, Statement.RETURN_GENERATED_KEYS)) {

		   
		   ps.setInt(1, cRecord.getCHAT_NO());
		   ps.setString(2, cRecord.getCHAT_MSG());
		   ps.setInt(3, cRecord.getFLAG());
		   ps.setString(4, cRecord.getCHAT_RECRIVER());
		   ps.setString(5, cRecord.getCHAT_SENDER());
		   if (image != null) {
		    ps.setBytes(6, image);
		   } else {
		    ps.setBytes(6, null);
		   }

		   updateCount = ps.executeUpdate();

		   if (updateCount != 0) {
		    ResultSet generatedKeys = ps.getGeneratedKeys();
		    if (generatedKeys.next()) {
		     gid = generatedKeys.getLong(1);// here is your generated Id 
		    }
		   }
		  } catch (SQLException se) {
		   se.printStackTrace(System.err);
		  }
		  return gid;
	}

	@Override
	public int insert(Chat_Room cr) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int selectId() {
		// TODO Auto-generated method stub
		int result = 0;
		  try (Connection connection = dataSource.getConnection();
		    PreparedStatement ps = connection.prepareStatement(SELECT_LAST_ID);) {

		   result = ps.executeUpdate();

		  } catch (SQLException se) {
		   se.printStackTrace(System.err);
		  }

		  return result;
	}

	@Override
	public byte[] getImage(int id) {
		// TODO Auto-generated method stub
		byte[] image = null;
		  ResultSet rs = null;

		  try (Connection connection = dataSource.getConnection();
		    PreparedStatement ps = connection.prepareStatement(GET_IMAGE_STMT);) {

		   ps.setInt(1, id);
		   rs = ps.executeQuery();

		   while (rs.next()) {
		    image = rs.getBytes("CHAT_IMAGE");
		   }
		  } catch (SQLException se) {
		   se.printStackTrace(System.err);
		  }
		  return image;
	}

	@Override
	public int update(String sender, int chatRoom) {
		// TODO Auto-generated method stub
		int updateCount = 0;
		  try (Connection connection = dataSource.getConnection();
		    PreparedStatement ps = connection.prepareStatement(UPDATE_FLAG);) {

		   ps.setInt(1, chatRoom);
		   ps.setString(2, sender);

		   updateCount = ps.executeUpdate();

		  } catch (SQLException se) {
		   se.printStackTrace(System.err);
		  }

		  return 0;
	}

	@Override
	public int selectChatRoom(String chef_no, String user_no) {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		  Chat_Record chat = null;
		  
		  try (Connection connection = dataSource.getConnection();
		    PreparedStatement ps = connection.prepareStatement(SELECT_ROOM)) {
		   ps.setString(1, user_no);
		   ps.setString(2, chef_no);
		   
		   rs = ps.executeQuery();

		   while (rs.next()) {
		    chat = new Chat_Record();
		    chat.setCHAT_NO(rs.getInt("CHAT_NO"));
		   }

		  } catch (SQLException se) {
		   se.printStackTrace(System.err);
		  }
		  return chat == null ? 0 : chat.getCHAT_NO();
	}

	@Override
	public List<Chat_Room> selectChatRoomList(String chef_no, String user_no) {
		// TODO Auto-generated method stub
		List<Chat_Room> list = new ArrayList<>();
		  ResultSet rs = null;
		  Chat_Room room = null;
		  try (Connection connection = dataSource.getConnection();
		    PreparedStatement ps = connection.prepareStatement(SELECT_ROOM_LIST)) {
		   ps.setString(1, user_no);
		   ps.setString(2, chef_no);
		   rs = ps.executeQuery();

		   while (rs.next()) {
//		    room = new Chat_Room();
//		    room.setChat_ID(rs.getInt("CHAT_NO"));
//		    room.setAccount_ID(rs.getString("USER_NO"));
//		    room.set(rs.getString("CHEF_NO"));
//		    room.setUser_name(rs.getString("USER_NAME"));
//		    room.setChef_name(rs.getString("CHEF_NAME"));
//		    list.add(room);
		   }

		  } catch (SQLException se) {
		   se.printStackTrace(System.err);
		  }
		  return list;
	}

	@Override
	public List<Chat_Record> getAll(int chatRoom) {
		// TODO Auto-generated method stub
		List<Chat_Record> list = new ArrayList<>();
		  ResultSet rs = null;
		  Chat_Record chat = null;
		  try (Connection connection = dataSource.getConnection();
		    PreparedStatement ps = connection.prepareStatement(GET_ALL)) {
		   ps.setInt(1, chatRoom);
		   
		   rs = ps.executeQuery();
		   

		   while (rs.next()) {
		    chat = new Chat_Record();
		    chat.setID(rs.getInt("ID"));
		    chat.setCHAT_NO(rs.getInt("CHAT_NO"));
		    chat.setCHAT_MSG(rs.getString("CHAT_MSG"));
		    chat.setCHAT_SENDER(rs.getString("CHAT_SENDER"));
		    chat.setCHAT_RECRIVER(rs.getString("CHAT_RECEIVER"));
		    chat.setFLAG(rs.getInt("FLAG"));
		    chat.setCHAT_DATE(rs.getTimestamp("CHAT_DATE"));
		    chat.setCHAT_IMAGE(rs.getBytes("CHAT_IMAGE"));
		    list.add(chat);
		   }

		  } catch (SQLException se) {
		   se.printStackTrace(System.err);
		  }
		  return list;
	}

}
