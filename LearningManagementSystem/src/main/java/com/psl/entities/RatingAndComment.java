package com.psl.entities;

import java.util.List;

public class RatingAndComment {
	
	private float rating;
	private List<String> comments;
	
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}
	public List<String> getComments() {
		return comments;
	}
	public void setComments(List<String> comments) {
		this.comments = comments;
	}
	@Override
	public String toString() {
		return "RatingAndComment [rating=" + rating + ", comments=" + comments + "]";
	}
	public RatingAndComment() {
		super();
	}
	public RatingAndComment(float rating, List<String> comments) {
		this.rating = rating;
		this.comments = comments;
	}

}
