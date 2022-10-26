package app.controllers;

import lajavel.Controller;
import lajavel.View;
import lajavel.facades.Response;

public class getStartedController extends Controller {

    public static void index (Response response) {
        response.html(View.render("get-started"));
    }
}
