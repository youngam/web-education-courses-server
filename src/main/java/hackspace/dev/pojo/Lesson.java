package hackspace.dev.pojo;

import javax.persistence.*;

/**
 * Created by alex on 2/2/17.
 */

@Entity
@Table(name = Lesson.LESSON)
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class Lesson extends BaseEntity{
    public static final String LESSON = "Lesson";

    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";
    public static final String AUTHOR = "author";
    public static final String AUTHOR_ID = "authorId";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = TITLE)
    private String title;

    @Column(name = DESCRIPTION)
    private String description;

    @Transient
    private Integer authorId;

    @OneToOne (cascade=CascadeType.ALL)
    @JoinColumn(name=AUTHOR_ID, unique = false)
    private User author;

    public Lesson(int id, String title, String description, Integer authorId) {
        this(id, title, description, authorId, null);
    }

    public Lesson(int id, String title, String description, User author) {
        this(id, title, description, null, author);
    }

    public Lesson() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public void setAuthor(User author) {
        this.author = author;
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

    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", authorId=" + authorId +
                ", author=" + author +
                '}';
    }
}
