package hackspace.dev.service;

import hackspace.dev.db.DbHelper;
import hackspace.dev.db.HibernateHelper;
import hackspace.dev.pojo.Lesson;
import hackspace.dev.pojo.User;
import org.hibernate.Query;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static hackspace.dev.db.HibernateHelper.createQuery;
import static hackspace.dev.db.HibernateHelper.getEntity;
import static hackspace.dev.db.HibernateHelper.updateEntity;
import static hackspace.dev.pojo.Lesson.LESSON;
import static hackspace.dev.pojo.Lesson.TITLE;

/**
 * Created by alex on 2/11/17.
 */
public class LessonsService {
    public static final String SELECT_LESSONS = "FROM " + LESSON;
    public static final String SELECT_LESSON_BY_TITLE = String.format("FROM %s WHERE title = :%s", LESSON, TITLE);
    /*public static final String INSERT_LESSON = "INSERT INTO lessons(title, description, authorId) VALUE ('%s', '%s', '%d');";
    public static final String DELETE_LESSON = "DELETE FROM lessons WHERE lessons.id = %d";
    public static final String UPDATE_LESSON = "UPDATE lessons SET lessons.title = '%s', " +
            "lessons.description = '%s' WHERE id = %d;";*/

    private final UserService userService = new UserService();

    public List<Lesson> readLessons() {
        List<Lesson> lessons = HibernateHelper.selectEntities(SELECT_LESSONS, Lesson.class);

        //TODO remove after investigating map entities between tables
        for (Lesson lesson : lessons) {
            addAuthor(lesson);
        }

        return lessons;
    }

    private void addAuthor(Lesson lesson) {
        User author = userService.getUser(lesson.getAuthorId());
        lesson.setAuthor(author);
    }

    public boolean lessonTitleFree(String title) {
        Query query = createQuery(SELECT_LESSON_BY_TITLE)
                    .setParameter(TITLE, title);

        return readLesson(query) == null;
    }

    private Lesson readLesson(Query query) {
        return (Lesson) query.uniqueResult();
    }

    public Lesson createLesson(Lesson lesson) {
        HibernateHelper.saveEntity(lesson);

        Query query = createQuery(SELECT_LESSON_BY_TITLE)
                        .setParameter(TITLE, lesson.getTitle());
        return readLesson(query);
    }

    public boolean deleteLesson(Integer lessonId) {
        HibernateHelper.deleteEntity(Lesson.class, lessonId);
        return true;
    }

    public Lesson updateLesson(Lesson lesson) {
        updateEntity(lesson);
        return getEntity(Lesson.class, lesson.getId());
    }
}
