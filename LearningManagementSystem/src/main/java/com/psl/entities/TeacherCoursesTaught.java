package com.psl.entities;

/*
 * Entity declaration of TeacherCoursesTaught
 */




// Stores courses taught by a teacher along with its rating. 
// Not to be confused with TeacherCourseMapping which is an 
// actual entity in DB and DOES NOT store feedback ratings 
// of any course. This is sort of a temporary data storage class
public class TeacherCoursesTaught {
	
	private int trainerId;
	private int courseId;
	private int tcId;
	private float rating;
	
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}
	public int getTrainerId() {
		return trainerId;
	}
	public void setTrainerId(int trainerId) {
		this.trainerId = trainerId;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public int getTcId() {
		return tcId;
	}
	public void setTcId(int tcId) {
		this.tcId = tcId;
	}
	
	@Override
	public String toString() {
		return "TeacherCourseMapping [trainerId=" + trainerId + ", courseId=" + courseId + ", tcId=" + tcId + "]";
	}
	
	public TeacherCoursesTaught(int trainerId, int courseId, int tcId, float rating) {
		super();
		this.trainerId = trainerId;
		this.courseId = courseId;
		this.tcId = tcId;
		this.rating = rating;
	}
	
	public TeacherCoursesTaught() {
		super();
	}
}
