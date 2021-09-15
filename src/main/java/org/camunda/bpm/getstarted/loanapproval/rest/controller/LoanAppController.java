package org.camunda.bpm.getstarted.loanapproval.rest.controller;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.getstarted.loanapproval.rest.dto.CommonResponse;
import org.camunda.bpm.getstarted.loanapproval.rest.dto.LoanAppRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.camunda.demo.springboot.ProcessConstants;
@RestController
@RequestMapping("/loan")
public class LoanAppController {
	
	
	 @Autowired
	  private ProcessEngine camunda;

	 // @RequestMapping(method=RequestMethod.POST)
	// @PostMapping
	 
	 @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	  public ResponseEntity<?> submitLoanApp(@RequestBody LoanAppRequest loanAppRequest ) {
		  System.out.println("++++++Placing Loan");
		  ProcessInstance instance= placeLoan();
		  instance.getBusinessKey();
		  instance.getProcessDefinitionId();
		  instance.getProcessInstanceId();
		  System.out.println("++++++ instance.getBusinessKey()="+ instance.getBusinessKey());
		  System.out.println("++++++ instance.getProcessDefinitionId()="+instance.getProcessDefinitionId());
		  System.out.println("++++++  instance.getProcessInstanceId()="+ instance.getProcessInstanceId());	  
	      System.out.println("++++++Placing loan end");
	      CommonResponse commonResponse=new CommonResponse();
	      commonResponse.setResultFlag(true);
	     // commonResponse.setMessage("success"); 
	    
	      commonResponse.setMessage("instance.getBusinessKey()="+ instance.getBusinessKey() 
	      			+" | " + " instance.getProcessDefinitionId()="+instance.getProcessDefinitionId()
	      			+" | "+" instance.getProcessInstanceId()="+ instance.getProcessInstanceId()
	    		  );
	    
	      System.out.println(commonResponse.getMessage());
	      return ResponseEntity.status(HttpStatus.CREATED).body(commonResponse);
	  }

	  public ProcessInstance placeLoan() {
	    return camunda.getRuntimeService().startProcessInstanceByKey(//
	       // ProcessConstants.PROCESS_KEY_order, 
	    		"loanApproval",
	        Variables.createVariables() );
	        // .putValue(ProcessConstants.VAR_NAME_orderId, orderId) *	       
	        //   .putValue(ProcessConstants.VAR_NAME_amount, amount));
	  }
	  
	

}
