package com.psl.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component("userSecurity")
public class UserSecurity {
    public boolean hasId(Authentication authentication, int id) {
        String currentUserId = String.valueOf(SecurityContextHolder.getContext().getAuthentication().getName());
        String anotherUserId = String.valueOf(id);
        String userType = currentUserId.substring(0, Math.min(currentUserId.length(), 1));
        if (userType.equals("3")) {
            return true;
        }
        else if (currentUserId.equals(anotherUserId)) {
            return true;
        }
        else {
            return false;
        }
    }
    public boolean hasManagerId(Authentication authentication, int id) {
        String currentUserId = String.valueOf(SecurityContextHolder.getContext().getAuthentication().getName());
        String anotherUserId = String.valueOf(id);
        if (currentUserId.equals(anotherUserId)) {
            return true;
        }
        else {
            return false;
        }
    }


    public boolean hasAccessToLearners(Authentication authentication) {
        String currentUserId = String.valueOf(SecurityContextHolder.getContext().getAuthentication().getName()).strip();
        String userType = currentUserId.substring(0, Math.min(currentUserId.length(), 1));
        if (userType.equals("1")) {
            return true;
        }
        else if (userType.equals("3")) {
            return true;
        }
        else {
            return false;
        }
    }
    public boolean hasAccessToTrainers(Authentication authentication) {
        String currentUserId = String.valueOf(SecurityContextHolder.getContext().getAuthentication().getName()).strip();
        String userType = currentUserId.substring(0, Math.min(currentUserId.length(), 1));
        if (userType.equals("2")) {
            return true;
        }
        else if (userType.equals("3")) {
            return true;
        }
        else {
            return false;
        }
    }
    public boolean hasAccessToManagers(Authentication authentication) {
        String currentUserId = String.valueOf(SecurityContextHolder.getContext().getAuthentication().getName()).strip();
        String userType = currentUserId.substring(0, Math.min(currentUserId.length(), 1));
        if (userType.equals("3")) {
            return true;
        }
        else {
            return false;
        }
    }
}