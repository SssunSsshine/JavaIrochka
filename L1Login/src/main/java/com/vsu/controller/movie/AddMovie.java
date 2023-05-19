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

@WebServlet("/movie/add")
public class AddMovie extends HttpServlet {private static final String JSP_PATH = "/jsp/";
    private MovieService movieService;

    @Override
    public void init(ServletConfig config) {
        movieService = new MovieService(new MovieRepository(new ConnectionFactory()));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String title = req.getParameter("title");
        String release = req.getParameter("release");
        User user = (User) session.getAttribute("user");
        try {
            movieService.insertMovie(new Movie(Integer.parseInt(release), title, user.getId()));
        } catch (Exception e) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/movie/form");
            req.setAttribute("error", e.toString());
            dispatcher.forward(req, resp);
            return;
        }
        resp.sendRedirect(req.getContextPath() + "/user/page");
    }


}
