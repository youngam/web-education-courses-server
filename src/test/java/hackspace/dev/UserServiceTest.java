package hackspace.dev;

import hackspace.dev.db.DbHelper;
import hackspace.dev.pojo.User;
import hackspace.dev.service.UserService;
import org.junit.Test;

import java.util.List;

public class UserServiceTest {
    private final UserService userService = new UserService();

    @Test
    public void testReadUsers() {
        List<User> users = DbHelper.getInstance().readUsers();

        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testGetUser() {
        User user = userService.getUser(2);
        System.out.println(user);
    }

}
