package org.camunda.bpm.getstarted.loanapproval.rest.dto;

public class LoanAppRequest {
	private String loanId;
	private Integer amount ;
	public String getLoanId() {
		return loanId;
	}
	public void setLoanId(String loanId) {
		this.loanId = loanId;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
	

}
