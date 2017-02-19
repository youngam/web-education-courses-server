package hackspace.dev.servlets;

import hackspace.dev.db.DbHelper;
import hackspace.dev.pojo.User;
import hackspace.dev.utils.GsonUtils;
import hackspace.dev.utils.HibernateUtils;
import org.hibernate.Session;

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

        Session session = HibernateUtils.getSessionFactory().openSession();

        session.beginTransaction();
        User user = new User(20, "User", "Popovich", 1);
        session.update(user);
        session.getTransaction().commit();

        List<User> users = DbHelper.getInstance().readUsers();
        response.getWriter().write(GsonUtils.toGson(users));
    }
}
