package hackspace.dev.utils;

import com.google.gson.JsonElement;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by alex on 2/11/17.
 */
public final class ResponseHelper {
    private ResponseHelper() {}

    public static String buildOkResponse(Object object) {
        return buildOkResponse(GsonUtils.toGson(object));
    }

    public static String buildOkResponse(String rootResponse) {
        //TODO add object like response or etc
        return rootResponse;
    }

    public static String buildErrorResponse(Object object) {
        return buildErrorResponse(GsonUtils.toGson(object));
    }

    public static String buildErrorResponse(String rootResponse) {
        //TODO add object like response or etc
        return rootResponse;
    }

    public static void writeResponse(String response, HttpServletResponse resp) throws IOException {
        System.out.println("Response " + response);
        resp.getWriter().write(response);
    }
}
