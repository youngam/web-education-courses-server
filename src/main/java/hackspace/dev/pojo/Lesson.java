package hackspace.dev.pojo;

import sun.java2d.cmm.Profile;

/**
 * Created by alex on 2/2/17.
 */
public class Lesson extends BaseEntity{
    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";
    public static final String AUTHOR = "author";
    public static final String AUTHOR_ID = "authorId";

    private int id;
    private String title;
    private String description;
    private Integer authorId;
    private User author;

    public Lesson(int id, String title, String description, Integer authorId) {
        this(id, title, description, authorId, null);
    }

    public Lesson(int id, String title, String description, User author) {
        this(id, title, description, null, author);
    }

    public Lesson(int id, String title, String description, Integer authorId, User author) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.authorId = authorId;
        this.author = author;
    }

    public Lesson(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lesson lesson = (Lesson) o;

        if (title != null ? !title.equals(lesson.title) : lesson.title != null) return false;
        return  description != null ? !description.equals(lesson.description) : lesson.description != null;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
