package DAO_Interface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.xdevapi.Result;
import com.sun.source.tree.WhileLoopTree;

import Bean.Like;
import Bean.Notice;
import Bean.Product;
import DAO.Notice_DAO;
import Servlet_Shunel.ServiceLocator;

public class Notice_DAO_Interface implements Notice_DAO {
	DataSource dataSource;

	public Notice_DAO_Interface() {
		dataSource = ServiceLocator.getInstance().getDataSource();
	}

	@Override
	public int insert(Notice notice) {
		return 0;
	}

	// 修改促銷、系統訊息
	@Override
	public int update(Notice notice) {
		int count = 0;
		String sql = "UPDATE Shunel.NOTICE SET NOTICE_TITLE = ? , NOTICE_CONTENT = ? WHERE NOTICE_ID = ?;";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {
			ps.setString(1, notice.getNotice_Title());
			ps.setString(2, notice.getNotice_Content());
			ps.setInt(3, notice.getNotice_ID());
			System.out.println("ssss" + ps);
			count = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	// 刪除訊息
	@Override
	public int delete(int notice_ID) {
		int count = 0;
		String sql = "DELETE FROM NOTICE WHERE NOTICE_ID = ?;";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {
			ps.setInt(1, notice_ID);
			count = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public List<String> getAllLike(int product_ID) {
		String sql = "select ACCOUNT_ID from Shunel.like where PRODUCT_ID = ? ;";
		List<String> userList = new ArrayList<String>();
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {
			ps.setInt(1, product_ID);
			System.out.println(ps.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String str = rs.getString(1);
				userList.add(str);
				System.out.println(userList);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}

	@Override
	public String getProduct_Name(int product_ID) {
		String sql = "SELECT PRODUCT_NAME FROM Shunel.PRODUCT where PRODUCT_ID = ?;";
		String str = "";
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			{
				ps.setInt(1, product_ID);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					str = rs.getString(1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("商品名稱為：" + str);
		return str;
	}

	@Override
	public int sendLikeNotice(String product_Name, int product_ID, String account_ID) {
		int count = 0;
		String sql = "INSERT INTO Shunel.NOTICE ( NOTICE_TITLE, NOTICE_CONTENT, NOTICE_CATEGORY_ID, CATEGORY_MESSAGE_ID, ACCOUNT_ID ) VALUES ( ? , ? , ? , ? , ? );";

		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {
			ps.setString(1, "您關注的" + product_Name + "現已重新上架");
			ps.setString(2, product_Name + "重新上架了，非常感謝您關注此商品，歡迎前往購買，如對商品有任何疑問，也可在提問中與我們聯繫");
			ps.setInt(3, 3);
			ps.setInt(4, product_ID);
			ps.setString(5, account_ID);
			System.out.print(ps.toString());
			count = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public Notice findById(int notice_ID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Notice> getSaleAll() {
		String sql = "select * from NOTICE WHERE NOTICE_CATEGORY_ID = 0 ORDER BY  NOTICE_TIME DESC;";
		List<Notice> noticeList = new ArrayList<Notice>();
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println("--------");
				int NOTICE_ID = rs.getInt(1);
				String NOTICE_TITLE = rs.getString(2);
				String NOTICE_CONTENT = rs.getString(3);
				Timestamp NOTICE_TIME = rs.getTimestamp(4);
				int NOTICE_CATEGORY_ID = rs.getInt(5);
				int CATEGORY_MESSAGE_ID = rs.getInt(6);
				String ACCOUNT_ID = rs.getString(7);
				Notice notice = new Notice(NOTICE_ID, NOTICE_TITLE, NOTICE_CONTENT, NOTICE_TIME, NOTICE_CATEGORY_ID,
						CATEGORY_MESSAGE_ID, ACCOUNT_ID);
				noticeList.add(notice);
			}
			return noticeList;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return noticeList;
	}

	@Override
	public List<Notice> getGoodsAll(String Account_ID) {
		String sql = "select * from NOTICE WHERE NOTICE_CATEGORY_ID = 1 and ACCOUNT_ID = ? ORDER BY  NOTICE_TIME DESC;";
		List<Notice> noticeList = new ArrayList<Notice>();
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {
			ps.setString(1, Account_ID);
			ResultSet rs = ps.executeQuery();
			System.out.print(ps.toString());
			while (rs.next()) {
				System.out.println("--------");
				int NOTICE_ID = rs.getInt(1);
				String NOTICE_TITLE = rs.getString(2);
				String NOTICE_CONTENT = rs.getString(3);
				Timestamp NOTICE_TIME = rs.getTimestamp(4);
				int NOTICE_CATEGORY_ID = rs.getInt(5);
				int CATEGORY_MESSAGE_ID = rs.getInt(6);
				String ACCOUNT_ID = rs.getString(7);
				Notice notice = new Notice(NOTICE_ID, NOTICE_TITLE, NOTICE_CONTENT, NOTICE_TIME, NOTICE_CATEGORY_ID,
						CATEGORY_MESSAGE_ID, ACCOUNT_ID);
				noticeList.add(notice);
			}
			return noticeList;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return noticeList;
	}

	@Override
	public List<Notice> getSystemAll() {
		String sql = "select * from NOTICE WHERE NOTICE_CATEGORY_ID = 2 ORDER BY  NOTICE_TIME DESC;";
		List<Notice> noticeList = new ArrayList<Notice>();
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println("--------");
				int NOTICE_ID = rs.getInt(1);
				String NOTICE_TITLE = rs.getString(2);
				String NOTICE_CONTENT = rs.getString(3);
				Timestamp NOTICE_TIME = rs.getTimestamp(4);
				int NOTICE_CATEGORY_ID = rs.getInt(5);
				int CATEGORY_MESSAGE_ID = rs.getInt(6);
				String ACCOUNT_ID = rs.getString(7);
				Notice notice = new Notice(NOTICE_ID, NOTICE_TITLE, NOTICE_CONTENT, NOTICE_TIME, NOTICE_CATEGORY_ID,
						CATEGORY_MESSAGE_ID, ACCOUNT_ID);
				noticeList.add(notice);
			}
			return noticeList;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return noticeList;

	}

	@Override

	public List<Notice> getNoticeAll(String Account_ID) {
		String sql = "SELECT * FROM Shunel.NOTICE WHERE NOTICE_CATEGORY_ID = 3 and ACCOUNT_ID = ? order by notice_time desc; ";
		String sql2 = "SELECT PRODUCT_STATUS , PRICE ,PROMOTION_PRICE from PRODUCT pd left join PROMOTION pm  on pd.PRODUCT_ID = pm.PRODUCT_ID where pd.PRODUCT_ID  = ?;";

		List<Notice> noticeList = new ArrayList<Notice>();
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);
				PreparedStatement ps2 = connection.prepareStatement(sql2);) {
			ps.setString(1, Account_ID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				System.out.println("--------");
				int NOTICE_ID = rs.getInt(1);
				String NOTICE_TITLE = rs.getString(2);
				String NOTICE_CONTENT = rs.getString(3);
				Timestamp NOTICE_TIME = rs.getTimestamp(4);
				int NOTICE_CATEGORY_ID = rs.getInt(5);
				int CATEGORY_MESSAGE_ID = rs.getInt(6);
				String ACCOUNT_ID = rs.getString(7);

				int product_status = 0;
				int price = 0;
				int promotion_price = 0;
				ps2.setInt(1, CATEGORY_MESSAGE_ID);
				ResultSet rs2 = ps2.executeQuery();
				if (rs2.next()) {
					product_status = rs2.getInt("PRODUCT_STATUS");
					price = rs2.getInt("PRICE");
					promotion_price = rs2.getInt("PROMOTION_PRICE");
				}

				Notice notice = new Notice(NOTICE_ID, NOTICE_TITLE, NOTICE_CONTENT, NOTICE_TIME, NOTICE_CATEGORY_ID,
						CATEGORY_MESSAGE_ID, ACCOUNT_ID);
				notice.setPRODUCT_STATUS(product_status);
				notice.setPrice(product_status == 1 ? price : promotion_price);
				noticeList.add(notice);
			}
			return noticeList;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return noticeList;

	}

	@Override
	public Notice getLastSystemN() {
		String sql = "SELECT * FROM Shunel.notice WHERE NOTICE_CATEGORY_ID = 2 order by notice_time desc limit 1 ; ";
		Notice lastSystemN = null;
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println("--------");
				int NOTICE_ID = rs.getInt(1);
				String NOTICE_TITLE = rs.getString(2);
				String NOTICE_CONTENT = rs.getString(3);
				Timestamp NOTICE_TIME = rs.getTimestamp(4);
				int NOTICE_CATEGORY_ID = rs.getInt(5);
				int CATEGORY_MESSAGE_ID = rs.getInt(6);
				String ACCOUNT_ID = rs.getString(7);
				lastSystemN = new Notice(NOTICE_ID, NOTICE_TITLE, NOTICE_CONTENT, NOTICE_TIME, NOTICE_CATEGORY_ID,
						CATEGORY_MESSAGE_ID, ACCOUNT_ID);
			}
			return lastSystemN;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lastSystemN;
	}

	@Override
	public Notice getLastGoodN(String Account_ID) {
		String sql = "SELECT * FROM Shunel.notice WHERE NOTICE_CATEGORY_ID = 1 and ACCOUNT_ID =  ? order by notice_time desc limit 1 ; ";
		Notice lastGoodN = null;
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {
			ps.setString(1, Account_ID);
			ResultSet rs = ps.executeQuery();
			System.out.print(ps.toString());
			while (rs.next()) {
				System.out.println("--------");
				int NOTICE_ID = rs.getInt(1);
				String NOTICE_TITLE = rs.getString(2);
				String NOTICE_CONTENT = rs.getString(3);
				Timestamp NOTICE_TIME = rs.getTimestamp(4);
				int NOTICE_CATEGORY_ID = rs.getInt(5);
				int CATEGORY_MESSAGE_ID = rs.getInt(6);
				String ACCOUNT_ID = rs.getString(7);
				lastGoodN = new Notice(NOTICE_ID, NOTICE_TITLE, NOTICE_CONTENT, NOTICE_TIME, NOTICE_CATEGORY_ID,
						CATEGORY_MESSAGE_ID, ACCOUNT_ID);
			}
			return lastGoodN;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lastGoodN;
	}

	@Override
	public Notice getLastSaleN() {
		String sql = "SELECT * FROM Shunel.notice WHERE NOTICE_CATEGORY_ID = 0 order by notice_time desc limit 1 ; ";
		Notice lastSaleN = null;
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println("--------");
				int NOTICE_ID = rs.getInt(1);
				String NOTICE_TITLE = rs.getString(2);
				String NOTICE_CONTENT = rs.getString(3);
				Timestamp NOTICE_TIME = rs.getTimestamp(4);
				int NOTICE_CATEGORY_ID = rs.getInt(5);
				int CATEGORY_MESSAGE_ID = rs.getInt(6);
				String ACCOUNT_ID = rs.getString(7);
				lastSaleN = new Notice(NOTICE_ID, NOTICE_TITLE, NOTICE_CONTENT, NOTICE_TIME, NOTICE_CATEGORY_ID,
						CATEGORY_MESSAGE_ID, ACCOUNT_ID);
			}
			return lastSaleN;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lastSaleN;
	}

	// 發送單一對象商品重新上架訊息(like)
	// 逸軒
	@Override
	public int sendProductNotice(int product_id, String product_name) {
		int count = 0;
		String sql = "INSERT INTO Shunel.NOTICE ( NOTICE_TITLE, NOTICE_CONTENT, NOTICE_CATEGORY_ID, CATEGORY_MESSAGE_ID ) VALUES ( ? , ? , ?, ? );";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {
			ps.setString(1, "您關注的商品已重新上架");
			ps.setString(2, "久等了，您關注的" + product_id + " 已重新上架，歡迎購買");
			ps.setInt(3, 4);
			ps.setInt(4, product_id);
			System.out.print(ps.toString());
			count = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	// 發送群體促銷訊息(Notice)
	@Override
	public int sendSaleN(String notice_title, String notice_content) {
		int count = 0;
		String sql = "INSERT INTO Shunel.NOTICE ( NOTICE_TITLE, NOTICE_CONTENT, NOTICE_CATEGORY_ID) VALUES ( ? , ? , ? );";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {
			ps.setString(1, notice_title);
			ps.setString(2, notice_content);
			ps.setInt(3, 0);
			System.out.print(ps.toString());
			count = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	
	//針對單一商品推
	@Override
	public int sendSaleNAndProduct(String notice_title, String notice_content, int product_ID) {
		int count = 0;
		String sql = "INSERT INTO Shunel.NOTICE ( NOTICE_TITLE, NOTICE_CONTENT, NOTICE_CATEGORY_ID,CATEGORY_MESSAGE_ID) VALUES ( ? , ? , ? , ?);";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {
			ps.setString(1, notice_title);
			ps.setString(2, notice_content);
			ps.setInt(3, 0);
			ps.setInt(4, product_ID);
			System.out.print(ps.toString());
			count = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	// 發送群體系統訊息(Notice)
	@Override
	public int sendSystemN(String notice_title, String notice_content) {
		int count = 0;
		String sql = "INSERT INTO Shunel.NOTICE ( NOTICE_TITLE, NOTICE_CONTENT, NOTICE_CATEGORY_ID) VALUES ( ? , ? , ? );";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {
			ps.setString(1, notice_title);
			ps.setString(2, notice_content);
			ps.setInt(3, 2);
			System.out.print(ps.toString());
			count = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	// 取全部人的Token
	@Override
	public List<String> getToken() {
		String sql = "SELECT  TOKEN FROM Shunel.USER_ACCOUNT where 1=1 ;";
		List<String> tokenList = new ArrayList<String>();
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {
			
			System.out.println(ps.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String str = "";
				str = rs.getString("TOKEN");
				tokenList.add(str);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tokenList;
	}

	// 推播內容放置
	@Override
	public Notice TitleAndDetail(int CATEGORY_ID, String GATEGORY_MESSAGE_ID) {
		Notice titleAndDetail = null;
		String sql = "select NOTICE_TITLE , NOTICE_CONTENT from NOTICE where NOTICE_CATEGORY_ID = ? and CATEGORY_MESSAGE_ID = ? ;";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {
			ps.setInt(1, CATEGORY_ID);
			ps.setString(2, GATEGORY_MESSAGE_ID);
			ResultSet rs = ps.executeQuery();
			System.out.println(ps.toString());
			while (rs.next()) {
				System.out.println("--------");
				String NOTICE_TITLE = rs.getString(1);
				String NOTICE_CONTENT = rs.getString(2);
				titleAndDetail = new Notice(NOTICE_TITLE, NOTICE_CONTENT);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return titleAndDetail;
	}

	// 發送單一對象訂單成立訊息(order_main)
	// 亭葳
	@Override
	public int putGoodsNotice(int order_id) {
		int count = 0;

		String sql = "INSERT INTO Shunel.NOTICE ( NOTICE_TITLE, NOTICE_CONTENT, NOTICE_CATEGORY_ID, CATEGORY_MESSAGE_ID ) VALUES ( ? , ? , ?, ? );";

		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {
			ps.setString(1, "訂單已成立");
			ps.setString(2, "您的訂單編號為  " + order_id + " ，將於付款後出貨");
			ps.setInt(3, 1);
			ps.setInt(4, order_id);
			System.out.print(ps.toString());
			count = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	// 發送付款通過訊息
	// 亭葳
	@Override
	public int sendGoodsPriceNotice(int order_id) {
		int count = 0;
		System.out.print("test!");
		String sql = "UPDATE Shunel.NOTICE SET NOTICE_TITLE = ? , NOTICE_CONTENT = ? WHERE ( NOTICE_CATEGORY_ID = 1  and  CATEGORY_MESSAGE_ID = ?);";

		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {
			ps.setString(1, "付款已通過");
			ps.setString(2, "訂單編號" + order_id + "付款已通過，感謝您的購買，將盡快為您安排出貨");
			ps.setInt(3, order_id);
			System.out.print(ps.toString());
			count = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	// 發送單一對象出貨訊息(order_main)
	// 亭葳
	@Override
	public int sendGoodsNotice(String order_id) {
		int count = 0;
		String sql = "UPDATE Shunel.NOTICE SET NOTICE_TITLE = ? , NOTICE_CONTENT = ? WHERE ( NOTICE_CATEGORY_ID = 1  and  CATEGORY_MESSAGE_ID = ?);";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {
			ps.setString(1, "商品已送出");
			ps.setString(2, "訂單編號" + order_id + " 已出貨，再麻煩簽收，謝謝!");
			ps.setString(3, order_id);
			System.out.print(ps.toString());
			count = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	// 發送單一對象到貨訊息(order_main)
	// 亭葳
	@Override
	public int goodsFinishNotice(String order_id) {
		int count = 0;
		String sql = "UPDATE Shunel.NOTICE SET NOTICE_TITLE = ? , NOTICE_CONTENT = ? WHERE ( NOTICE_CATEGORY_ID = 1  and  CATEGORY_MESSAGE_ID = ?);";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {
			ps.setString(1, "商品已送達");
			ps.setString(2, "訂單編號" + order_id + " 已送達您指定的地區，再麻煩前往領取，謝謝!");
			ps.setString(3, order_id);
			System.out.print(ps.toString());
			count = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	
	
	// 發送單一對象訂單取消訊息(order_main)
	// 亭葳
	@Override
	public int goodsCancelNotice(String order_id) {
		int count = 0;
		String sql = "UPDATE Shunel.NOTICE SET NOTICE_TITLE = ? , NOTICE_CONTENT = ? WHERE ( NOTICE_CATEGORY_ID = 1  and  CATEGORY_MESSAGE_ID = ?);";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {
			ps.setString(1, "訂單已取消");
			ps.setString(2, "訂單編號" + order_id + "已取消，歡迎您再度光臨選購");
			ps.setString(3, order_id);
			System.out.print(ps.toString());
			count = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	
	// 發送單一對象退貨訊息(order_main)
	// 亭葳
	@Override
	public int goodsReturnNotice(String order_id) {
		int count = 0;
		String sql = "UPDATE Shunel.NOTICE SET NOTICE_TITLE = ? , NOTICE_CONTENT = ? WHERE ( NOTICE_CATEGORY_ID = 1  and  CATEGORY_MESSAGE_ID = ?);";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {
			ps.setString(1, "商品已辦理退貨");
			ps.setString(2, "訂單編號" + order_id + "已退貨成功，造成您的不便敬請見諒");
			ps.setString(3, order_id);
			System.out.print(ps.toString());
			count = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}


	// 亭葳
	// 從訂單總表連到USER_ACCOUNT去取Token
	@Override
	public String getOneTokenFromOrderMain(String order_ID) {
		String str = "";
		String sql = "SELECT TOKEN FROM Shunel.USER_ACCOUNT ua join ORDER_MAIN om on ua.ACCOUNT_ID = om.ACCOUNT_ID where ORDER_ID = ?;";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {
			ps.setString(1, order_ID);
			System.out.print(ps.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				str = rs.getString("TOKEN");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return str;
	}

	@Override
	public String getAccountToken(String Account_ID) {
		String str = "";
		String sql = "SELECT TOKEN FROM Shunel.USER_ACCOUNT where ACCOUNT_ID = ? ;";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {
			ps.setString(1, Account_ID);
			System.out.print(ps.toString());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				str = rs.getString("TOKEN");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return str;
	}
	
	

//	@Override
//	public Product findByNoticeMessageId(int prouct_id) {
//		String sql = " SELECT PRODUCT_NAME, COLOR, DITAL FROM Shunel.PRODUCT WHERE PRODUCT_ID = ?;";
//		Product product = null;
//		try (Connection connection = dataSource.getConnection();
//				PreparedStatement ps = connection.prepareStatement(sql);) {
//			ps.setInt(1, prouct_id);
//			ResultSet rs = ps.executeQuery();
//			while (rs.next()) {
//				System.out.println("--------");
//				String PRODUCT_NAME = rs.getString("PRODUCT_NAME");
//				String COLOR = rs.getString("COLOR");
//				String DITAL = rs.getString("DITAL");
//				product = new Product(PRODUCT_NAME, COLOR, DITAL);
//			}
//			return product;
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return product;
//	}

	@Override
	public List<String> getOneToken(List<String> Account_ID) {
		String str = "";
		List<String> allToken = new ArrayList<String>();
		String sql = "SELECT TOKEN FROM Shunel.USER_ACCOUNT where ACCOUNT_ID = ?;";
		String account_id = "";
		for (int i = 0; i < Account_ID.size(); i++) {

			account_id = Account_ID.get(i);
			try (Connection connection = dataSource.getConnection();
					PreparedStatement ps = connection.prepareStatement(sql);) {
				ps.setString(1, account_id);
				System.out.print(ps.toString());
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					str = rs.getString("TOKEN");
					allToken.add(str);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return allToken;
	}

};
