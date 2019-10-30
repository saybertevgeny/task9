package ru.lanit.servlet;

import ru.lanit.entity.Address;
import ru.lanit.entity.Person;
import ru.lanit.exception.MappingException;
import ru.lanit.exception.NotFoundEntityException;
import ru.lanit.mapper.request.AddressMapper;
import ru.lanit.repository.AddressRepository;
import ru.lanit.repository.PersonRepository;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/address")
public class AddressServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        boolean existError = false;
        try {
            Person person = PersonRepository.getById(Integer.valueOf(request.getParameter("person")));
            Address address = AddressMapper.map(request);
            address.setPerson(person);
            AddressRepository.save(address);
        } catch (NotFoundEntityException e) {
            existError = true;
            request.setAttribute("errorMessage", "Пользователь не найден");
        } catch (MappingException e) {
            existError = true;
            request.setAttribute("errorMessage", e.getMessage());
        }

        if (existError) {
            request.getRequestDispatcher("address.jsp").forward(request, response);
        } else {
            response.sendRedirect("statistic");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("persons", PersonRepository.getList(false));
        request.getRequestDispatcher("address.jsp").forward(request, response);
    }
}
