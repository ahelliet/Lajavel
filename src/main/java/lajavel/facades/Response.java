package lajavel.facades;

import io.javalin.http.Context;

import javax.servlet.http.HttpServletRequest;

public class Response {

    private final Context context;
    public final HttpServletRequest request;

    public Response(Context context) {
        this.context = context;
        this.request = context.req;
    }

    public void html (String html) {
        this.context.html(html);
    }
}
