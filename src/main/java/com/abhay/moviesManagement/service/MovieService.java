package com.abhay.moviesManagement.service;

import com.abhay.moviesManagement.entity.Movie;
import com.abhay.moviesManagement.entity.User;
import com.abhay.moviesManagement.repository.MovieRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private UserService userService;


    public void addMovies(Movie movie, String userName) {
        User user = userService.findByUserName(userName);
        movie.setDate(LocalDateTime.now());
        Movie saved = movieRepository.save(movie);
        user.getMovies().add(saved);
        userService.addUser(user);

    }

    public List<Movie> getAll() {
        return movieRepository.findAll();
    }

    public Optional<Movie> findById(ObjectId ID) {
        return movieRepository.findById(ID);
    }


    public void deleteAll() {
        movieRepository.deleteAll();
    }


    public void deleteById(ObjectId id) {
        movieRepository.deleteById(id);
    }


    public Movie updateMovie(ObjectId id, Movie movie) {

        Movie existing = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found with id: " + id));

        if (movie.getName() != null && !movie.getName().isBlank()) {
            existing.setName(movie.getName());
        }

        if (movie.getGenre() != null && !movie.getGenre().isBlank()) {
            existing.setGenre(movie.getGenre());
        }

        return movieRepository.save(existing);
    }
}

