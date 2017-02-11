package hackspace.dev.service;

import hackspace.dev.db.DbHelper;
import hackspace.dev.pojo.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserService {
    private final Connection connection = DbHelper.getInstance().getConnection();
    public static final String SELECT_USER_BY_NAME = "SELECT * FROM users WHERE users.name = '%s'";
    public static final String SELECT_USER_BY_NAME_AND_PASS =
            "SELECT * FROM users WHERE users.name = '%s' AND users.password = '%s'";
    public static final String INSERT_USER = "INSERT INTO users(name, password) VALUES('%s', '%s')";


    public boolean isUserNameFree(String userName) {
        String query = String.format(SELECT_USER_BY_NAME, userName);
        return readUser(query) == null;
    }

    public User createUser(User user) {
        try {
            Statement statement = connection.createStatement();
            String query = String.format(INSERT_USER, user.getName(), user.getPassword());
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        String selectUserQuery = String.format(SELECT_USER_BY_NAME, user.getName());
        return readUser(selectUserQuery);
    }

    private User readUser(String query) {
        User user = null;
        try {
            Statement statement = connection.createStatement();
            System.out.println("query" + query);
            ResultSet rs = statement.executeQuery(query);
            if(rs.first()) {
                int id = Integer.parseInt(rs.getString(User.ID));
                String name = rs.getString(User.NAME);
                user = new User(id, name);
            }
            return user;
        } catch (SQLException e) {
            System.out.println("Exception " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public boolean isSignInSuccess(User user) {
        return false;
    }
}
