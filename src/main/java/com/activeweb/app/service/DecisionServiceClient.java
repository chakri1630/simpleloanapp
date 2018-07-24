package com.activeweb.app.service;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.activeweb.app.beans.LoanApplication;
import com.activeweb.app.dao.ApplicationDAO;
import com.activeweb.app.service.beans.Application;
import com.activeweb.app.service.beans.Decision;

@Component
public class DecisionServiceClient {

	private String serviceUrl = "http://localhost:8081/decisionservice/application";
	
	Logger logger = LoggerFactory.getLogger(DecisionServiceClient.class);

	@Autowired
	ApplicationDAO applicationDAO;
	
	public Decision consumerDecisionService(Integer applicationId) {
	
		LoanApplication loanApplication = applicationDAO.retrieveApplication(applicationId);
		
		Application application = new Application();

		application.setSsn(loanApplication.getSsn());
		application.setDownPayment(loanApplication.getDownPayment());
		application.setLoanAmount(loanApplication.getLoanAmount());

		RestTemplate restTemplate = new RestTemplate();

		return restTemplate.postForObject(serviceUrl, application, Decision.class);

	}

}
