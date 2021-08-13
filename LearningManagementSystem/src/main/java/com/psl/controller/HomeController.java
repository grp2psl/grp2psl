package com.psl.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class HomeController {
	
	@GetMapping("/homepage")
	public RedirectView redirectAfterLogin() {
		RedirectView redirectView = new RedirectView();
		String currentUserId = String.valueOf(SecurityContextHolder.getContext().getAuthentication().getName());
		String userType = currentUserId.substring(0, Math.min(currentUserId.length(), 1));
        if (userType.equals("1")) {
            // String redirectSuccessURL = "/LearningManagementSystem/learners/" + currentUserId + "/";
        	// return new RedirectView(redirectSuccessURL);
			redirectView.setUrl("http://localhost:3000/learner");
			return redirectView;
        }
        else if (userType.equals("2")) {
            // String redirectSuccessURL = "/LearningManagementSystem/trainers/" + currentUserId + "/";
        	// return new RedirectView(redirectSuccessURL);
			redirectView.setUrl("http://localhost:3000/trainer");
			return redirectView;
        }
        else if (userType.equals("3")) {
            // String redirectSuccessURL = "/LearningManagementSystem/managers/" + currentUserId + "/";
        	// return new RedirectView(redirectSuccessURL);
			redirectView.setUrl("http://localhost:3000/manager");
			return redirectView;
        }
		else {
			// return new RedirectView("/LearningManagementSystem/");
			redirectView.setUrl("http://localhost:3000/");
			return redirectView;
		}
    }

	@GetMapping("/currentuserid") 
	@ResponseBody
	public String getCurrentUserId() {
		String currentUserId = String.valueOf(SecurityContextHolder.getContext().getAuthentication().getName());
		System.out.println(currentUserId);
		return currentUserId;
	}
}