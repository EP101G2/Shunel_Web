package DAO;

import java.util.List;

import Bean.Authority;

public interface Authority_DAO {

	void insert(Authority authority);

	void update(Authority authority);

	void delete(int Admin_ID, int Function_ID);

	Authority findById(int Admin_ID, int Function_ID);

	List<Authority> getAll();
}
