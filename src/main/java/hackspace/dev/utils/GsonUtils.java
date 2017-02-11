package hackspace.dev.utils;

import com.google.gson.Gson;

public final class GsonUtils {
    private final static Gson GSON = new Gson();

    public static Gson getGson() {
        return GSON;
    }

    public static String toGson(Object object) {
        return GSON.toJson(object);
    }
}
