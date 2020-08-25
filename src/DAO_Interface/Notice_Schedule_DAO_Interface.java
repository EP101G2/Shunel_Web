package DAO_Interface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import Bean.Notice_Schedule;
import DAO.Notice_DAO;
import DAO.Notice_Schedule_DAO;
import Servlet_Shunel.ServiceLocator;

public class Notice_Schedule_DAO_Interface implements Notice_Schedule_DAO {

	DataSource dataSource;

	public Notice_Schedule_DAO_Interface() {
		dataSource = ServiceLocator.getInstance().getDataSource();
	}

	@Override
	public List<Notice_Schedule> getScheduleNAll() {
		String sql = "select * from NOTICE_SCHEDULE  ORDER BY NOTICE_SCHEDUL_STARTTIME DESC;";
		List<Notice_Schedule> notice_ScheduleList = new ArrayList<Notice_Schedule>();
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println("--------");
				int NOTICE_SCHEDULE_ID = rs.getInt(1);

				String NOTICE_SCHEDULE_T = rs.getString(2);

				String NOTICE_SCHEDULE_D = rs.getString(3);

				Timestamp NOTICE_SCHEDUL_STARTTIME = rs.getTimestamp(4);

				Timestamp NOTICE_SCHEDUL_ENDTIME = rs.getTimestamp(5);

				int SCHEDULE_FLAG = rs.getInt(6);

				int PRODUCT_ID = rs.getInt(7);

				Notice_Schedule notice_Schedule = new Notice_Schedule(NOTICE_SCHEDULE_ID, NOTICE_SCHEDULE_T,
						NOTICE_SCHEDULE_D, NOTICE_SCHEDUL_STARTTIME, NOTICE_SCHEDUL_ENDTIME, SCHEDULE_FLAG, PRODUCT_ID);

				notice_ScheduleList.add(notice_Schedule);
			}
			return notice_ScheduleList;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return notice_ScheduleList;
	}

	@Override
	public int insert(Notice_Schedule notice_Schedule) {
		int count = 0;
		int Notice_schedule_ID = 0;
		String sql = "INSERT INTO NOTICE_SCHEDULE (NOTICE_SCHEDULE_T, NOTICE_SCHEDULE_D, NOTICE_SCHEDUL_STARTTIME, NOTICE_SCHEDUL_ENDTIME, SCHEDULE_FLAG, PRODUCT_ID) VALUES (?, ?, ?, ?,?, ?);";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);) {
			ps.setString(1, notice_Schedule.getNOTICE_SCHEDULE_T());
			ps.setString(2, notice_Schedule.getNOTICE_SCHEDULE_D());
			ps.setTimestamp(3, notice_Schedule.getNOTICE_SCHEDUL_STARTTIME());
			ps.setTimestamp(4, notice_Schedule.getNOTICE_SCHEDUL_ENDTIME());
			ps.setInt(5, notice_Schedule.getSCHEDULE_FLAG());
			ps.setInt(6, notice_Schedule.getPRODUCT_ID());
			System.out.print(ps.toString());
			count = ps.executeUpdate();
			
			if (count != 0) {
				ResultSet rs = ps.getGeneratedKeys();
				if(rs.next()) {
					Notice_schedule_ID = rs.getInt(1);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Notice_schedule_ID;
	}

	@Override
	public int update(Notice_Schedule notice_Schedule) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int notice_Schedule_ID) {
		// TODO Auto-generated method stub
		return 0;
	}

}
