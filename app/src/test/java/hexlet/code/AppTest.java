package hexlet.code;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import hexlet.code.repository.UrlCheckRepository;

import hexlet.code.controllers.UrlsController;
import hexlet.code.repository.UrlRepository;
import hexlet.code.util.NamedRoutes;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.testtools.JavalinTest;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.sql.SQLException;

public class AppTest {
    private Javalin app;
    private static MockWebServer mockServer;

    @BeforeEach
    public final void setUp() throws SQLException, IOException {
        app = App.getApp();
    }

    private static Path getFixturePath() {
        return Paths.get("src", "test", "resources", "fixtures", "page.html")
                .toAbsolutePath().normalize();
    }

    private static String readFixture() throws IOException {
        Path filePath = getFixturePath();
        return Files.readString(filePath).trim();
    }

    @BeforeAll
    public static void startMockServer() throws IOException {
        mockServer = new MockWebServer();
        MockResponse mockResponse = new MockResponse()
                .setBody(readFixture());
        mockServer.enqueue(mockResponse);
        mockServer.start();
    }

    @AfterAll
    public static void stopMockServer() throws IOException {
        mockServer.shutdown();
    }

    @Test
    public void testMainPage() {
        JavalinTest.test(app, (server, client) -> {
            var response = client.get("/");
            assertThat(response.code()).isEqualTo(200);
        });
    }

    @Test
    public void testCorrectLink() {
        JavalinTest.test(app, (server, client) -> {
            var requestBody = "url=https://yandex.ru";
            var response = client.post(NamedRoutes.urlsPath(), requestBody);
            assert response.body() != null;
            var responseBody = response.body().string();

            assertThat(response.code()).isEqualTo(200);
            assertThat(responseBody).contains("https://yandex.ru");
            assertThat(UrlRepository.findByName("https://yandex.ru")).isPresent();
            assertThat(UrlRepository.findById(1).get().getName()).isEqualTo("https://yandex.ru");

            Context ctx = mock(Context.class);
            var requestBodySecondary = "url=https://kremlin.ru";
            when(ctx.formParam("url")).thenReturn(requestBodySecondary.substring(4));
            UrlsController.createUrl(ctx);

            verify(ctx).sessionAttribute("flash", "Страница успешно добавлена");
            verify(ctx).sessionAttribute("flashType", "success");
            verify(ctx).redirect(NamedRoutes.urlsPath());
        });
    }

    @Test
    public void testSameLink() {
        JavalinTest.test(app, (server, client) -> {
            var requestBody = "url=https://yandex.ru";
            var response = client.post(NamedRoutes.urlsPath(), requestBody);

            Context ctx = mock(Context.class);
            when(ctx.formParam("url")).thenReturn(requestBody.substring(4));
            UrlsController.createUrl(ctx);

            verify(ctx).sessionAttribute("flash", "Страница уже существует");
            verify(ctx).sessionAttribute("flashType", "info");
            verify(ctx).redirect(NamedRoutes.urlsPath());
        });

    }

    @Test
    public void testIncorrectLink() {
        JavalinTest.test(app, (server, client) -> {
            var requestBody = "url=yandex.ru";
            var response = client.post(NamedRoutes.urlsPath(), requestBody);
            assert response.body() != null;
            var responseBody = response.body().string();

            assertThat(response.code()).isEqualTo(200);
            assertThat(responseBody).doesNotContain("yandex.ru");
            assertThat(UrlRepository.findByName("yandex.ru")).isEmpty();
            assertThat(UrlRepository.findById(1)).isEmpty();
        });
    }

    @Test
    public void testLinkPage() {
        JavalinTest.test(app, (server, client) -> {
            var requestBody = "url=https://yandex.ru";
            var response = client.post(NamedRoutes.urlsPath(), requestBody);
            var responseSecondary = client.get("/urls/1");
            assert responseSecondary.body() != null;
            var responseBody = responseSecondary.body().string();
            assertThat(responseSecondary.code()).isEqualTo(200);
            assertThat(responseBody).contains("https://yandex.ru");
        });
    }

    @Test
    public void testCreateUrlEmptyInput() throws SQLException {
        Context ctx = mock(Context.class);
        when(ctx.formParam("url")).thenReturn("   ");
        UrlsController.createUrl(ctx);
        verify(ctx).sessionAttribute("flash", "Некорректный URL");
        verify(ctx).sessionAttribute("flashType", "danger");
        verify(ctx).redirect(NamedRoutes.rootPath());
    }

    @Test
    public void testCheck() {
        String testUrl = mockServer.url("/").toString().replaceAll("/$", "");
        JavalinTest.test(app, ((server, client) -> {
            var requestBody = "url=" + testUrl;

            // Проверка создания URL
            var response = client.post("/urls", requestBody);
            assertThat(response.code()).isEqualTo(200);

            var actualUrl = UrlRepository.findByName(testUrl).orElse(null);
            assertThat(actualUrl).as("URL should be found").isNotNull();

            // Проверка проверки URL
            client.post("/urls/" + actualUrl.getId() + "/checks");
            response = client.get("/urls/" + actualUrl.getId());
            assertThat(response.code()).isEqualTo(200);

            assert response.body() != null;
            String responseBody = response.body().string();
            assertThat(responseBody).contains(testUrl);

            // Получение последних проверок URL
            var urlCheck = UrlCheckRepository.findLatestChecks().get(actualUrl.getId());
            assertThat(urlCheck).as("URL check should exist").isNotNull();

            // Проверка параметров ответа
            assertThat(urlCheck.getStatusCode()).as("Check response status code").isEqualTo(200);
            assertThat(urlCheck.getTitle()).as("Check title").isEqualTo("Test title");
            assertThat(urlCheck.getH1()).as("Check h1").isEqualTo("Test h1");
            assertThat(urlCheck.getDescription()).as("Check description").isEqualTo("Test description");
        }));
    }

}
