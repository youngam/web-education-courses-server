package hackspace.dev.servlets;

import hackspace.dev.db.HibernateHelper;
import hackspace.dev.pojo.User;
import hackspace.dev.utils.GsonUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/test")
public class TestServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        List<User> users = HibernateHelper.selectEntities("from User", User.class);
        response.getWriter().write(GsonUtils.toGson(users));
    }
}
