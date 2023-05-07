package org.zerock.wecart.listener;

import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.quartz.SchedulerListener;
import org.quartz.Trigger;
import org.quartz.TriggerKey;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor
public class SchedulerListenerImpl implements SchedulerListener {

	
	@Override
	public void jobScheduled(Trigger trigger) {
		log.warn("jobScheduled({}) invoked.", trigger);

	} // jobScheduled
	

	@Override
	public void jobUnscheduled(TriggerKey triggerKey) {
		log.warn("jobUnscheduled({}) invoked.", triggerKey);

	} // jobUnscheduled

	
	@Override
	public void triggerFinalized(Trigger trigger) {
		log.warn("triggerFinalized({}) invoked.", trigger);

	} // triggerFinalized

	
	@Override
	public void triggerPaused(TriggerKey triggerKey) {
		log.warn("triggerPaused({}) invoked.", triggerKey);

	} // triggerPaused

	
	@Override
	public void triggersPaused(String triggerGroup) {
		log.warn("triggersPaused({}) invoked.", triggerGroup);

	} // triggersPaused

	
	@Override
	public void triggerResumed(TriggerKey triggerKey) {
		log.warn("triggerResumed({}) invoked.", triggerKey);

	} // triggerResumed
	

	@Override
	public void triggersResumed(String triggerGroup) {
		log.warn("triggersResumed({}) invoked.", triggerGroup);

	} // triggersResumed
	

	@Override
	public void jobAdded(JobDetail jobDetail) {
		log.warn("jobAdded({}) invoked.", jobDetail);

	} // jobAdded
	

	@Override
	public void jobDeleted(JobKey jobKey) {
		log.warn("jobDeleted({}) invoked.", jobKey);

	} // jobDeleted
	

	@Override
	public void jobPaused(JobKey jobKey) {
		log.warn("jobPaused({}) invoked.", jobKey);

	} // jobPaused
	

	@Override
	public void jobsPaused(String jobGroup) {
		log.warn("jobsPaused({}) invoked.", jobGroup);

	} // jobsPaused
	

	@Override
	public void jobResumed(JobKey jobKey) {
		log.warn("jobResumed({}) invoked.", jobKey);

	} // jobResumed
	

	@Override
	public void jobsResumed(String jobGroup) {
		log.warn("jobsResumed({}) invoked.", jobGroup);

	} // jobsResumed
	

	@Override
	public void schedulerError(String msg, SchedulerException cause) {
		log.warn("schedulerError({}, {}) invoked.", msg, cause);

	} // schedulerError
	

	@Override
	public void schedulerInStandbyMode() {
		log.warn("schedulerInStandbyMode() invoked.");

	} // schedulerInStandbyMode
	

	@Override
	public void schedulerStarted() {
		log.warn("schedulerStarted() invoked.");

	} // schedulerStarted
	

	@Override
	public void schedulerStarting() {
		log.warn("schedulerStarting() invoked.");

	} // schedulerStarting
	

	@Override
	public void schedulerShutdown() {
		log.warn("schedulerShutdown() invoked.");

	} // schedulerShutdown
	

	@Override
	public void schedulerShuttingdown() {
		log.warn("schedulerShuttingdown() invoked.");

	} // schedulerShuttingdown
	

	@Override
	public void schedulingDataCleared() {
		log.warn("schedulingDataCleared() invoked.");

	} // schedulingDataCleared
	
} // end class
