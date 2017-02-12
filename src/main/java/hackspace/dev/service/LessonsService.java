package hackspace.dev.service;

import hackspace.dev.db.DbHelper;
import hackspace.dev.pojo.Lesson;
import hackspace.dev.pojo.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 2/11/17.
 */
public class LessonsService {
    public static final String SELECT_LESSONS = "SELECT * FROM lessons";
    public static final String SELECT_LESSON_BY_TITLE = "SELECT * FROM lessons WHERE lessons.title = '%s'";
    public static final String SELECT_LESSON_BY_ID = "SELECT * FROM lessons WHERE lessons.id = %d";
    public static final String INSERT_LESSON = "INSERT INTO lessons(title, description) VALUE ('%s', '%s');";
    public static final String DELETE_LESSON = "DELETE FROM lessons WHERE lessons.id = %d";
    public static final String UPDATE_LESSON = "UPDATE lessons SET lessons.title = '%s', " +
            "lessons.description = '%s' WHERE id = %d;";


    public List<Lesson> readLessons() {
        List<Lesson> lessons = new ArrayList<>();
        Connection connection = DbHelper.getInstance().getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SELECT_LESSONS);
            while (rs.next()) {
                lessons.add(getLesson(rs));
            }

            return lessons;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean lessonTitleFree(String title) {
        return readLesson(String.format(SELECT_LESSON_BY_TITLE, title)) == null;
    }

    private Lesson readLesson(String query) {
        Connection connection = DbHelper.getInstance().getConnection();
        Lesson lesson = null;
        try {
            Statement statement = connection.createStatement();
            System.out.println("query" + query);
            ResultSet rs = statement.executeQuery(query);
            if (rs.first()) {
                lesson = getLesson(rs);
            }
            return lesson;
        } catch (SQLException e) {
            System.out.println("Exception " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private Lesson getLesson(ResultSet rs) throws SQLException {
        int id = Integer.parseInt(rs.getString(User.ID));
        String title = rs.getString(Lesson.TITLE);
        String description = rs.getString(Lesson.DESCRIPTION);
        return new Lesson(id, title, description);
    }

    public Lesson createLesson(Lesson lesson) {
        Connection connection = DbHelper.getInstance().getConnection();

        try {
            Statement statement = connection.createStatement();
            String query = String.format(INSERT_LESSON, lesson.getTitle(), lesson.getDescription());
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        String selectUserQuery = String.format(SELECT_LESSON_BY_TITLE, lesson.getTitle());
        return readLesson(selectUserQuery);
    }

    public boolean deleteLesson(Long lessonId) {
        Connection connection = DbHelper.getInstance().getConnection();

        try {
            Statement statement = connection.createStatement();
            String query = String.format(DELETE_LESSON, lessonId);
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return true;
    }

    public Lesson updateLesson(Lesson lesson) {
        Connection connection = DbHelper.getInstance().getConnection();
        try {
            Statement statement = connection.createStatement();
            String query = String.format(UPDATE_LESSON, lesson.getTitle(), lesson.getDescription(), lesson.getId());
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        String selectUserQuery = String.format(SELECT_LESSON_BY_ID, lesson.getId());
        return readLesson(selectUserQuery);
    }
}
