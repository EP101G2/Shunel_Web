package DAO;

import java.util.List;

import Bean.Authority;

public interface Authority_DAO {

	void insert(Authority authority);

	void update(Authority authority);

	void delete(int admin_ID, int function_ID);

	Authority findById(int admin_ID, int function_ID);

	List<Authority> getAll();
}
