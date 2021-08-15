package com.psl.entities;

/*
 * IN_PROGRESS - Learner has enrolled and course training is in progress.
 * FAIL - Learner has scored < 70% in test.
 * PASS - Learner has scored >= 70% in test.
 * FEEDBACK_GIVEN - Learner has given the feedback.
 * FEEDBACK_PENDING - Learner has passed but has not given the feedback.
 * COMPLETED - Learner has passed as well as has given the feedback. 
 */
public enum CourseOfferingStatus {
	IN_PROGRESS, FAIL, PASS, FEEDBACK_GIVEN, FEEDBACK_PENDING, COMPLETED
}
