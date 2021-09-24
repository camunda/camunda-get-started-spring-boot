package org.camunda.bpm.getstarted.loanapproval.adapter;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//import com.camunda.demo.springboot.ProcessConstants;

@Component
public class SendShipGoodsAmqpAdapter implements JavaDelegate {
	
	private static Logger logger=LoggerFactory.getLogger(SendShipGoodsAmqpAdapter.class);

  @Autowired
  protected RabbitTemplate rabbitTemplate;
  
  @Override
  public void execute(DelegateExecution ctx) throws Exception {
    String orderId = (String) ctx.getVariable(ProcessConstants.VAR_NAME_orderId);    
    
    String exchange = "shipping";
    String routingKey = "createShipment";
     
    logger.info("+++++++orderId="+ orderId 
		    					  + " | " +" exchange= " + exchange  
		    					  + " | " + " routingKey=" + routingKey); 
    rabbitTemplate.convertAndSend(exchange, routingKey, orderId);
  }

}
