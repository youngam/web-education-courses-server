package hackspace.dev.servlets;

import com.google.gson.JsonElement;
import hackspace.dev.api.ApiMethod;
import hackspace.dev.error.ApiError;
import hackspace.dev.error.ErrorType;
import hackspace.dev.pojo.Lesson;
import hackspace.dev.pojo.RootRequest;
import hackspace.dev.service.LessonsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static hackspace.dev.utils.GsonUtils.getGson;
import static hackspace.dev.utils.GsonUtils.toGson;
import static hackspace.dev.utils.ResponseHelper.buildErrorResponse;
import static hackspace.dev.utils.ResponseHelper.buildOkResponse;
import static hackspace.dev.utils.ResponseHelper.writeResponse;

/**
 * Created by alex on 2/11/17.
 */
@WebServlet("/lessons")
public class LessonsServlet extends BaseServlet {
    private LessonsService lessonsService = new LessonsService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        writeResponse(toGson(lessonsService.readLessons()), resp);
    }

    @Override
    protected void doPost(JsonElement requestJson, HttpServletResponse resp) throws ServletException, IOException {
        RootRequest request = getGson().fromJson(requestJson, RootRequest.class);

        switch (ApiMethod.getMethod(request.getMethodName())) {

            case CREATE_LESSON:
                createLesson(request.getRequestBody(), resp);
        }
    }

    private void createLesson(JsonElement requestBody, HttpServletResponse resp) throws IOException {
        Lesson lesson = getGson().fromJson(requestBody, Lesson.class);
        boolean lessonTitleIsFree = lessonsService.lessonTitleFree(lesson.getTitle());

        String response;
        if(lessonTitleIsFree) response = buildOkResponse(lessonsService.createLesson(lesson));
        else response = buildErrorResponse(new ApiError(ErrorType.LESSON_TITLE_ALREADY_EXISTS));

        writeResponse(response, resp);
    }
}
