package hackspace.dev.servlets;

import com.google.gson.JsonElement;
import hackspace.dev.api.ApiMethod;
import hackspace.dev.error.ErrorFactory;
import hackspace.dev.error.ErrorType;
import hackspace.dev.pojo.BaseEntity;
import hackspace.dev.pojo.Lesson;
import hackspace.dev.pojo.RootRequest;
import hackspace.dev.service.ILessonsProvider;
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
    private ILessonsProvider lessonsProvider = LessonsService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        writeResponse(toGson(lessonsProvider.readLessons()), resp);
    }

    @Override
    protected void doPost(JsonElement requestJson, HttpServletResponse resp) throws ServletException, IOException {
        RootRequest request = getGson().fromJson(requestJson, RootRequest.class);

        switch (ApiMethod.getMethod(request.getMethodName())) {

            case CREATE_LESSON:
                createLesson(request.getRequestBody(), resp);
                break;

            case DELETE_LESSON:
                deleteLesson(request.getRequestBody(), resp);
                break;

            case UPDATE_LESSON:
                updateLesson(request.getRequestBody(), resp);
                break;

        }
    }

    private void updateLesson(JsonElement requestBody, HttpServletResponse resp) throws IOException {
        Lesson lesson = getGson().fromJson(requestBody, Lesson.class);
        String response = buildOkResponse(lessonsProvider.updateLesson(lesson));
        writeResponse(response, resp);
    }

    private void deleteLesson(JsonElement requestBody, HttpServletResponse resp) throws IOException {
        Integer lessonId = requestBody.getAsJsonObject().get(BaseEntity.ID).getAsInt();
        boolean successfulDeleted = lessonsProvider.deleteLesson(lessonId);
                                            // so lazy to create new object :(
        String response;
        if (successfulDeleted)  response = buildOkResponse(requestBody.toString());
        else response = buildErrorResponse(ErrorFactory.createError(ErrorType.CAN_NOT_DELETE_LESSON));

        writeResponse(response, resp);
    }

    private void createLesson(JsonElement requestBody, HttpServletResponse resp) throws IOException {
        Lesson lesson = getGson().fromJson(requestBody, Lesson.class);
        boolean lessonTitleIsFree = lessonsProvider.lessonTitleFree(lesson.getTitle());

        String response;
        if(lessonTitleIsFree) response = buildOkResponse(lessonsProvider.createLesson(lesson));
        else response = buildErrorResponse(ErrorFactory.createError(ErrorType.LESSON_TITLE_ALREADY_EXISTS));

        writeResponse(response, resp);
    }
}
