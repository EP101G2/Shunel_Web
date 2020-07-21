package DAO;

import java.util.List;

import Bean.Admin;


public interface Admin_DAO {

	int insert(Admin admin);
    int update(Admin admin);
	int delete(int admin_id);
	Admin findById(int admin_id);
	List<Admin> getAll();
	
}
