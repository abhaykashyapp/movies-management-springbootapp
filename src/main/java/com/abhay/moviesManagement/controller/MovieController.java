package com.abhay.moviesManagement.controller;

import com.abhay.moviesManagement.entity.Movie;
import com.abhay.moviesManagement.service.MovieService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping
    public Movie addMovies(@RequestBody Movie movie) {
        movie.setDate(LocalDateTime.now());
        movieService.addMovies(movie);
        return movie;
    }

    @GetMapping
    public List<Movie> getMovies() {
        return movieService.getAll();
    }

    @GetMapping("/id/{ID}")
    public ResponseEntity<Movie> getMovieById(@PathVariable ObjectId ID) {
        Optional<Movie> movie = movieService.findById(ID);

        if (movie.isPresent()){

            return  new ResponseEntity<>(movie.get(), HttpStatus.OK);
        }return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping
    public void deleteAll() {
        movieService.deleteAll();
    }


    @DeleteMapping("/id/{ID}")
    public void deleteById(@PathVariable ObjectId ID) {
        movieService.deleteById(ID);
    }


    @PutMapping("/id/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable ObjectId id,
                                             @RequestBody Movie movie) {

        Movie updated = movieService.updateMovie(id, movie);
        return ResponseEntity.ok(updated);
    }
}
