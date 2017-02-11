package hackspace.dev.pojo;

/**
 * Created by alex on 2/2/17.
 */
public class Lesson {
    private String title;
    private String description;
    private String author;

    public Lesson(String title, String description, String author) {

        this.title = title;
        this.description = description;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lesson lesson = (Lesson) o;

        if (title != null ? !title.equals(lesson.title) : lesson.title != null) return false;
        if (description != null ? !description.equals(lesson.description) : lesson.description != null) return false;
        return author != null ? author.equals(lesson.author) : lesson.author == null;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        return result;
    }
}
