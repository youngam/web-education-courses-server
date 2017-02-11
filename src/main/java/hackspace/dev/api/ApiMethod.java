package hackspace.dev.api;

/**
 * Created by alex on 2/11/17.
 */
public enum  ApiMethod {
    SIGN_IN("signIn"),
    SIGN_UP("signUp")
    ;

    private String name;

    ApiMethod(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static ApiMethod getMethod(String name) {
        for (ApiMethod apiMethod : values()) {
            if(apiMethod.getName().equals(name)) return apiMethod;
        }

        throw new IllegalArgumentException("unknown methodName " + name);
    }
}
