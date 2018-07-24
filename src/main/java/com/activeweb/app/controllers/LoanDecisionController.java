package com.activeweb.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.activeweb.app.service.DecisionServiceClient;
import com.activeweb.app.service.beans.Decision;
import com.activeweb.auto.constants.AutoLoanAppConstants;

@Controller
public class LoanDecisionController {

	@Autowired
	DecisionServiceClient decisionClient;
	
	@RequestMapping(value="/getDecision", method=RequestMethod.GET)
	public ModelAndView getDecision(@RequestParam Integer applicationId) {
		
		Decision decision = decisionClient.consumerDecisionService(applicationId);
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("application_status", decision.getStatus());
		
		modelAndView.setViewName(AutoLoanAppConstants.DECISIONPAGE);
		
		return modelAndView;
		
	}
	
	@RequestMapping(value="/loanstatus", method=RequestMethod.GET)
	public String getDecisionStatusPage() {
		return AutoLoanAppConstants.LOANSTATUSPAGE;
	}
	
}
