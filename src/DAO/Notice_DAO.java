package DAO;

import java.util.List;

import Bean.Notice;

public interface Notice_DAO {
	int insert(Notice notice);
	int update(Notice notice);
	int delete(int notice_ID);
	Notice findById(int notice_ID);
	List<Notice> getAllSystem();
	List<Notice> getNoticeAll();
}
