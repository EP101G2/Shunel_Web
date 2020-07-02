package DAO;

import java.util.List;

import Bean.Prouct;



public interface Prouct_DAO {

	void insert(Prouct prouct);
	void update(Prouct prouct);
	void delete(int id);
	Prouct findById(int prouct_id);
	List<Prouct> getAll();

	
	
}
