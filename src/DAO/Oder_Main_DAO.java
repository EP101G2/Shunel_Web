package DAO;

import java.util.List;

import Bean.Order_Detail;
import Bean.Order_Main;

public interface Oder_Main_DAO {

	
	int insert(Order_Main oM);

	int update(Order_Main oM);

	int delete(int oM_ID);

	Order_Detail findById(int oM_ID);

	List<Order_Main> getAll();

	
	
}
