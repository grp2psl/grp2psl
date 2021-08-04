package com.psl.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class HomeController {
	
	@GetMapping("/homepage")
	public RedirectView redirectAfterLogin() {
		String currentUserId = String.valueOf(SecurityContextHolder.getContext().getAuthentication().getName());
		String userType = currentUserId.substring(0, Math.min(currentUserId.length(), 1));
        if (userType.equals("1")) {
            String redirectSuccessURL = "/LearningManagementSystem/learners/" + currentUserId + "/";
        	return new RedirectView(redirectSuccessURL);
        }
        else if (userType.equals("2")) {
            String redirectSuccessURL = "/LearningManagementSystem/trainers/" + currentUserId + "/";
        	return new RedirectView(redirectSuccessURL);
        }
        else if (userType.equals("3")) {
            String redirectSuccessURL = "/LearningManagementSystem/managers/" + currentUserId + "/";
        	return new RedirectView(redirectSuccessURL);
        }
		else {
			return new RedirectView("/LearningManagementSystem/");
		}
    }
}
