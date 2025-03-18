package hexlet.code.controllers;

import hexlet.code.dto.UrlPage;
import hexlet.code.model.Url;
import hexlet.code.repository.UrlRepository;
import io.javalin.http.Context;

import java.sql.SQLException;

import static io.javalin.rendering.template.TemplateUtil.model;

public class UrlController {
    public static void index(Context ctx) throws SQLException {
        int id = ctx.pathParamAsClass("id", Integer.class).get();
        Url url = UrlRepository.findById(id).get();
        UrlPage page = new UrlPage(url);
        ctx.render("url/index.jte", model("page", page));
    }
}
