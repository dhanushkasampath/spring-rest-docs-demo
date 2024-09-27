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

4.


run
maven clean package

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