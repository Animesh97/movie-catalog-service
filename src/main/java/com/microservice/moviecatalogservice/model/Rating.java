package com.microservice.moviecatalogservice.model;

public class Rating {

	private String movieId;
	private int movieRating;

	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	public int getMovieRating() {
		return movieRating;
	}

	public void setMovieRating(int movieRating) {
		this.movieRating = movieRating;
	}

	public Rating(String movieId, int movieRating) {
		super();
		this.movieId = movieId;
		this.movieRating = movieRating;
	}

	public Rating() {
		super();
	}

}
