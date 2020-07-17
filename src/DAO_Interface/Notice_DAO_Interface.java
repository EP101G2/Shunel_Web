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
		String sql = "SELECT * FROM NOTICE WHERE NOTICE_CATEGORY_ID = 3 ; ";
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

}
