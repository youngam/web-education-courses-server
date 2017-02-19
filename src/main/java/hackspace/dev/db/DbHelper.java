package hackspace.dev.db;

import com.mysql.jdbc.Driver;
import hackspace.dev.pojo.User;
import hackspace.dev.utils.HibernateUtils;
import org.hibernate.Query;
import org.hibernate.Session;

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
        return HibernateHelper.selectEntities("from User", User.class);
    }
}
