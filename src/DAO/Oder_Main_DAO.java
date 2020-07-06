package DAO;

import java.util.List;

import Bean.Order_Detail;

public interface Oder_Main_DAO {

	
	int insert(Order_Detail order_Detail);

	int update(Order_Detail order_Detail);

	int delete(int Order_ID, int Product_ID);

	Order_Detail findById(int Order_ID, int Product_ID);

	List<Order_Detail> getAll();

	
	
}
