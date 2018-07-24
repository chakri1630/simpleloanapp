package com.activeweb.app.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.activeweb.auto.constants.AutoLoanAppConstants;

@Controller
public class HomeController {

	Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home() {

		logger.info("Home handler is called..");

		return AutoLoanAppConstants.HOMEPAGE;

	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam("userName") String userName,
			@RequestParam("password") String password, Model model) {

		logger.info("Login handler is called..");

		if (userName != null && password != null) {
			return AutoLoanAppConstants.APPLICATIONPAGE;
		}

		logger.info("Home handler is done..");

		return AutoLoanAppConstants.ERRORPAGE;

	}

	@RequestMapping(value = "/loanstatus/{loanId}", method = RequestMethod.GET)
	public String login(@PathVariable("loanId") String loanID, Model model) {

		logger.info("Decision handler is called..");

		//ModelAndView modelAndView = new ModelAndView();
		
		if(loanID.equals("1212")) {
			model.addAttribute("status", "you are approved");
		}else {
			model.addAttribute("status", "we need more docuemntation");
		}
		
		
		logger.info("Decision handler is done..");

		return AutoLoanAppConstants.DECISIONPAGE;

	}
	
	
}
