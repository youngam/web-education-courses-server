package hackspace.dev;

import hackspace.dev.db.HibernateHelper;
import hackspace.dev.pojo.Lesson;
import hackspace.dev.service.LessonsService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LessonServiceTest {
    private final LessonsService lessonsService = new LessonsService();

    @Test
    public void testReadLessons() {

        for (Lesson lesson : lessonsService.readLessons()) {
            System.out.println(lesson);
        }
    }

    @Before
    void setUp() {

    }

    @After
    void tearDown() {

    }

    @Test
    public void testCreateLesson() {
        Lesson lesson = new Lesson(4, "test lesson4 ", "test description", 1);
        lessonsService.createLesson(lesson);

        System.out.println(HibernateHelper.getEntity(Lesson.class, 4));
    }

    @Test
    public void testDeleteLesson() {
        lessonsService.deleteLesson(3);
        Assert.assertNull(HibernateHelper.getEntity(Lesson.class, 3));
    }

}
