import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.zerock.wecart.job.PublicDataJob;


public class PublicDataQuartz {

	public static void main(String[] args) throws SchedulerException {
		
		SchedulerFactory factory = new StdSchedulerFactory();
		Scheduler scheduler = factory.getScheduler();
		
//		-----
		
		JobDetail job = JobBuilder.newJob(PublicDataJob.class)
								  .withDescription("Update Public Data on the 1st of each month")
								  .withIdentity("PublicDataJob")
								  .build();		
		
//		-----

		String cronExpression = "0 0 2 1 * ?";
		
		Trigger cronTrigger =
			TriggerBuilder.newTrigger().startNow().
				<CronTrigger>withSchedule(									
					CronScheduleBuilder.cronSchedule(cronExpression)
				).build();
		
//		-----
		
		scheduler.scheduleJob(job, cronTrigger);

		scheduler.start();
	} //main
	
} //end class
