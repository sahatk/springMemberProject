/**
 * 
 */
package com.javateam.springMemberProject.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Startup Controller
 * 
 * @author javateam
 *
 */
@Controller
public class HomeController {

	private static final Logger log
		= LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping("/")
	public String home(Model model) {
		
		log.info("################## home ################");
		
		return "redirect:/auth/login.do";
	}
}
