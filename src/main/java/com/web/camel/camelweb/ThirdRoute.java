package com.web.camel.camelweb;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class ThirdRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        restConfiguration().component("servlet").host("localhost").port(8080).bindingMode(RestBindingMode.json);
        rest().get("/thirdRoute").produces(MediaType.APPLICATION_JSON_VALUE).route().setBody(constant("Welcome we " +
                "are now into the third Route Logic.."));

        //http://localhost:8080/camel/thirdRoute/say/hello
        rest("/thirdRoute/say")
                .get("/hello").to("direct:thirdRoute/hello")
                .get("/bye").consumes("application/json").to("direct:thirdRoute/bye")
                .post("/bye").to("mock:update");

        //http://localhost:8080/camel/say/bye
        from("direct:thirdRoute/hello")
                .transform().constant("Hello World Route 3");

        //http://localhost:8080/camel/say/bye
        from("direct:thirdRoute/bye")
                .transform().constant("Bye World Route 3");
    }
}
