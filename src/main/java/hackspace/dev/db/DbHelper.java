package hackspace.dev.db;

import com.mysql.jdbc.Driver;
import hackspace.dev.pojo.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbHelper {
    public static final DbHelper INSTANCE = new DbHelper();
    public Connection mConnection;
    private static final String URL = "jdbc:mysql://localhost/education_courses";
    private static final String USER = "root";
    private static final String PASS = "root123654789";


    public static final String SELECT_USERS = "SELECT * FROM users";
    private DbHelper() {}

    public static DbHelper getInstance() {
        return INSTANCE;
    }

    public Connection getConnection() {
        if (mConnection == null) mConnection = createConnection();
        return mConnection;
    }

    private Connection createConnection() {
        Connection connection = null;
        try {
            Driver driver = new Driver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return connection;
    }

    public List<User> readUsers() {
        List<User> users = new ArrayList<>();
        try {
            Statement statement = getConnection().createStatement();
            ResultSet rs = statement.executeQuery(SELECT_USERS);
            while (rs.next()) {
                int id = Integer.parseInt(rs.getString(User.ID));
                String name = rs.getString(User.NAME);
                int userTypeId = Integer.parseInt(rs.getString(User.USER_TYPE_ID));
                users.add(new User(id, name, userTypeId));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }
}
