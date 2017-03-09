package hackspace.dev;

import hackspace.dev.db.HibernateHelper;
import hackspace.dev.pojo.User;
import hackspace.dev.service.UserService;
import org.junit.Test;

import java.util.List;

public class UserServiceTest {
    private final UserService userService = UserService.getInstance();

    @Test
    public void testReadUsers() {
        List<User> users = HibernateHelper.selectEntities("from User", User.class);


        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testGetUser() {
        User user = userService.getUser(2);
        System.out.println(user);
    }

    @Test
    public void testAddUser() {
        HibernateHelper.saveEntity(new User(null, "admin23fasfsa4", "admin2fasffas34", 2));
        HibernateHelper.saveEntity(new User(null, "admin312fas323fsa4", "admfasin2fa3123sf34", 2));
        System.out.println(HibernateHelper.getEntity(User.class, 1));
    }

}
