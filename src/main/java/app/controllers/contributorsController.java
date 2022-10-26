package app.controllers;

import app.entities.Contributor;
import lajavel.Controller;
import lajavel.View;
import lajavel.facades.Response;

import java.util.Map;

public class contributorsController extends Controller {
    public static void index (Response response) {
        response.html(View.render("contributors",
                Map.entry("tata", new Contributor("John", "Doe")),
                Map.entry("toto", new Contributor("Jane", "Doe"))
                ));
    }
}