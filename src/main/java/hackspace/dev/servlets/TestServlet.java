package hackspace.dev.servlets;

import com.google.gson.Gson;
import hackspace.dev.pojo.PersonData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/test")
public class TestServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        PersonData personData = new PersonData("Mohaideen", "Jamil");

        String json = new Gson().toJson(personData);
        response.setContentType("application/json");
        response.getWriter().write(json);
    }
}
