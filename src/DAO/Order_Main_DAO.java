package DAO;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import Bean.Order_Main;
import Bean.orderStatistics;

public interface Order_Main_DAO {
	
	int insert( Order_Main order_Main);
	int update(Order_Main order_Main);
	int delete(int Order_ID);
//	Order_Main findById(int Order_ID);
	List<Order_Main> getAll();
	int getStatus(int status);
	int updataOrder(int oM_ID,Order_Main order_Main);
	Order_Main findById(String account_ID);
	byte[] getImage(int id);
	List<Order_Main> getOrderMains(String account_ID, int status);
	List<Order_Main> getOrdersForManage();
	int updateStatus(int orderId, int status);
	Order_Main getOrderMain(int orderID);
	List<Order_Main> getShortOrderDetails(int order_ID);
	
	
	/*取得銷售統計數量-----------------------------------------------------------------------------------------------------------------------------------------*/
	List<orderStatistics> getStatistics(Timestamp date1,Timestamp date2);
}
