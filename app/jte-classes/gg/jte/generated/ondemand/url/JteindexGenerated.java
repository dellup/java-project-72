package gg.jte.generated.ondemand.url;
import hexlet.code.dto.UrlPage;
import hexlet.code.repository.UrlCheckRepository;
import hexlet.code.util.NamedRoutes;
@SuppressWarnings("unchecked")
public final class JteindexGenerated {
	public static final String JTE_NAME = "url/index.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,3,3,3,3,5,5,8,8,11,11,11,16,16,16,20,20,20,24,24,24,31,31,31,31,31,31,31,31,31,48,48,49,49,51,51,51,52,52,52,53,53,53,54,54,54,55,55,55,56,56,56,58,58,59,59,65,65,65,65,65,3,3,3,3};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, UrlPage page) {
		jteOutput.writeContent("\r\n");
		gg.jte.generated.ondemand.layout.JtepageGenerated.render(jteOutput, jteHtmlInterceptor, page, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\r\n    <main class=\"flex-grow-1\">\r\n        <section class=\"p-4\">\r\n            <h1>Сайт: ");
				jteOutput.setContext("h1", null);
				jteOutput.writeUserContent(page.getUrl().getName());
				jteOutput.writeContent("</h1>\r\n            <table class=\"table table-hover table-bordered\">\r\n                <tbody>\r\n                <tr>\r\n                    <th scope=\"row\">ID</th>\r\n                    <td>");
				jteOutput.setContext("td", null);
				jteOutput.writeUserContent(page.getUrl().getId());
				jteOutput.writeContent("</td>\r\n                </tr>\r\n                <tr>\r\n                    <th scope=\"row\">Имя</th>\r\n                    <td>");
				jteOutput.setContext("td", null);
				jteOutput.writeUserContent(page.getUrl().getName());
				jteOutput.writeContent("</td>\r\n                </tr>\r\n                <tr>\r\n                    <th scope=\"row\">Дата создания</th>\r\n                    <td>");
				jteOutput.setContext("td", null);
				jteOutput.writeUserContent(page.getUrl().getCreatedAt().toString());
				jteOutput.writeContent("</td>\r\n                </tr>\r\n                </tbody>\r\n            </table>\r\n        </section>\r\n        <section class=\"p-4\">\r\n            <h2>Проверки: </h2>\r\n            <form");
				var __jte_html_attribute_0 = NamedRoutes.urlCheckPath(page.getUrl().getId());
				if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
					jteOutput.writeContent(" action=\"");
					jteOutput.setContext("form", "action");
					jteOutput.writeUserContent(__jte_html_attribute_0);
					jteOutput.setContext("form", null);
					jteOutput.writeContent("\"");
				}
				jteOutput.writeContent(" method=\"post\" class=\"rss-form text-body\">\r\n                <div class=\"col-auto\">\r\n                    <button type=\"submit\" class=\"h-100 btn btn-lg btn-info px-sm-5\">Запустить проверку</button>\r\n                </div>\r\n            </form>\r\n            <table class=\"table table-bordered table-hover mt-3\">\r\n                <thead>\r\n                <tr>\r\n                    <th>ID</th>\r\n                    <th>Код ответа</th>\r\n                    <th>title</th>\r\n                    <th>h1</th>\r\n                    <th>description</th>\r\n                    <th>Дата проверки</th>\r\n                </tr>\r\n                </thead>\r\n                <tbody>\r\n                ");
				if (!page.getCheck().isEmpty()) {
					jteOutput.writeContent("\r\n                    ");
					for (int i = 0; i < page.getCheck().size(); i++) {
						jteOutput.writeContent("\r\n                        <tr>\r\n                            <th scope=\"row\">");
						jteOutput.setContext("th", null);
						jteOutput.writeUserContent(page.getCheck().get(i).getId());
						jteOutput.writeContent("</th>\r\n                            <td>");
						jteOutput.setContext("td", null);
						jteOutput.writeUserContent(page.getCheck().get(i).getStatusCode());
						jteOutput.writeContent("</td>\r\n                            <td>");
						jteOutput.setContext("td", null);
						jteOutput.writeUserContent(page.getCheck().get(i).getTitle());
						jteOutput.writeContent("</td>\r\n                            <td>");
						jteOutput.setContext("td", null);
						jteOutput.writeUserContent(page.getCheck().get(i).getH1());
						jteOutput.writeContent("</td>\r\n                            <td>");
						jteOutput.setContext("td", null);
						jteOutput.writeUserContent(page.getCheck().get(i).getDescription());
						jteOutput.writeContent("</td>\r\n                            <td>");
						jteOutput.setContext("td", null);
						jteOutput.writeUserContent(page.getCheck().get(i).getCreatedAt().toString());
						jteOutput.writeContent("</td>\r\n                        </tr>\r\n                    ");
					}
					jteOutput.writeContent("\r\n                ");
				}
				jteOutput.writeContent("\r\n\r\n                </tbody>\r\n            </table>\r\n        </section>\r\n    </main>\r\n");
			}
		});
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		UrlPage page = (UrlPage)params.get("page");
		render(jteOutput, jteHtmlInterceptor, page);
	}
}
