package DAO;

import java.util.List;

import Bean.Promotion;

public interface Promotion_DAO {
	int insert(Promotion promotion);

	int update(Promotion promotion);

	int delete(int Promotion_ID);

	Promotion findById(int Promotion_ID);

	List<Promotion> getPromotionAll();
	
	List<Promotion> getPromotionForNotice();

}
