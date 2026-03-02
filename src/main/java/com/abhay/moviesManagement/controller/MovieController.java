package com.abhay.moviesManagement.controller;

import com.abhay.moviesManagement.entity.Movie;
import com.abhay.moviesManagement.entity.User;
import com.abhay.moviesManagement.service.MovieService;
import com.abhay.moviesManagement.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @Autowired
    private UserService userService;

    @PostMapping("/{userName}")
    public ResponseEntity<Movie> addMovies(@RequestBody Movie movie, @PathVariable String userName) {
        try {


            movieService.addMovies(movie, userName);
            return new ResponseEntity<>(movie, HttpStatus.CREATED);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{userName}")
    public ResponseEntity<?> getAllMoviesofUser(@PathVariable String userName) {

        User user = userService.findByUserName(userName);
        List<Movie> all = user.getMovies();
        if (all != null && !all.isEmpty()) {

            return new ResponseEntity<>(all, HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/id/{ID}")
    public ResponseEntity<Movie> getMovieById(@PathVariable ObjectId ID) {
        Optional<Movie> movie = movieService.findById(ID);
        if (movie.isPresent()) {
            return new ResponseEntity<>(movie.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAll() {
        movieService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @DeleteMapping("/id/{ID}")
    public ResponseEntity<?> deleteById(@PathVariable ObjectId ID) {
        movieService.deleteById(ID);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PutMapping("/id/{id}")
    public ResponseEntity<?> updateMovie(@PathVariable ObjectId id, @RequestBody Movie movie) {
        try {
            Movie updated = movieService.updateMovie(id, movie);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
