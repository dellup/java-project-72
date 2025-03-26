package hexlet.code.controllers;

import hexlet.code.model.Url;
import hexlet.code.model.UrlCheck;
import hexlet.code.repository.UrlCheckRepository;
import hexlet.code.repository.UrlRepository;
import hexlet.code.util.NamedRoutes;
import io.javalin.http.Context;
import kong.unirest.core.HttpResponse;
import kong.unirest.core.Unirest;
import kong.unirest.core.UnirestException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.sql.SQLException;
import java.sql.Timestamp;


public class UrlCheckController {
    public static void createCheck(Context ctx) throws SQLException {
        int id = ctx.pathParamAsClass("id", Integer.class).get();
        Url url = UrlRepository.findById(id).get();
        UrlCheck urlCheck = new UrlCheck();
        urlCheck.setUrlId(id);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        try {
            HttpResponse<String> response = Unirest.get(url.getName()).asString();
            Document doc = Jsoup.parse(response.getBody());
            int status = response.getStatus();
            String h1 = doc.selectFirst("h1") != null ? doc.selectFirst("h1").text() : "";
            String title = doc.title();
            String description = doc.selectFirst("meta[name=description]") != null
                    ? doc.selectFirst("meta[name=description]").attr("content") : "";
            urlCheck.setTitle(title);
            urlCheck.setDescription(description);
            urlCheck.setH1(h1);
            urlCheck.setStatusCode(status);
            urlCheck.setCreatedAt(timestamp);
            UrlCheckRepository.save(urlCheck);
            ctx.sessionAttribute("flash", "Страница успешно проверена");
            ctx.sessionAttribute("flashType", "success");
            ctx.redirect(NamedRoutes.urlPath(id));
        } catch (UnirestException e) {
            ctx.sessionAttribute("flash", "Некорректный URL");
            ctx.sessionAttribute("flashType", "danger");
            ctx.status(400);
            ctx.redirect(NamedRoutes.urlPath(id));
        } catch (Exception e) {
            ctx.sessionAttribute("flash", e.getMessage());
            ctx.sessionAttribute("flashType", "danger");
            ctx.status(500);
            ctx.redirect(NamedRoutes.urlPath(id));
        }
    }
}
