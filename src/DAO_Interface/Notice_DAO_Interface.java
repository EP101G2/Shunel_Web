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
	public List<Notice> getAllSystem() {
		String sql = "select * from NOTICE WHERE NOTICE_CATEGORY_ID = 2 ";
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
	public List<Notice> getAll() {
		// TODO Auto-generated method stub
		return null;
	}


	

}
