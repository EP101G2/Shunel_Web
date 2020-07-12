package DAO;

import java.util.List;

import Bean.Order_Main;

public interface Order_Main_DAO {
	
	int insert( Order_Main order_Main);
	int update(Order_Main order_Main);
	int delete(int Order_ID);
	Order_Main findById(int Order_ID);
	List<Order_Main> getAll();
	List<Order_Main> getStatus(int status);

}
