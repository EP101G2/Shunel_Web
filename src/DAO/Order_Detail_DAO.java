 package DAO;

import java.util.List;

import Bean.Order_Detail;
import Bean.Order_Main;
import Bean.Product;

public interface Order_Detail_DAO {

	int insert(Order_Detail order_Detail);

	int update(Order_Detail order_Detail);

	int delete(int Order_ID);

	byte[] getImage(int id);

	Order_Detail findById(int Order_ID);

	List<Order_Detail> getAll();

}
