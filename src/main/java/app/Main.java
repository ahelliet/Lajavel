package app;

import app.controllers.contributorsController;
import app.controllers.getStartedController;
import app.controllers.indexController;
import lajavel.*;
import lajavel.enums.HttpVerb;
import lajavel.enums.Mode;

public class Main {
    public static void main(String[] args) {
        Application app = Application.start(7070, Mode.DEVELOPMENT);

/*      Log.info("info");
        Log.warn("warn");
        Log.debug("debug");
        Log.error("error");*/

        Route.register(HttpVerb.GET, "/", indexController.class, "index");
        Route.register(HttpVerb.GET, "/get-started", getStartedController.class, "index");
        Route.register(HttpVerb.GET, "/contributors", contributorsController.class, "index");
    }
}