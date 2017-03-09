package hackspace.dev.service;

import hackspace.dev.pojo.Lesson;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by alex on 3/9/17.
 */
public interface ILessonsProvider {
    Lesson updateLesson(Lesson lesson);

    Lesson createLesson(Lesson lesson);

    List<Lesson> readLessons();

    boolean lessonTitleFree(String title);

    boolean deleteLesson(Integer lessonId);


}