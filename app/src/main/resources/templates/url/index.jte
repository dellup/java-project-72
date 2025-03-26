@import hexlet.code.dto.UrlPage
@import hexlet.code.repository.UrlCheckRepository
@import hexlet.code.util.NamedRoutes
@param UrlPage page

@template.layout.page(
page = page,
content = @`
    <main class="flex-grow-1">
        <section class="p-4">
            <h1>Сайт: ${page.getUrl().getName()}</h1>
            <table class="table table-hover table-bordered">
                <tbody>
                <tr>
                    <th scope="row">ID</th>
                    <td>${page.getUrl().getId()}</td>
                </tr>
                <tr>
                    <th scope="row">Имя</th>
                    <td>${page.getUrl().getName()}</td>
                </tr>
                <tr>
                    <th scope="row">Дата создания</th>
                    <td>${page.getUrl().getCreatedAt().toString()}</td>
                </tr>
                </tbody>
            </table>
        </section>
        <section class="p-4">
            <h2>Проверки: </h2>
            <form action="${NamedRoutes.urlCheckPath(page.getUrl().getId())}" method="post" class="rss-form text-body">
                <div class="col-auto">
                    <button type="submit" class="h-100 btn btn-lg btn-info px-sm-5">Запустить проверку</button>
                </div>
            </form>
            <table class="table table-bordered table-hover mt-3">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Код ответа</th>
                    <th>title</th>
                    <th>h1</th>
                    <th>description</th>
                    <th>Дата проверки</th>
                </tr>
                </thead>
                <tbody>
                @if(!page.getCheck().isEmpty())
                    @for(int i = 0; i < page.getCheck().size(); i++)
                        <tr>
                            <th scope="row">${page.getCheck().get(i).getId()}</th>
                            <td>${page.getCheck().get(i).getStatusCode()}</td>
                            <td>${page.getCheck().get(i).getTitle()}</td>
                            <td>${page.getCheck().get(i).getH1()}</td>
                            <td>${page.getCheck().get(i).getDescription()}</td>
                            <td>${page.getCheck().get(i).getCreatedAt().toString()}</td>
                        </tr>
                    @endfor
                @endif

                </tbody>
            </table>
        </section>
    </main>
`
)