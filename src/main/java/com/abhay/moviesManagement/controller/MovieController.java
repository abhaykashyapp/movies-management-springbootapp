package com.abhay.moviesManagement.controller;

import com.abhay.moviesManagement.entity.Movie;
import com.abhay.moviesManagement.service.MovieService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Movie getMovieById(@PathVariable ObjectId ID) {
        return movieService.findById(ID).orElse(null);
    }

    @DeleteMapping
    public void deleteAll() {
        movieService.deleteAll();
    }


    @DeleteMapping("/id/{ID}")
    public void deleteById(@PathVariable ObjectId ID) {
        movieService.deleteById(ID);
    }




    @PutMapping("/id/{ID}")
    public Movie updateMovies(@PathVariable ObjectId ID,@RequestBody Movie movie) {
        Movie old = movieService.findById(ID    ).orElse(null);
        if (old!=null){
            old.setName(movie.getName()!= null && !movie.getName().equals("") ? movie.getName(): old.getName());
            old.setGenre(movie.getGenre()!= null && !movie.getGenre().equals("") ? movie.getGenre(): old.getGenre());

        }

movieService.addMovies(old);
        return old;
    }





}
