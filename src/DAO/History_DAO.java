package DAO;

import java.util.List;

import Bean.History;

//import Bean.Category;

public interface History_DAO {
	
	
	
	void insert(History history);
	void update(History history);
	void delete(int history_id);
	History findById(int history_id);
	List<History> getAll();

}
