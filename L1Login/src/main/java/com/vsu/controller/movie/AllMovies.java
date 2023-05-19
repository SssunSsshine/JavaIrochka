package com.vsu.controller.movie;

import com.vsu.entity.Movie;
import com.vsu.entity.User;
import com.vsu.repository.ConnectionFactory;
import com.vsu.repository.MovieRepository;
import com.vsu.service.MovieService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/movie/all")
public class AllMovies extends HttpServlet {
    public static final String JSP_PATH = "/jsp/";
    private MovieService movieService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        movieService = new MovieService(new MovieRepository(new ConnectionFactory()));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        List<Movie> movieList = movieService.selectAllByUserId(user.getId());
        RequestDispatcher dispatcher = req.getRequestDispatcher(JSP_PATH + "movies.jsp");
        req.setAttribute("movies", movieList);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}

