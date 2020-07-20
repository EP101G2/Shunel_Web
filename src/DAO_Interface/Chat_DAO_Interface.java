package DAO_Interface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
		return 0;
	}

	@Override
	public int insert(Chat_Room cr) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int selectId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public byte[] getImage(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(String sender, int chatRoom) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int selectChatRoom(String chef_no, String user_no) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Chat_Room> selectChatRoomList(String chef_no, String user_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Chat_Record> getAll(int chatRoom) {
		// TODO Auto-generated method stub
		return null;
	}

}
