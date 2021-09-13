package org.camunda.bpm.getstarted.loanapproval.rest.dto;

public class CommonResponse {
	private boolean resultFlag;
	private String message;
	public boolean isResultFlag() {
		return resultFlag;
	}
	public void setResultFlag(boolean resultFlag) {
		this.resultFlag = resultFlag;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
