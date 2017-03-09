package hackspace.dev.error;

import com.google.gson.annotations.SerializedName;

public class ErrorFactory {
    public static final ApiError createError(ErrorType errorType) {
        return new ApiError(errorType);
    }

}
