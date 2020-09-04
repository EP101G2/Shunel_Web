package Servlet_Shunel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import Bean.Notice;
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
		request.setCharacterEncoding("UTF-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		BufferedReader br = request.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = null;
		while ((line = br.readLine()) != null) {
			jsonIn.append(line);
		}
		System.out.print("input:" + jsonIn);

		JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);
		if (notice_Schedule_DAO == null) {
			notice_Schedule_DAO = new Notice_Schedule_DAO_Interface();
		}
		String action = jsonObject.get("action").getAsString();
		List<Notice_Schedule> notice_Schedules;
		Notice_Schedule notice_Schedule = null;
		int returnID;

		switch (action) {
		case "getScheduleNAll":
			notice_Schedules = notice_Schedule_DAO.getScheduleNAll();
			writeText(response, gson.toJson(notice_Schedules));
			break;

		case "insert":
			String result = jsonObject.get("notice_Schedule").getAsString();
			notice_Schedule = gson.fromJson(result, Notice_Schedule.class);
			returnID = notice_Schedule_DAO.insert(notice_Schedule);
			QuartzManager.addJob(String.valueOf(returnID), QuartzJob.class,
					notice_Schedule.getNOTICE_SCHEDUL_STARTTIME(), notice_Schedule.getPRODUCT_ID(),
					notice_Schedule.getNOTICE_SCHEDULE_T(), notice_Schedule.getNOTICE_SCHEDULE_D(), returnID);

			SchedulerFactory schedulerFactory = new StdSchedulerFactory();
			String JOB_GROUP_NAME = "MY_JOBGROUP_NAME";
			String TRIGGER_GROUP_NAME = "MY_TRIGGERGROUP_NAME";

			TriggerKey triggerKey = TriggerKey.triggerKey(String.valueOf(returnID), TRIGGER_GROUP_NAME);
			Scheduler sched;
			try {
				sched = schedulerFactory.getScheduler();
				Trigger trigger = sched.getTrigger(triggerKey);
				Date oldTime = trigger.getStartTime();
				System.out.println(trigger == null ? "-trigger null-" : oldTime);

				JobKey jobKey = JobKey.jobKey(String.valueOf(returnID), JOB_GROUP_NAME);
				JobDetail jobDetail = sched.getJobDetail(jobKey);
				System.out.println(jobDetail == null ? "-jobDetail null-" : "-jobDetail not null-");

			} catch (SchedulerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			break;

		case "update":
			result = jsonObject.get("notice_Schedule").getAsString();
			notice_Schedule = gson.fromJson(result, Notice_Schedule.class);
			int update = notice_Schedule_DAO.update(notice_Schedule);
			QuartzManager.modifyJobTime(String.valueOf(notice_Schedule.getNOTICE_SCHEDULE_ID()),
					notice_Schedule.getNOTICE_SCHEDUL_STARTTIME(), notice_Schedule.getPRODUCT_ID(),
					notice_Schedule.getNOTICE_SCHEDULE_T(), notice_Schedule.getNOTICE_SCHEDULE_D(),
					notice_Schedule.getNOTICE_SCHEDULE_ID());
			writeText(response, String.valueOf(update));
			break;

		case "delete":
			String getDeleteString = "";
			getDeleteString = jsonObject.get("delete").getAsString();
			Type listType = new TypeToken<List<Notice_Schedule>>() {
			}.getType();
			notice_Schedules = gson.fromJson(getDeleteString, listType);
			int getDelete = 0;
			for (Notice_Schedule notice_Schedule2 : notice_Schedules) {
				getDelete = notice_Schedule_DAO.delete(notice_Schedule2.getNOTICE_SCHEDULE_ID());
				QuartzManager.removeJob(String.valueOf(getDelete));
				getDelete += getDelete;
			}
//			
			writeText(response, String.valueOf(getDelete));

			break;

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
