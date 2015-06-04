package com.example.rest.example.services;

@RunWith(Arquillian.class)
public class BookTest {

    @Deployment(testable = false, name = "bookservice")
    public static WebArchive createDeploymentBookInfoService() {
        return ShrinkWrap.create(WebArchive.class, "bookservice.war").addClasses(BookInfoService.class, ApplicationResource.class);
    }

    @Deployment(testable = false, name = "bookcomments")
    public static WebArchive createDeploymentCommentsService() {
        return ShrinkWrap.create(WebArchive.class, "bookcomments.war").addClasses(CommentsService.class, ApplicationResource.class);
    }

    @Deployment(testable = false, name = "book")
    public static WebArchive createDeploymentBookService() {
        WebArchive webArchive = ShrinkWrap.create(WebArchive.class, "book.war").addClasses(BookService.class, ApplicationResource.class)
                .addAsLibraries(Maven.resolver().loadPomFromFile("pom.xml").resolve("com.netflix.rxjava:rxjava-core").withTransitivity().as(JavaArchive.class));
        return webArchive;
    }

    @ArquillianResource
    URL base;

    @Test
    @OperateOnDeployment("book")
    public void should_return_book() throws MalformedURLException {

        Client client = ClientBuilder.newClient();
        JsonObject book = client.target(URI.create(new URL(base, "rest/").toExternalForm())).path("book/1111").request().get(JsonObject.class);

        //assertions
    }
}
