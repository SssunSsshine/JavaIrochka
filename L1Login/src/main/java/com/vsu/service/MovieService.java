package com.vsu.service;

import com.vsu.entity.Movie;
import com.vsu.repository.MovieRepository;

import java.util.List;

public class MovieService {
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie insertMovie(Movie movie) {
        if (movieRepository.insert(movie) < 1) {
            return null;
        } else {
            return movie;
        }
    }

    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }

    public Movie selectById(String id) {
        return movieRepository.selectById(Long.parseLong(id));
    }

    public List<Movie> selectAllByUserId(Long id) {
        return movieRepository.selectAllByUserId(id);
    }

    public void updateByID(Movie movie) {
        movieRepository.updateByID(movie);
    }
}
