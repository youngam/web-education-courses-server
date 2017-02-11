package hackspace.dev.pojo;

/**
 * Created by alex on 2/3/17.
 */
public class PersonData {
    private String firstName;
    private String secondName;

    public PersonData(String firstName, String secondName) {
        this.firstName = firstName;
        this.secondName = secondName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public PersonData() {}

}
