package com.web.camel.camelweb;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MyFirstRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("file:files/input")
                .log("${body}")
                .setBody(body().append("Hello This is appended string."))
                .transform(simple("${body.replace('Satyajit','Sanjay')}"))
                .log("${body}")
                //.setBody(body().contains(("Satyajit").replace("Satyajit", "Sanjay"))
                .to("file:files/output");
    }
}