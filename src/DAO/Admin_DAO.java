package DAO;

import java.util.List;

import Bean.Admin;
import Bean.User_Account;


public interface Admin_DAO {

	int insert(Admin admin);
    int update(Admin admin);
	int delete(int admin_id);
	Admin findById(int admin_id);
	Admin login(String adminUserName_ID);
	List<Admin> getAll();
	
}
