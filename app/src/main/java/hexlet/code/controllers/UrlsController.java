package hexlet.code.controllers;

import hexlet.code.dto.UrlsPage;
import hexlet.code.model.Url;
import hexlet.code.model.UrlCheck;
import hexlet.code.repository.UrlCheckRepository;
import hexlet.code.util.NamedRoutes;
import io.javalin.http.Context;
import hexlet.code.repository.UrlRepository;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.javalin.rendering.template.TemplateUtil.model;

public class UrlsController {
    public static void createUrl(Context ctx) throws SQLException {
        var input = ctx.formParam("url");

        if (input == null || input.trim().isEmpty()) {
            ctx.sessionAttribute("flash", "Некорректный URL");
            ctx.sessionAttribute("flashType", "danger");
            ctx.redirect(NamedRoutes.rootPath());
            return;
        }

        URL parsedUrl;
        try {
            parsedUrl = new URI(input.trim()).toURL();
        } catch (URISyntaxException | MalformedURLException | NullPointerException | IllegalArgumentException e) {
            ctx.sessionAttribute("flash", "Некорректный URL");
            ctx.sessionAttribute("flashType", "danger");
            ctx.redirect(NamedRoutes.rootPath());
            return;
        }

        String normalizedUrl = String
                .format(
                        "%s://%s%s",
                        parsedUrl.getProtocol(),
                        parsedUrl.getHost(),
                        parsedUrl.getPort() == -1 ? "" : ":" + parsedUrl.getPort()
                )
                .toLowerCase();

        Url url = UrlRepository.findByName(normalizedUrl).orElse(null);

        if (url == null) {
            UrlRepository.save(new Url(normalizedUrl));
            ctx.sessionAttribute("flash", "Страница успешно добавлена");
            ctx.sessionAttribute("flashType", "success");
        } else {
            ctx.sessionAttribute("flash", "Страница уже существует");
            ctx.sessionAttribute("flashType", "info");
        }
        ctx.redirect(NamedRoutes.urlsPath());
    }

    public static void showUrls(Context ctx) throws SQLException {
        List<Url> urls = UrlRepository.getEntities();
        Map<Integer, UrlCheck> latestChecks = new HashMap<>();
        urls.forEach(o -> {
            try {
                if (!UrlCheckRepository.getByUrlId(o.getId()).isEmpty()) {
                    var a = UrlCheckRepository.getByUrlId(o.getId());
                    latestChecks.put(o.getId(), UrlCheckRepository.getByUrlId(o.getId()).getLast());
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        UrlsPage page = new UrlsPage(urls, latestChecks);
        page.setFlash(ctx.sessionAttribute("flash"));
        page.setFlashType(ctx.sessionAttribute("flashType"));
        ctx.sessionAttribute("flash", null);
        ctx.sessionAttribute("flashType", null);
        ctx.render("urls/index.jte", model("page", page));
    }
}
