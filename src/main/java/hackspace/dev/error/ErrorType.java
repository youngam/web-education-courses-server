package hackspace.dev.error;

public enum ErrorType {
    USER_NAME_BUSY("User name is busy, try another one"),
    INCORRECT_LOGIN_OR_PASS("Incorrect login or password"),
    LESSON_TITLE_ALREADY_EXISTS("Lesson title already exists. Try another one"),
            ;


    String errorMessage;

    ErrorType(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
