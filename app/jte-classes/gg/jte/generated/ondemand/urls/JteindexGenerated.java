package gg.jte.generated.ondemand.urls;
import hexlet.code.dto.UrlsPage;
import hexlet.code.util.NamedRoutes;
@SuppressWarnings("unchecked")
public final class JteindexGenerated {
	public static final String JTE_NAME = "urls/index.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,2,2,2,4,4,7,7,20,20,22,22,22,23,23,23,23,23,23,23,23,23,23,23,23,27,27,33,33,33,33,33,2,2,2,2};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, UrlsPage page) {
		jteOutput.writeContent("\r\n");
		gg.jte.generated.ondemand.layout.JtepageGenerated.render(jteOutput, jteHtmlInterceptor, page, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\r\n    <main class=\"flex-grow-1\">\r\n        <section>\r\n            <table class=\"table table-bordered table-hover mt-3\">\r\n                <thead>\r\n                <tr>\r\n                    <th scope=\"col-1\">ID</th>\r\n                    <th>Имя</th>\r\n                    <th scope=\"col-2\">Последняя проверка</th>\r\n                    <th scope=\"col-1\">Код ответа</th>\r\n                </tr>\r\n                </thead>\r\n                <tbody>\r\n                ");
				for (int i = 0; i < page.getUrls().size(); i++) {
					jteOutput.writeContent("\r\n                    <tr>\r\n                        <th scope=\"row\">");
					jteOutput.setContext("th", null);
					jteOutput.writeUserContent(page.getUrls().get(i).getId());
					jteOutput.writeContent("</th>\r\n                        <td><a");
					var __jte_html_attribute_0 = NamedRoutes.urlPath(page.getUrls().get(i).getId());
					if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
						jteOutput.writeContent(" href=\"");
						jteOutput.setContext("a", "href");
						jteOutput.writeUserContent(__jte_html_attribute_0);
						jteOutput.setContext("a", null);
						jteOutput.writeContent("\"");
					}
					jteOutput.writeContent(">");
					jteOutput.setContext("a", null);
					jteOutput.writeUserContent(page.getUrls().get(i).getName());
					jteOutput.writeContent("</a></td>\r\n                        <td></td>\r\n                        <td></td>\r\n                    </tr>\r\n                ");
				}
				jteOutput.writeContent("\r\n                </tbody>\r\n            </table>\r\n\r\n        </section>\r\n    </main>\r\n");
			}
		});
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		UrlsPage page = (UrlsPage)params.get("page");
		render(jteOutput, jteHtmlInterceptor, page);
	}
}
