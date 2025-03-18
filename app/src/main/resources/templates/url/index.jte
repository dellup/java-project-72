@import hexlet.code.dto.UrlPage
@param UrlPage page

@template.layout.page(
page = page,
content = @`
    <main class="flex-grow-1">
        <section>
            ${page.getUrl().getName()}
        </section>
    </main>
`
)