package DAO;

import java.util.List;

import Bean.Notice;

public interface Notice_DAO {
	int insert(Notice notice);
	
	//訂單成立時把order_id 及 NOTICE_CATEGORY_ID = 3 放入Notice 表格中
	int putGoodNotice(int order_id);
	
	//找到Notice表格中的 order_id，並對其發送個人通知
	int getGoodNotice(int order_id);
	
	int putProductNotice(int product_id,String product_name);
	

	int update(Notice notice);

	int delete(int notice_ID);

	Notice findById(int notice_ID);

	List<Notice> getNoticeAll();

	List<Notice> getSystemAll();

	List<Notice> getSaleAll();

	List<Notice> getQAAll();

	Notice getLastSaleN();

	Notice getLastQAN();

	Notice getLastSystemN();
}
