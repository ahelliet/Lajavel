package lajavel;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import lajavel.enums.Mode;

public class Application {

    private static Application instance;
    public Javalin server;
    public int port;
    public Mode mode;

    private Application(int port, Mode mode) {
        this.port = port;
        this.server = Javalin.create(config -> {
            config.addStaticFiles("/public", Location.CLASSPATH);
        }).start(port);
        this.mode = mode;
    }

    public static void start (int port) {
        start(port, Mode.DEVELOPMENT);
    }

    public static Application start(int port, Mode mode) {

        if(instance == null) {
            instance = new Application(port, mode);
        } else {
            throw  new RuntimeException();
        }
        return instance;
    }

    public static Application getInstance() {
        if(instance == null) {
            throw new RuntimeException("Application not started");
        }
        return instance;
    }
}
