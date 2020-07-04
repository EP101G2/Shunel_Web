package DAO;

import java.util.List;

import Bean.Order_Detail;


public interface Order_Detail_DAO {
	void insert(Order_Detail order_Detail);

	void update(Order_Detail order_Detail);

	void delete(int Order_ID, int Product_ID);

	Order_Detail findById(int Order_ID, int Product_ID);

	List<Order_Detail> getAll();

}
