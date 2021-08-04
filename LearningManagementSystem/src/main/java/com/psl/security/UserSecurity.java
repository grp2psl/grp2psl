package com.psl.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component("userSecurity")
public class UserSecurity {
    public boolean hasId(Authentication authentication, int id) {
        String currentUserId = String.valueOf(SecurityContextHolder.getContext().getAuthentication().getName());
        String anotherUserId = String.valueOf(id);
        if (currentUserId.equals(anotherUserId)) {
            return true;
        }
        else {
            return false;
        }
    }
    public String successURL() {
        String currentUserId = String.valueOf(SecurityContextHolder.getContext().getAuthentication().getName());
        String url = "/learners/" + currentUserId + "/";
        return url;
    }
}