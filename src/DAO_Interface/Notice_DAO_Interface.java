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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Notice notice) {
		// TODO Auto-generated method stub
		return 0;
	}

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
		String sql = "SELECT * FROM Shunel.NOTICE WHERE NOTICE_CATEGORY_ID = 3 or NOTICE_CATEGORY_ID = 4 order by notice_time desc; ";
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

	@Override
	public int putGoodNotice(int order_id) {
		int count = 0;

		String sql = "INSERT INTO Shunel.NOTICE ( NOTICE_TITLE, NOTICE_CONTENT, NOTICE_CATEGORY_ID, CATEGORY_MESSAGE_ID ) VALUES ( ? , ? , ?, ? );";

		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {
			ps.setString(1, "訂單已成立");
			ps.setString(2, "您的訂單編號為  " + order_id + " ，將儘快為您出貨");
			ps.setInt(3, 3);
			ps.setInt(4, order_id);
			System.out.print(ps.toString());
			count = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public int getGoodNotice(int order_id) {
		int count = 0;
		String sql = "UPDATE Shunel.NOTICE SET NOTICE_TITLE = ? , NOTICE_CONTENT = ? WHERE ( NOTICE_CATEGORY_ID = 3  and  CATEGORY_MESSAGE_ID = ?);"
				+ "select NOTICE_TITLE , NOTICE_CONTENT from NOTICE where NOTICE_CATEGORY_ID = 3 and GATEGORY_MESSAGE_ID = ? ;";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {
			ps.setString(1, "商品已送出");
			ps.setString(2, "訂單編號" + order_id + " 已出貨，再麻煩簽收，謝謝!");
			ps.setInt(3, order_id);
			ps.setInt(4, order_id);
			System.out.print(ps.toString());
			count = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public int putProductNotice(int product_id, String product_name) {
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

	public int putChatNotice(int chat_id, String chat_Msg) {
		int count = 0;
		return count;
	}



};
