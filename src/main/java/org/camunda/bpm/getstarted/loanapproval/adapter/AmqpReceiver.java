package org.camunda.bpm.getstarted.loanapproval.adapter;

import java.util.UUID;

import org.camunda.bpm.engine.ProcessEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

//import com.camunda.demo.springboot.ProcessConstants;

@Component
//@Profile("!test")
public class AmqpReceiver {
	
	private static Logger logger=LoggerFactory.getLogger(AmqpReceiver.class);

  @Autowired
  private ProcessEngine camunda;

  public AmqpReceiver() {
  }
  
  public AmqpReceiver(ProcessEngine camunda) {
    this.camunda = camunda;
  }
  
  /**
   * Dummy method to handle the shipGoods command message - as we do not have a 
   * shipping system available in this small example
   */
  @RabbitListener(bindings = @QueueBinding( //
      value = @Queue(value = "shipping_create_test", durable = "true"), //
      exchange = @Exchange(value = "shipping", type = "topic", durable = "true"), //
      key = "*"))
  @Transactional  
  public void dummyShipGoodsCommand(String orderId) {
	  logger.info("++++++dummyShipGoodsCommand->orderId="+orderId);
	 logger.info("++++++ I am sleeping 10 seconds ");
	  try {
		Thread.sleep(10000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    // and call back directly with a generated transactionId
	 handleGoodsShippedEvent(orderId, UUID.randomUUID().toString());
  }

  public void handleGoodsShippedEvent(String orderId, String shipmentId) {
    camunda.getRuntimeService().createMessageCorrelation(ProcessConstants.MSG_NAME_GoodsShipped) //
    //.processDefinitionId("order:1:5f90848a-15d0-11ec-9fb4-64006a77f166")   
   // .processInstanceBusinessKey(orderId)
   // .processInstanceId(shipmentId)
    .processInstanceVariableEquals(ProcessConstants.VAR_NAME_orderId, orderId) //
        .setVariable(ProcessConstants.VAR_NAME_shipmentId, shipmentId) //
        //.correlateWithResult();
       . correlate();
    
  }
  
}
