package lajavel;

import io.javalin.http.Context;
import lajavel.enums.HttpVerb;
import lajavel.facades.Response;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Route {

    public static void invokeController(Context context, Class<?> controllerClass, String methodName) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        Response response = new Response(context);

        Controller controller = (Controller) controllerClass.getDeclaredConstructor().newInstance();
        Method method = controllerClass.getMethod(methodName, Response.class);
        method.invoke(controller, response);

    }
    public static void register(HttpVerb verb, String routeName, Class<?> controllerClass, String methodName) {
        Application app = Application.getInstance();

        switch (verb) {
            case GET:
                app.server.get(routeName, response ->
                    invokeController(response, controllerClass, methodName));
                break;
            case POST:
                app.server.post(routeName, response ->
                        invokeController(response, controllerClass, methodName));
                break;
            case UPDATE:
                app.server.patch(routeName, response ->
                        invokeController(response, controllerClass, methodName));
                break;
            case DELETE:
                app.server.delete(routeName, response ->
                        invokeController(response, controllerClass, methodName));
                break;
            }
        }
    }

