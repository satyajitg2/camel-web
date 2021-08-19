package com.web.camel.camelweb;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class SecondRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        restConfiguration().component("servlet").host("localhost").port(8080).bindingMode(RestBindingMode.json);
        rest().get("/hello-world").produces(MediaType.APPLICATION_JSON_VALUE).route().setBody(constant("Welcome there is no exception now."));

        rest("/say")
                .get("/hello").to("direct:hello")
                .get("/bye").consumes("application/json").to("direct:bye")
                .post("/bye").to("mock:update");

        //http://localhost:8080/camel/say/bye
        from("direct:hello")
                .transform().constant("Hello World Route1");

        //http://localhost:8080/camel/say/bye
        from("direct:bye")
                .transform().constant("Bye World Route 2");
    }
}
