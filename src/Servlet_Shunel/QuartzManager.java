package Servlet_Shunel;

import java.sql.Timestamp;
import java.util.Date;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzManager {
	private static SchedulerFactory schedulerFactory = new StdSchedulerFactory();
	private static String JOB_GROUP_NAME = "MY_JOBGROUP_NAME";
	private static String TRIGGER_GROUP_NAME = "MY_TRIGGERGROUP_NAME";

	/**
	 * 新增一個定時任務，使用預設的任務組名、觸發器組名 一組任務名 + 組名 = 唯一key
	 * 可用key去SchedulerFactory內查找已成立的排程任務再做更動
	 * 
	 * @param jobName    任務名
	 * @param cls        任務
	 * @param time       時間設定
	 * @param product_id 訂單號
	 */
	// ***Warnings?

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void addJob(String jobName, Class jobClass, Timestamp StartTime, int product_id, String Title,
			String Content,int Schedule_ID) {
		try {
			// ***?
			Scheduler sched = schedulerFactory.getScheduler();
			// 與需要執行的任務class繫結
			JobDetail jobDetail = JobBuilder.newJob(jobClass)
					// ***GROUP_NAME?
					.withIdentity(jobName, JOB_GROUP_NAME).usingJobData("product_id", product_id)
					.usingJobData("title", Title).usingJobData("content", Content).usingJobData("schedule",Schedule_ID).build();

			// 排程觸發的時間在這裡設定
			Trigger trigger = TriggerBuilder.newTrigger()
					// ***GROUP_NAME?
					.withIdentity(jobName, TRIGGER_GROUP_NAME).startAt(StartTime).build();

			sched.scheduleJob(jobDetail, trigger);

			if (!sched.isShutdown()) {
				sched.start();
			}

		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 修改一個任務的觸發時間(使用預設的任務組名，觸發器名，觸發器組名)
	 * 
	 * @param jobName
	 * @param time
	 * @param product_id 訂單號
	 */
	@SuppressWarnings("rawtypes")
	public static void modifyJobTime(String jobName, Timestamp time, int product_id, String Title, String Content,int Schedule_ID) {
		try {
			TriggerKey triggerKey = TriggerKey.triggerKey(jobName, TRIGGER_GROUP_NAME);
			Scheduler sched = schedulerFactory.getScheduler();
			Trigger trigger = sched.getTrigger(triggerKey);
			if (trigger == null) {
				return;
			}
			Date oldTime = trigger.getStartTime();
			if (oldTime.getTime() != time.getTime()) {
				JobKey jobKey = JobKey.jobKey(jobName, JOB_GROUP_NAME);
				JobDetail jobDetail = sched.getJobDetail(jobKey);
				Class objJobClass = jobDetail.getJobClass();
				removeJob(jobName);
				addJob(jobName, objJobClass, time, product_id, Title, Content,Schedule_ID);
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 移除一個任務(使用預設的任務組名，觸發器名，觸發器組名)
	 * 
	 * @param jobName
	 */
	public static void removeJob(String jobName) {
		try {
			TriggerKey triggerKey = TriggerKey.triggerKey(jobName, TRIGGER_GROUP_NAME);
			JobKey jobKey = JobKey.jobKey(jobName, JOB_GROUP_NAME);
			Scheduler sched = schedulerFactory.getScheduler();
			sched.pauseTrigger(triggerKey);// 停止觸發器
			sched.unscheduleJob(triggerKey);// 移除觸發器
			sched.deleteJob(jobKey);// 刪除任務
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 啟動所有定時任務
	 */
	public static void startJobs() {
		try {
			Scheduler sched = schedulerFactory.getScheduler();
			sched.start();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 關閉所有定時任務
	 */
	public static void shutdownJobs() {
		try {
			Scheduler sched = schedulerFactory.getScheduler();
			if (!sched.isShutdown()) {
				sched.shutdown();
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
}
