@import hexlet.code.dto.MainPage
@import hexlet.code.util.NamedRoutes
@param MainPage page

@template.layout.page(
    page = page,
    content = @`
        <main class="flex-grow-1">
            <section>
                <div class="bg-dark container-fluid p-5">
                    <div class="row">
                        <div class="col-md-10 col-lg-8 mx-auto text-white">
                            <h1 class="display-3 mb-0">Анализатор страниц</h1>
                            <p class="lead">Бесплатно проверяйте сайты на SEO пригодность</p>
                            <form action="${NamedRoutes.urlsPath()}" method="post" class="rss-form text-body">
                                <div class="form-floating mb-3">
                                    <input class="form-control" type="search" id="linkInput" placeholder="Ссылка" name="url">
                                    <label for="linkInput">Ссылка</label>
                                </div>
                                <div class="col-auto">
                                    <button type="submit" class="h-100 btn btn-lg btn-info px-sm-5">Проверить</button>
                                </div>
                            </form>
                            <p class="text-secondary">Пример: https://google.com</p>
                        </div>
                    </div>
                </div>
            </section>
        </main>
    `
)