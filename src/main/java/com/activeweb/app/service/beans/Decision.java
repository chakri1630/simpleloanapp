package com.activeweb.app.service.beans;

import java.math.BigDecimal;

public class Decision {

	private String status;
	private BigDecimal apr;
	private BigDecimal approvedAmount;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getApr() {
		return apr;
	}

	public void setApr(BigDecimal apr) {
		this.apr = apr;
	}

	public BigDecimal getApprovedAmount() {
		return approvedAmount;
	}

	public void setApprovedAmount(BigDecimal approvedAmount) {
		this.approvedAmount = approvedAmount;
	}

	@Override
	public String toString() {
		return "Decision [status=" + status + ", apr=" + apr + ", approvedAmount=" + approvedAmount + "]";
	}

}
