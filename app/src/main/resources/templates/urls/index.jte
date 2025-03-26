@import hexlet.code.dto.UrlsPage
@import hexlet.code.util.NamedRoutes
@param UrlsPage page

@template.layout.page(
page = page,
content = @`
    <main class="flex-grow-1">
        <section>
            <table class="table table-bordered table-hover mt-3 p-3">
                <thead>
                <tr>
                    <th scope="col-1">ID</th>
                    <th>Имя</th>
                    <th scope="col-2">Последняя проверка</th>
                    <th scope="col-1">Код ответа</th>
                </tr>
                </thead>
                <tbody>
                @for(int i = 0; i < page.getUrls().size(); i++)
                    <tr>
                        <th scope="row">${page.getUrls().get(i).getId()}</th>
                        <td><a href="${NamedRoutes.urlPath(page.getUrls().get(i).getId())}">${page.getUrls().get(i).getName()}</a></td>
                        <td>@if(!page.getLatestChecks().isEmpty() && page.getLatestChecks().get(page.getUrls().get(i).getId()) != null)
                                ${page.getLatestChecks().get(page.getUrls().get(i).getId()).getCreatedAt().toString()}
                            @endif</td>
                        <td>@if(!page.getLatestChecks().isEmpty() && page.getLatestChecks().get(page.getUrls().get(i).getId()) != null)
                                ${page.getLatestChecks().get(page.getUrls().get(i).getId()).getStatusCode()}
                            @endif</td>
                    </tr>
                @endfor
                </tbody>
            </table>

        </section>
    </main>
`
)