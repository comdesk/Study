package org.zerock.wecart.listener;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor
public class JobListenerImpl implements JobListener {

	
	@Override
	public String getName() {
		log.trace("getName() invoked.");
		
		return "- JobListener -";
	} // getName
	

	@Override
	public void jobToBeExecuted(JobExecutionContext ctx) {
		log.trace("jobToBeExecuted({}) invoked.", ctx);

	} // jobToBeExecuted
	

	@Override
	public void jobExecutionVetoed(JobExecutionContext ctx) {
		log.trace("jobExecutionVetoed({}) invoked.", ctx);

	} // jobExecutionVetoed
	

	@Override
	public void jobWasExecuted(JobExecutionContext ctx, JobExecutionException e) {
		log.trace("jobWasExecuted({}, {}) invoked.", ctx, e);

	} // jobWasExecuted

} // end class
