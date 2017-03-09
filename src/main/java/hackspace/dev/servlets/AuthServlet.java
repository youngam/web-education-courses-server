package hackspace.dev.servlets;

import com.google.gson.JsonElement;
import hackspace.dev.api.ApiMethod;
import hackspace.dev.error.ApiError;
import hackspace.dev.error.ErrorFactory;
import hackspace.dev.pojo.RootRequest;
import hackspace.dev.pojo.User;
import hackspace.dev.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static hackspace.dev.error.ErrorType.INCORRECT_LOGIN_OR_PASS;
import static hackspace.dev.error.ErrorType.USER_NAME_BUSY;
import static hackspace.dev.utils.GsonUtils.getGson;
import static hackspace.dev.utils.GsonUtils.toGson;
import static hackspace.dev.utils.ResponseHelper.buildErrorResponse;
import static hackspace.dev.utils.ResponseHelper.buildOkResponse;
import static hackspace.dev.utils.ResponseHelper.writeResponse;

@WebServlet("/auth")
public class AuthServlet extends BaseServlet {
    private final UserService userService = UserService.getInstance();

    @Override
    protected void doPost(JsonElement requestJson, HttpServletResponse resp) throws ServletException, IOException {
        RootRequest request = getGson().fromJson(requestJson, RootRequest.class);

        switch (ApiMethod.getMethod(request.getMethodName())) {

            case SIGN_IN :
                signInUser(request.getRequestBody(), resp);
                break;
            case SIGN_UP:
                signUpUser(request.getRequestBody(), resp);
                break;
        }

    }

    private void signInUser(JsonElement request, HttpServletResponse resp) throws IOException {
        User user = getGson().fromJson(request, User.class);
        User userFromDb = userService.selectUser(user);

        String response;
        if(userFromDb != null) response = buildOkResponse(toGson(userFromDb));
        else response = buildErrorResponse(toGson(ErrorFactory.createError(INCORRECT_LOGIN_OR_PASS)));

        writeResponse(response, resp);
    }

    private void signUpUser(JsonElement request, HttpServletResponse resp) throws IOException {
        User user = getGson().fromJson(request, User.class);
        boolean userNameFree = userService.isUserNameFree(user.getName());

        String response;

        if(userNameFree)  response = buildOkResponse(toGson(userService.createUser(user)));
        else response = buildErrorResponse(toGson(ErrorFactory.createError(USER_NAME_BUSY)));

        writeResponse(response, resp);
    }

}
