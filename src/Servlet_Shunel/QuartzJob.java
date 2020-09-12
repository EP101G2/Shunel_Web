package Servlet_Shunel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.jasper.tagplugins.jstl.core.If;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firestore.v1.Value;

import DAO.Notice_DAO;
import DAO.Notice_Schedule_DAO;
import DAO_Interface.Notice_DAO_Interface;
import DAO_Interface.Notice_Schedule_DAO_Interface;

public class QuartzJob implements Job {
	Notice_DAO notice_DAO;
	Notice_Schedule_DAO notice_Schedule_DAO;
	
	
	

	@Override
	public void execute(JobExecutionContext content) throws JobExecutionException {
	
		if(notice_DAO == null) {			
			notice_DAO = new Notice_DAO_Interface();
		}else if (notice_Schedule_DAO == null) {
			notice_Schedule_DAO = new Notice_Schedule_DAO_Interface();
			
		}
		
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "★★★★★★★★★★★");
		System.out.println(content.getJobDetail().getKey().getName() + "任務執行");
		JobDataMap dataMap = content.getJobDetail().getJobDataMap();
		int product_id = dataMap.getInt("product_id");
		String title = dataMap.getString("title");
		String detail = dataMap.getString("content");
		int notice_Schedule_ID = dataMap.getInt("schedule");
		System.out.println("需要推送訊息的產品編號為" + product_id);
		
		
		
		
		notice_DAO.sendSaleNAndProduct(title, detail,  product_id);
		notice_Schedule_DAO.changeScheduleFlag(notice_Schedule_ID);

		// 針對單一商品排程或全部促銷商品排程

		List<String> tokenList = notice_DAO.getToken();
		System.out.println("jjjjjjj");
		System.out.println(tokenList.size());

		FirebaseCloudMsg.getInstance().FCMsendMsgMuti(tokenList, title, detail);
	}

}
