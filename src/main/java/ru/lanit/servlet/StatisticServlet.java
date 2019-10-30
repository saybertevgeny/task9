package ru.lanit.servlet;

import ru.lanit.repository.PersonRepository;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/statistic")
public class StatisticServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("persons", PersonRepository.getList());
        request.getRequestDispatcher("statistic.jsp").forward(request, response);
    }
}
