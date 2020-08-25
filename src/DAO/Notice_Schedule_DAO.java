package DAO;

import java.util.List;

import Bean.Notice_Schedule;

public interface Notice_Schedule_DAO {
	
	List<Notice_Schedule> getScheduleNAll();

	int insert(Notice_Schedule notice_Schedule);

	int update(Notice_Schedule notice_Schedule);

	int delete(int notice_Schedule_ID);

}
