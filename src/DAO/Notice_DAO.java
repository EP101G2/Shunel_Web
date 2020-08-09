package DAO;

import java.util.List;

import Bean.Notice;

public interface Notice_DAO {
	int insert(Notice notice);
	
	//訂單成立時把order_id 及 NOTICE_CATEGORY_ID = 3 放入Notice 表格中
	int putGoodsNotice(int order_id);
	
	//找到Notice表格中的 order_id，並對其發送個人通知
	int sendGoodsNotice(String order_id);
	
	int sendProductNotice(int product_id,String product_name);

	int update(Notice notice);

	int delete(int notice_ID);

	Notice findById(int notice_ID);

	List<Notice> getNoticeAll();

	List<Notice> getSystemAll();

	List<Notice> getSaleAll();

	List<Notice> getQAAll();
	
	List<String> getToken();

	Notice getLastSaleN();

	Notice getLastQAN();

	Notice getLastSystemN();

	int sendSaleN(String notice_title, String notice_content);

	int sendSystemN(String notice_title, String notice_content);
	
	String  getOneTokenFromOrderMain(String order_ID);

	Notice TitleAndDetail(int CATEGORY_ID, String GATEGORY_MESSAGE_ID);

	int sendGoodsPriceNotice(int order_id);


	

	

	
	
}
