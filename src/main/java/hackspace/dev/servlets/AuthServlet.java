package hackspace.dev.servlets;

import com.google.gson.Gson;
import hackspace.dev.db.DbHelper;
import hackspace.dev.pojo.User;
import hackspace.dev.service.UserService;
import hackspace.dev.utils.GsonUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static hackspace.dev.utils.GsonUtils.toGson;

@WebServlet("/getUser")
public class AuthServlet extends HttpServlet {
    private final UserService userService = new UserService();

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        List<User> users = DbHelper.getInstance().readUsers();
        String json = new Gson().toJson(users.get(0));
        response.setContentType("application/json");
        response.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = GsonUtils.getGson().fromJson(req.getReader(), User.class);
        System.out.println("user: " + user.getName() + " " + user.getPassword());

        boolean userNameFree = userService.isUserNameFree(user.getName());

        User result = null;
        if(userNameFree)  result = userService.createUser(user);
        else result = new User(-1, "userName is ", "busy");

        String response = toGson(result);
        System.out.println("Response " + response);

        resp.getWriter().write(response);
    }

}
