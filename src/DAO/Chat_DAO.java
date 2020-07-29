package DAO;

import java.util.List;

import Bean.Chat_Record;
import Bean.Chat_Room;
import Bean.Order_Detail;
import Bean.Order_Main;

public interface Chat_DAO {

	int createRoom(String chef_no, String user_no, String user_name, String chef_name);

	long insert(Chat_Record cRecord, byte[] image);

	//不需
	int insert(Chat_Room cr);

	int selectId();

	byte[] getImage(int id);
	
	int update(String sender, int chatRoom);

	int selectChatRoom(String chef_no, String user_no);
	
	List<Chat_Room> selectChatRoomList(String chef_no, String user_no);
	
	List<Chat_Record> getAll(int chatRoom);
}
