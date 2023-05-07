package org.zerock.wecart.listener;

import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.Trigger.CompletedExecutionInstruction;
import org.quartz.TriggerListener;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor
public class TriggerListenerImpl implements TriggerListener {

	
	@Override
	public String getName() {
		log.debug("getName() invoked.");
		
		return "- TriggerListener -";
	} // getName
	

	@Override
	public void triggerFired(Trigger t, JobExecutionContext ctx) {
		log.debug("triggerFired({}, {}) invoked.", t, ctx);

	} // triggerFired
	

	@Override
	public boolean vetoJobExecution(Trigger t, JobExecutionContext ctx) {
		log.debug("vetoJobExecution({}, {}) invoked.", t, ctx);
		
		return false;
	} // vetoJobExecution

	
	@Override
	public void triggerMisfired(Trigger t) {
		log.debug("triggerMisfired({}) invoked.", t);

	} // triggerMisfired

	
	@Override
	public void triggerComplete(Trigger t, JobExecutionContext ctx, CompletedExecutionInstruction triggerInstructionCode) {
		log.debug("triggerComplete({}, {}, {}) invoked.", t, ctx, triggerInstructionCode);

	} // triggerComplete

} // end class
