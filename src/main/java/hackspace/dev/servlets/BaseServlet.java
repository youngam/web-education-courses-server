package hackspace.dev.servlets;

import com.google.gson.JsonElement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static hackspace.dev.utils.GsonUtils.getGson;

public abstract class BaseServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(getJsonRequest(req), resp);
    }

    private JsonElement getJsonRequest(HttpServletRequest req) throws IOException {
        JsonElement requestJson = getGson().fromJson(req.getReader(), JsonElement.class);
        System.out.println("request: " + requestJson);
        return requestJson;
    }

    protected abstract void doPost(JsonElement requestJson, HttpServletResponse resp) throws ServletException, IOException;
}
