package com.vsu.controller.movie;

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

import java.io.IOException;

@WebServlet("/movie/delete")
public class DeleteMovie extends HttpServlet {
    private static final String JSP_PATH = "/jsp/";
    private MovieService movieService;

    @Override
    public void init(ServletConfig config) {
        movieService = new MovieService(new MovieRepository(new ConnectionFactory()));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        try {
            movieService.deleteMovie(Long.parseLong(id));
        } catch (Exception e) {
            RequestDispatcher dispatcher = req.getRequestDispatcher(JSP_PATH + "movie-form.jsp");
            req.setAttribute("error", e.toString());
            dispatcher.forward(req, resp);
            return;
        }
        resp.sendRedirect(req.getContextPath() + "/movie/all");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}

