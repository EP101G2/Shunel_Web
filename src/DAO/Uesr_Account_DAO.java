package DAO;

import java.util.List;

import Bean.Prouct;
import Bean.User_Account;

public interface Uesr_Account_DAO {

	
	void insert(User_Account user_Account);
	void update(User_Account user_Account);
	void delete(int user_Account_ID);
	User_Account findById(int user_Account_ID);
	List<User_Account> getAll();
	
}
