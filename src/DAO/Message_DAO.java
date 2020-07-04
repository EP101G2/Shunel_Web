package DAO;

import java.util.List;

import Bean.Message;

public interface Message_DAO  {

	int insert(Message message);
	int update(Message message);
	int delete(int Message_ID);
	Message findById(int Message_ID);
	List<Message> getAll();
}
