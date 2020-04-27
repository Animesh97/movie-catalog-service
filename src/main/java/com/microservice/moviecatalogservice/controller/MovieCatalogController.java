package com.microservice.moviecatalogservice.controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.microservice.moviecatalogservice.model.CatalogItem;
import com.microservice.moviecatalogservice.model.MovieInfo;
import com.microservice.moviecatalogservice.model.Rating;
import com.microservice.moviecatalogservice.model.UserRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {
	
	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/{userId}")
	public List<CatalogItem> getCatalogById(@PathVariable("userId") String userId) {

		/*
		 * List<Rating> ratingList = Arrays.asList( new Rating("Movie1", 5), new
		 * Rating("Movie2", 4), new Rating("Movie3", 3));
		 */
		UserRating userRating = restTemplate.getForObject("http://ratings-data-service/ratingsData/users/" + userId,
				UserRating.class);
		return userRating.getUserRating().stream().map(rating -> {
			MovieInfo movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(),
					MovieInfo.class);
			return new CatalogItem(movie.getMovieName(), "Hope is a dangerous thing", rating.getMovieRating());

		}).collect(Collectors.toList());

	}

}
