package hexlet.code;

import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinJte;


public class App {
    public static void main(String[] args) {
        var app = getApp();
        app.start(getPort());
    }
    private static int getPort() {
        String port = System.getenv().getOrDefault("PORT", "7060");
        return Integer.valueOf(port);
    }

    public static Javalin getApp() {
        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        app.before(ctx -> {
            ctx.contentType("text/html; charset=utf-8");
        });

        app.get("/", ctx -> {
           ctx.result("Hello, World");
        });

        return app;
    }
}