package DAO;

import java.util.List;

import Bean.Chat;

public interface chat_DAO {

	int insert(Chat chat, byte[] chat_image);

	int update(Chat chat, byte[] chat_image );

	int delete(int Chat_id);

	Chat findById(int chat_id);

	List<Chat> getAll();

	byte[] getImage(int Chat_id);

}