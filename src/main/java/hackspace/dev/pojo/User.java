package hackspace.dev.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by alex on 2/2/17.
 */
public class User extends BaseEntity{
    public static final String NAME = "name";
    public static final String PASSWORD = "password";

    private int id;

    @SerializedName(NAME)
    private String name;

    @SerializedName(PASSWORD)
    private String password;

    public User() {}

    public User(int id, String name) {
        this(id, name, null);
    }

    public User(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
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
}
