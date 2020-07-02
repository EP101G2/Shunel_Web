package DAO;

import java.util.List;

import Bean.History;

//import Bean.Category;

public interface History_DAO {
	
	
	
	int insert(History history);
	int update(History history);
	int delete(int history_id);
	History findById(int history_id);
	List<History> getAll();

}
