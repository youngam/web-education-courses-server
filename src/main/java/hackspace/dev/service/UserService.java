package hackspace.dev.service;

import hackspace.dev.db.HibernateHelper;
import hackspace.dev.pojo.User;

import javax.persistence.Query;
import java.sql.Connection;

import static hackspace.dev.db.HibernateHelper.createQuery;

public class UserService {
    public static UserService sInstance;
    public static final String SELECT_USER_BY_ID = "SELECT * FROM users WHERE users.id = '%d'";

    public static final String SELECT_USER_BY_NAME = String.format("FROM User WHERE name = :%s",
                                                                    User.NAME);

    public static final String SELECT_USER_BY_NAME_AND_PASS =
            String.format("FROM User WHERE name = :%s AND password = :%s",
                    User.NAME, User.PASSWORD);

    public static final String INSERT_USER = "INSERT INTO users(name, password, userTypeId) VALUES('%s', '%s', '%d')";



    private UserService() {}

    public static UserService getInstance() {
        if(sInstance == null) sInstance = new UserService();
        return sInstance;
    }

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
        return query.getResultList().isEmpty() ? null : (User) query.getResultList().get(0);

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
