package com.bmdb.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import com.bmdb.business.Movie;
import com.bmdb.db.MovieRepository;

@CrossOrigin
@RestController
@RequestMapping("/movies")
public class MovieController {
	@Autowired
	MovieRepository movieRepo;

	// @GetMapping("/")
	// public Iterable<Movie> listMovies() {
	// return movieRepo.findAll();
	// }

	// Return all movie with logging
	@GetMapping("/")
	public JsonResponse listMovies() {
		JsonResponse jr = null;
		try {
			jr = JsonResponse.getInstance(movieRepo.findAll());

		} catch (Exception e) {
			jr = JsonResponse.getInstance(e.getMessage());
		}
		return jr;
	}

	@GetMapping("/{id}")
	public JsonResponse getMovie(@PathVariable int id) {
		JsonResponse jr = null;
		try {
			jr = JsonResponse.getInstance(movieRepo.findById(id));
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e.getMessage());
		}

		return jr;
	}
	@GetMapping("/list-movies-for-rating")
	public JsonResponse  listMoviesFromRating ( @RequestParam String rating) {
		JsonResponse jr = null;
		try {
			jr = JsonResponse.getInstance(movieRepo.findByRating(rating));
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e.getMessage());
			e.printStackTrace();
		}

		return jr;
	}
	
	

	@PostMapping("/")
	public JsonResponse addMovie(@RequestBody Movie m) {
		JsonResponse jr = null;
		try {
			jr = JsonResponse.getInstance(movieRepo.save(m));
		} catch (DataIntegrityViolationException dive) {
			jr = JsonResponse.getInstance(dive.getRootCause().getMessage());
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e.getMessage());
		}

		return jr;
	}

	@PutMapping("/")
	public JsonResponse updateMovie(@RequestBody Movie m) {
		JsonResponse jr = null;
		try {
			if (movieRepo.existsById(m.getId())) {
				jr = JsonResponse.getInstance(movieRepo.save(m));
			} else {
				jr = JsonResponse.getInstance("error updating movie id: " + m.getId() + " doesn't exist");
			}
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e.getMessage());
		}

		return jr;
	}

	@DeleteMapping("/{id}")
	public JsonResponse deleteMovie(@PathVariable int id) {
		JsonResponse jr = null;
		try {
			if (movieRepo.existsById(id)) {
				movieRepo.deleteById(id);
				jr = JsonResponse.getInstance("Delete successful");
			} else {
				jr = JsonResponse.getInstance("error deleting movie id: " + id + " doesn't exist");
			}
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e.getMessage());
		}

		return jr;
	}

}
