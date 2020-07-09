package DAO;

import java.util.List;

//import Bean.Prouct;
import Bean.User_Account;

public interface Uesr_Account_DAO {

	
	int insert(User_Account user_Account);
	int update(User_Account user_Account);
	int delete(int user_Account_ID);
	User_Account login(String user_Account_ID);
	List<User_Account> getAll();
	
	
}
