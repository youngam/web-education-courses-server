package hackspace.dev.service;

import hackspace.dev.db.HibernateHelper;
import hackspace.dev.pojo.Lesson;
import hackspace.dev.pojo.User;

import javax.persistence.Query;
import java.util.List;

import static hackspace.dev.db.HibernateHelper.createQuery;
import static hackspace.dev.db.HibernateHelper.getEntity;
import static hackspace.dev.db.HibernateHelper.updateEntity;
import static hackspace.dev.pojo.Lesson.LESSON;
import static hackspace.dev.pojo.Lesson.TITLE;

/**
 * Created by alex on 2/11/17.
 */
public class LessonsService implements ILessonsProvider{
    public static final String SELECT_LESSONS = "FROM " + LESSON;
    public static final String SELECT_LESSON_BY_TITLE = String.format("FROM %s WHERE title = :%s", LESSON, TITLE);
    public static LessonsService sInstance;

    private final UserService userService = UserService.getInstance();

    private LessonsService() {}

    public static LessonsService getInstance() {
        if(sInstance == null) sInstance = new LessonsService();
        return sInstance;
    }

    public List<Lesson> readLessons() {
        List<Lesson> lessons = HibernateHelper.selectEntities(SELECT_LESSONS, Lesson.class);
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
        return (Lesson) query.getResultList().stream().findFirst().orElse(null);
    }

    public Lesson createLesson(Lesson lesson) {

        lesson.setAuthor(HibernateHelper.getEntity(User.class, lesson.getAuthorId()));
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
        lesson.setAuthor(HibernateHelper.getEntity(User.class, lesson.getAuthorId()));
        updateEntity(lesson);
        return getEntity(Lesson.class, lesson.getId());
    }
}
