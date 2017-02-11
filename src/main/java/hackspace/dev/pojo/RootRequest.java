package hackspace.dev.pojo;

import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;

/**
 * Created by alex on 2/11/17.
 */
public class RootRequest {
    public static final String METHOD_NAME = "methodName";
    public static final String REQUEST = "request";

    @SerializedName(METHOD_NAME)
    private String methodName;

    @SerializedName(REQUEST)
    private JsonElement requestBody;

    public String getMethodName() {
        return methodName;
    }

    public JsonElement getRequestBody() {
        return requestBody;
    }
}
