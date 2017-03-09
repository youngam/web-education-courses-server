package hackspace.dev.pojo;

import com.google.gson.annotations.SerializedName;

import javax.persistence.*;

/**
 * Created by alex on 2/2/17.
 */


@Entity
@Table(name = User.USER)
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@NamedQuery(name = User.SELECT_ALL_USERS, query = "SELECT c from User c")
public class User extends BaseEntity{
    public static final String USER = "user";
    public static final String SELECT_ALL_USERS = "User.getAll";
    public static final String NAME = "name";
    public static final String PASSWORD = "password";
    public static final String USER_TYPE_ID = "userTypeId";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = NAME)
    @SerializedName(NAME)
    private String name;

    @Column(name = PASSWORD)
    @SerializedName(PASSWORD)
    private String password;

    @Column(name = USER_TYPE_ID)
    @SerializedName(USER_TYPE_ID)
    private Integer userTypeId;

    public Integer getUserTypeId() {
        return userTypeId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserTypeId(Integer userTypeId) {
        this.userTypeId = userTypeId;
    }

    public User() {}

    public User(int id, String name, Integer userTypeId) {
        this(id, name, null, userTypeId);
    }

    public User(Integer id, String name, String password, Integer userTypeId) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.userTypeId = userTypeId;
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        return password != null ? password.equals(user.password) : user.password == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", userTypeId=" + userTypeId +
                '}';
    }
}
