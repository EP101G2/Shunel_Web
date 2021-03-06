package DAO;

import java.util.List;

import Bean.Chat_Record;
import Bean.Chat_Room;
import Chat.ChatMessage;

public interface Chat_Record_DAO {

	int createRoom(String chef_no, String user_no, String user_name, String chef_name);

	long insert(Chat_Record cRecord, byte[] image);

	List<ChatMessage> getAll(int chatRoom);

	int selectId();

	byte[] getImage(int id);
	
	int update(String sender, int chatRoom);
	
	int selectChatRoom(String chef_no, String user_no);
	
	List<Chat_Room> selectChatRoomList(String chef_no, String user_no);
	
	

}
