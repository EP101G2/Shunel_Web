package DAO;

import java.util.List;

import Bean.Chat_Record;
import Bean.Chat_Room;
import Bean.Order_Detail;
import Bean.Order_Main;
import Chat.ChatMessage;

public interface Chat_DAO {

	int createRoom(String chef_no, String user_no);

	int insert(ChatMessage chatMessage);

	//不需
	int insert(Chat_Room cr);

	
//	int insert(int chatRoom,String msg,String receiver,String sender,String Type);
	
	int insert(int chatRoom,String msg,String receiver,String sender,String Type,byte[] image);
	
	int selectId();

	byte[] getImage(int id);
	
	int update(String sender, int chatRoom);

	int selectChatRoom(String chef_no, String user_no);
	
	List<Chat_Room> selectChatRoomList(String chef_no, String user_no);
	
	List<ChatMessage> getAll(int chatRoom);
}
