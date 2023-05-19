package com.vsu.repository;

import com.vsu.entity.Movie;
import com.vsu.exception.DBException;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieRepository {
    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM movie WHERE id_movie = ?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM movie WHERE id_profile = ?";
    private static final String INSERT_QUERY = "INSERT INTO movie(title, release_year, id_profile) VALUES (?, ?, ?)";
    private static final String DELETE_QUERY_BY_ID = "DELETE FROM movie WHERE id_movie = ?";
    private static final String UPDATE_QUERY = "UPDATE movie SET title=?, release_year=?, id_profile=? WHERE id_movie = ?";
    private final ConnectionFactory connectionFactory;

    public MovieRepository(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public Movie selectById(Long id) {
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_QUERY);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getMovie(resultSet);
            }
            return null;
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public List<Movie> selectAllByUserId(Long idUser) {
        try (Connection connection = connectionFactory.getConnection()) {
            List<Movie> movies = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_QUERY);
            statement.setLong(1, idUser);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                movies.add(getMovie(resultSet));
            }
            return movies;
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public int insert(Movie movie) {
        int countUpdate = 0;
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(INSERT_QUERY);
            setMovieParamsToStatement(movie, statement);
            countUpdate = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e);
        }
        return countUpdate;
    }

    public int deleteById(Long id) {
        int countUpdate = 0;
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DELETE_QUERY_BY_ID);
            statement.setLong(1, id);
            countUpdate = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e);
        }
        return countUpdate;
    }

    public int updateByID(Movie movie) {
        int countUpdate = 0;
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY);
            setMovieParamsToStatement(movie, statement);
            statement.setLong(4, movie.getId());
            countUpdate = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e);
        }
        return countUpdate;
    }

    private void setMovieParamsToStatement(Movie movie, PreparedStatement statement) throws SQLException {
        statement.setString(1, movie.getTitle());
        statement.setInt(2, movie.getReleaseYear());
        statement.setLong(3, movie.getIdProfile());
    }

    @NotNull
    private Movie getMovie(ResultSet resultSet) throws SQLException {
        Movie movie = new Movie();
        movie.setId(resultSet.getLong("id_movie"));
        movie.setTitle(resultSet.getString("title"));
        movie.setReleaseYear(resultSet.getInt("release_year"));
        movie.setIdProfile(resultSet.getLong("id_profile"));
        return movie;
    }
}
