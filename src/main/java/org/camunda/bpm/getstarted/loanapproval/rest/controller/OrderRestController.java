package org.camunda.bpm.getstarted.loanapproval.rest.controller;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.getstarted.loanapproval.adapter.ProcessConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/order")
public class OrderRestController {
  
  @Autowired
  private ProcessEngine camunda;

  @RequestMapping(method=RequestMethod.POST)
  public void placeOrderPOST(String orderId, int amount) {
    placeOrder(orderId, amount);
  }

  /**
   * we need a method returning the {@link ProcessInstance} to allow for easier tests,
   * that's why I separated the REST method (without return) from the actual implementaion (with return value)
   */
  public ProcessInstance placeOrder(String orderId, int amount) {
    return camunda.getRuntimeService().startProcessInstanceByKey(//
        ProcessConstants.PROCESS_KEY_order, //
        Variables //
          .putValue(ProcessConstants.VAR_NAME_orderId, orderId) //
          .putValue(ProcessConstants.VAR_NAME_amount, amount));
  }
}
