Let's see how to document our rest api using "Spring REST Docs"

Why not swagger?
===============

Because its a 3rd party tool and it has some limitations
Spring REST Docs helps to produce documentation to your endpoints which are accurate and concise.

Eg:
Swagger can not handle models which have circular dependencies

STEPS OF IMPLEMENTATION
=======================

1. Add the dependency "spring-restdocs-mockmvc" and "spring-web"

2. Then implement the rest endpoints you want with needed models/service and controller

3. Then write unit tests for each

4. Specify the directory to generate .adoc files inside test classes.
   we use @AutoConfigureRestDocs(outputDir = "target/generated-snippets") for that

5. At the end of the unit test use .andDo() method and provide details as follows

    @Test
    public void testGetOrders() throws Exception {
        mockMvc.perform(get("/products/all")
            .contentType("application/json")).andDo(print())
            .andExpect(status().isOk())
            .andDo(document("{methodName}",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint())));
    }
6. Then run the test cases. After that you will see .adoc files will be generated in "target/generated-snippets" location.
Those files include curl, http request, response, request-body, response-body.

7. But it will generate those files for each test case. that is a distract for the user.
Instead, we can generate a html file containing all the documentations. For that create a file called index.adoc and add it in a package
src/java/main/asciidoc

The content of it is in that particular file

After that run
"mvn clean package"

Then you will see a html file has generated in "target/generated-doc/index.adoc" location. open it in browser. That is
the spring-rest-doc we are talking in this demo(see img-1.png in README dir).
This is a static content and that's the disadvantage of this over swagger

PROS OF SPRING REST DOCS
========================

1. Accuracy and reliability:
REST Docs generates documentation based on actual integration tests, ensuring the documentation
is always up to date and consistent with your real API behavior.

2. Customizable:
You can tailor the output to match your style guide or preferred format (HTML, Markdown, Asciidoctor, etc.).

3. Lightweight:
Since it doesn't require running a UI or additional services like Swagger does, it's simpler and lightweight in
production environments.

4. Test-driven documentation:
By coupling documentation generation with tests, you're encouraged to write comprehensive test cases, improving
the quality of both your API and the docs.

CONS OF SPRING REST DOCS
========================

1. No interactive API testing:
Unlike Swagger, you can't interact with the API directly from the documentation (unless you integrate external tools like Postman).

2. More setup:
REST Docs requires writing additional tests (usually with MockMvc) to generate the documentation, which may require
more effort up front.

If testing through the documentation is a priority, Swagger might be better suited. Some teams use both tools:
Spring REST Docs for well-structured documentation and Swagger for interactive testing in development environments.