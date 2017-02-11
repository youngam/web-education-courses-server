package hackspace.dev.servlets;

import hackspace.dev.db.DbHelper;
import hackspace.dev.pojo.Product;
import hackspace.dev.pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet("/main")
public class MainServlet extends HttpServlet {
    public static final String PRODUCT = "product";
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        List<Product> products = Arrays.asList(new Product("testProduct", 150),
                new Product("testProduct2", 250),
                new Product("testProduct3", 350),
                new Product("testProduct4", 450),
                new Product("testProduct5", 550));

        List<User> users = DbHelper.getInstance().readUsers();

        request.getSession().setAttribute(PRODUCT, products);
        request.getRequestDispatcher("main.jsp").forward(request, response);
    }
}
