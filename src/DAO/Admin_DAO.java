package DAO;

import java.util.List;

import Bean.Admin;


public interface Admin_DAO {

	void insert(Admin admin);
	void update(Admin admin);
	void delete(int admin_id);
	Admin findById(int admin_id);
	List<Admin> getAll();
	
}
