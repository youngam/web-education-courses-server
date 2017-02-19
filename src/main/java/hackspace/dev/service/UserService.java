package hackspace.dev.service;

import hackspace.dev.db.DbHelper;
import hackspace.dev.db.HibernateHelper;
import hackspace.dev.pojo.User;
import hackspace.dev.utils.HibernateUtils;
import org.hibernate.Query;
import org.hibernate.Session;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static hackspace.dev.db.HibernateHelper.createQuery;

public class UserService {
    private final Connection connection = DbHelper.getInstance().getConnection();
    public static final String SELECT_USER_BY_ID = "SELECT * FROM users WHERE users.id = '%d'";

    public static final String SELECT_USER_BY_NAME = String.format("FROM User WHERE name = :%s",
                                                                    User.NAME);

    public static final String SELECT_USER_BY_NAME_AND_PASS =
            String.format("FROM User WHERE name = :%s AND password = :%s",
                    User.NAME, User.PASSWORD);

    public static final String INSERT_USER = "INSERT INTO users(name, password, userTypeId) VALUES('%s', '%s', '%d')";


    public boolean isUserNameFree(String userName) {
        Query query = createQuery(SELECT_USER_BY_NAME)
                .setParameter(User.NAME, userName);

        return readUser(query) == null;
    }

    public User createUser(User user) {
        HibernateHelper.saveEntity(user);

        Query query = createQuery(SELECT_USER_BY_NAME)
                .setParameter(User.NAME, user.getName());

        return readUser(query);
    }

    public User readUser(Query query) {
        return (User) query.uniqueResult();

    }

    public User selectUser(User user) {
        Query query = createQuery(SELECT_USER_BY_NAME_AND_PASS)
                .setParameter(User.NAME, user.getName())
                .setParameter(User.PASSWORD, user.getPassword());

        return readUser(query);
    }

    public User getUser(int id) {
        return HibernateHelper.getEntity(User.class, id);
    }
}
