package ru.lanit.servlet;

import ru.lanit.exception.MappingException;
import ru.lanit.entity.Person;
import ru.lanit.mapper.request.PersonMapper;
import ru.lanit.repository.PersonRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/person")
public class PersonServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        boolean existError = false;
        try {
            Person person = PersonMapper.map(request);
            PersonRepository.save(person);
        } catch (MappingException e) {
            existError = true;
            request.setAttribute("errorMessage", e.getMessage());
        }
        if (existError) {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else {
            response.sendRedirect("address");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
