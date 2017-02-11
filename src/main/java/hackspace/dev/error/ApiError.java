package hackspace.dev.error;

import com.google.gson.annotations.SerializedName;

public class ApiError {
    public static final String ERROR_MESSAGE = "errorMessage";

    @SerializedName(ERROR_MESSAGE)
    private String errorMessage;

    public ApiError(ErrorType errorType) {
        errorMessage = errorType.getErrorMessage();
    }

}
