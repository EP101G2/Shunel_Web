package DAO;

import java.util.List;

import Bean.Order_Main;

public interface Order_Main_DAO {
	
	int insert( Order_Main order_Main);
	int update(Order_Main order_Main);
	int delete(int Order_ID);
//	Order_Main findById(int Order_ID);
	List<Order_Main> getAll();
	int getStatus(int status);
	int updataOrder(int oM_ID);
	Order_Main findById(String account_ID);
	byte[] getImage(int id);
	List<Order_Main> getShortOrderMains(String account_ID);
	List<Order_Main> getOrdersForManage();
	int updateStatus(int orderId, int status);
}
