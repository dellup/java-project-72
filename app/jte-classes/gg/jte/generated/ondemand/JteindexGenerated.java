package gg.jte.generated.ondemand;
import hexlet.code.dto.MainPage;
import hexlet.code.util.NamedRoutes;
@SuppressWarnings("unchecked")
public final class JteindexGenerated {
	public static final String JTE_NAME = "index.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,2,2,2,4,4,7,7,15,15,15,15,15,15,15,15,15,30,30,30,30,30,2,2,2,2};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, MainPage page) {
		jteOutput.writeContent("\r\n");
		gg.jte.generated.ondemand.layout.JtepageGenerated.render(jteOutput, jteHtmlInterceptor, page, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\r\n        <main class=\"flex-grow-1\">\r\n            <section>\r\n                <div class=\"bg-dark container-fluid p-5\">\r\n                    <div class=\"row\">\r\n                        <div class=\"col-md-10 col-lg-8 mx-auto text-white\">\r\n                            <h1 class=\"display-3 mb-0\">Анализатор страниц</h1>\r\n                            <p class=\"lead\">Бесплатно проверяйте сайты на SEO пригодность</p>\r\n                            <form");
				var __jte_html_attribute_0 = NamedRoutes.urlsPath();
				if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
					jteOutput.writeContent(" action=\"");
					jteOutput.setContext("form", "action");
					jteOutput.writeUserContent(__jte_html_attribute_0);
					jteOutput.setContext("form", null);
					jteOutput.writeContent("\"");
				}
				jteOutput.writeContent(" method=\"post\" class=\"rss-form text-body\">\r\n                                <div class=\"form-floating mb-3\">\r\n                                    <input class=\"form-control\" type=\"search\" id=\"linkInput\" placeholder=\"Ссылка\" name=\"url\">\r\n                                    <label for=\"linkInput\">Ссылка</label>\r\n                                </div>\r\n                                <div class=\"col-auto\">\r\n                                    <button type=\"submit\" class=\"h-100 btn btn-lg btn-info px-sm-5\">Проверить</button>\r\n                                </div>\r\n                            </form>\r\n                            <p class=\"text-secondary\">Пример: https://google.com</p>\r\n                        </div>\r\n                    </div>\r\n                </div>\r\n            </section>\r\n        </main>\r\n    ");
			}
		});
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		MainPage page = (MainPage)params.get("page");
		render(jteOutput, jteHtmlInterceptor, page);
	}
}
