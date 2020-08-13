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
	
	
	//修改促銷、系統訊息
	@Override
	public int update(Notice notice) {
		int count = 0;
		String sql = "UPDATE Shunel.NOTICE SET NOTICE_TITLE = ? , NOTICE_CONTENT = ? WHERE NOTICE_ID = ?;";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {
			ps.setString(1,notice.getNotice_Title());
			ps.setString(2,notice.getNotice_Content());
			ps.setInt(3, notice.getNotice_ID());
			System.out.println("ssss"+ps);
			count = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	
	//刪除訊息
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
				Notice notice = new Notice(NOTICE_ID, NOTICE_TITLE, NOTICE_CONTENT, NOTICE_TIME, NOTICE_CATEGORY_ID,
						CATEGORY_MESSAGE_ID);
				noticeList.add(notice);
			}
			return noticeList;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return noticeList;
	}

	@Override
	public List<Notice> getQAAll() {
		String sql = "select * from NOTICE WHERE NOTICE_CATEGORY_ID = 1 ORDER BY  NOTICE_TIME DESC;";
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
				Notice notice = new Notice(NOTICE_ID, NOTICE_TITLE, NOTICE_CONTENT, NOTICE_TIME, NOTICE_CATEGORY_ID,
						CATEGORY_MESSAGE_ID);
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
				Notice notice = new Notice(NOTICE_ID, NOTICE_TITLE, NOTICE_CONTENT, NOTICE_TIME, NOTICE_CATEGORY_ID,
						CATEGORY_MESSAGE_ID);
				noticeList.add(notice);
			}
			return noticeList;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return noticeList;

	}

	@Override

	public List<Notice> getNoticeAll() {
		String sql = "SELECT * FROM Shunel.NOTICE WHERE NOTICE_CATEGORY_ID = 3  order by notice_time desc; ";
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
				Notice notice = new Notice(NOTICE_ID, NOTICE_TITLE, NOTICE_CONTENT, NOTICE_TIME, NOTICE_CATEGORY_ID,
						CATEGORY_MESSAGE_ID);
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
				lastSystemN = new Notice(NOTICE_ID, NOTICE_TITLE, NOTICE_CONTENT, NOTICE_TIME, NOTICE_CATEGORY_ID,
						CATEGORY_MESSAGE_ID);
			}
			return lastSystemN;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lastSystemN;
	}

	@Override
	public Notice getLastQAN() {
		String sql = "SELECT * FROM Shunel.notice WHERE NOTICE_CATEGORY_ID = 1 order by notice_time desc limit 1 ; ";
		Notice lastQAN = null;
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
				lastQAN = new Notice(NOTICE_ID, NOTICE_TITLE, NOTICE_CONTENT, NOTICE_TIME, NOTICE_CATEGORY_ID,
						CATEGORY_MESSAGE_ID);
			}
			return lastQAN;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lastQAN;
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
				lastSaleN = new Notice(NOTICE_ID, NOTICE_TITLE, NOTICE_CONTENT, NOTICE_TIME, NOTICE_CATEGORY_ID,
						CATEGORY_MESSAGE_ID);
			}
			return lastSaleN;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lastSaleN;
	}


	
	//發送單一對象商品重新上架訊息(like)
	//逸軒
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
	

	//發送群體促銷訊息(Notice)
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

	
	
	//發送群體系統訊息(Notice)
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

	
	
	//取全部人的Token
	@Override
	public List<String> getToken() {
		String sql = "SELECT  TOKEN FROM Shunel.USER_ACCOUNT where TOKEN != ?;";
		List<String> tokenList = new ArrayList<String>();
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {
			ps.setString(1, "");
			System.out.println(ps.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String str = "";
				str = rs.getString("TOKEN");
				tokenList.add(str);
				System.out.println(tokenList);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tokenList;
	}
	
	
	
	
	//推播內容放置
	@Override
	public Notice TitleAndDetail(int CATEGORY_ID,String GATEGORY_MESSAGE_ID) {
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
				titleAndDetail= new Notice( NOTICE_TITLE, NOTICE_CONTENT);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return titleAndDetail;
	}
	
	
	
	

	
	
	    //發送單一對象訂單成立訊息(order_main)
		//亭葳
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
		
		//發送付款通過訊息
		//亭葳
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
		
		
		//發送單一對象出貨訊息(order_main)
		//亭葳
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
		
//		@Override
//		public int 
	
	//亭葳
	//從訂單總表連到USER_ACCOUNT去取Token
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

};
