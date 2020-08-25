package Servlet_Shunel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import Bean.Notice_Schedule;
import DAO.Notice_DAO;
import DAO.Notice_Schedule_DAO;
import DAO_Interface.Notice_DAO_Interface;
import DAO_Interface.Notice_Schedule_DAO_Interface;

/**
 * Servlet implementation class Notice_Schedule_Servlet
 */
@WebServlet("/Notice_Schedule_Servlet")
public class Notice_Schedule_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static String CONTENT_TYPE = "text/html; charset=utf-8";
	Notice_Schedule_DAO notice_Schedule_DAO = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Notice_Schedule_Servlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (notice_Schedule_DAO == null) {
			notice_Schedule_DAO = new Notice_Schedule_DAO_Interface();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		BufferedReader br = request.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = null;
		while ((line = br.readLine()) != null) {
			jsonIn.append(line);
		}
		System.out.print("input:" + jsonIn);

		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
		if(notice_Schedule_DAO ==null) {
			notice_Schedule_DAO = new Notice_Schedule_DAO_Interface();
		}
		String action = jsonObject.get("action").getAsString();
		List<Notice_Schedule> notice_Schedules;
		Notice_Schedule notice_Schedule = null;
		int returnID;
		
		notice_Schedules = null;
		switch (action) {
		case "getScheduleNAll":
			notice_Schedules = notice_Schedule_DAO.getScheduleNAll();
			writeText(response, gson.toJson(notice_Schedules));
			break;
			
		case "insertScheduleNotice":
			 returnID = notice_Schedule_DAO.insert(notice_Schedule);
			QuartzManager.addJob(String.valueOf(returnID), QuartzJob.class,notice_Schedule.getNOTICE_SCHEDUL_STARTTIME() , notice_Schedule.getPRODUCT_ID(),notice_Schedule.getNOTICE_SCHEDULE_T(),notice_Schedule.getNOTICE_SCHEDULE_D());
			
			break;
			
			case"updateNoticeSchedule":
				QuartzManager.modifyJobTime(String.valueOf(notice_Schedule.getNOTICE_SCHEDULE_ID()),notice_Schedule.getNOTICE_SCHEDUL_STARTTIME(),notice_Schedule.getPRODUCT_ID(),notice_Schedule.getNOTICE_SCHEDULE_T(), notice_Schedule.getNOTICE_SCHEDULE_D());
					
			
		}
		
		doGet(request, response);
	}

	private void writeText(HttpServletResponse response, String outText) throws IOException {
		response.setContentType(CONTENT_TYPE);
		PrintWriter out = response.getWriter();
		out.print(outText);
		// 將輸出資料列印出來除錯用
		System.out.println("output: " + outText);
	}

}
