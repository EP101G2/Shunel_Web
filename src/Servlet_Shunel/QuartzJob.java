package Servlet_Shunel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.google.firebase.messaging.FirebaseMessagingException;

import DAO.Notice_DAO;
import DAO.Notice_Schedule_DAO;

public class QuartzJob implements Job {
	Notice_DAO notice_DAO;
	Notice_Schedule_DAO notice_Schedule_DAO;

	@Override
	public void execute(JobExecutionContext content) throws JobExecutionException {
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "★★★★★★★★★★★");
		System.out.println(content.getJobDetail().getKey().getName() + "任務執行");
		JobDataMap dataMap = content.getJobDetail().getJobDataMap();
		int product_id = dataMap.getInt("product_id");
		String title = dataMap.getString("title");
		String detail = dataMap.getString("content");
		System.out.println("需要推送訊息的產品編號為" + product_id);

		// 針對單一商品排程或全部促銷商品排程

		List<String> tokenList = notice_DAO.getToken();

		try {
			FirebaseCloudMsg.getInstance().FCMsendMsgMuti(tokenList, title, detail);
		} catch (FirebaseMessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
