package com.web.camel.camelweb;

import com.web.camel.camelweb.beans.BeanService;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.model.rest.RestParamType;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.MediaType;

@Component
public class FourthRouteForSwagger extends RouteBuilder {

  @Override
  public void configure() throws Exception {
    restConfiguration().component("servlet").host("localhost").port(8080).bindingMode(RestBindingMode.json);

    rest().get("/swagger").produces(org.springframework.http.MediaType.APPLICATION_JSON_VALUE).route()
            .setBody(constant("Welcome Swagger"));

    //http://localhost:8080/camel/fourthRoute/say/hello
    rest("/fourthRoute/say")
            .get("/hello").to("direct:fourthRoute/hello")
            .get("/bye").consumes("application/json").to("direct:fourthRoute/bye")
            .post("/bye").to("mock:update");

    //http://localhost:8080/camel/say/bye
    from("direct:fourthRoute/hello")
            .transform().constant("Hello World Route 4");

    //http://localhost:8080/camel/say/bye
    from("direct:fourthRoute/bye")
            .transform().constant("Bye World Route 4");

    //http://localhost:8080/camel/name/availability/company?proposedName={helloname}
    rest("/name/availability")
        .description("Search for name availability from ASIC")
        .id("rest-nameAvailability")
        .produces(MediaType.APPLICATION_JSON)
        .skipBindingOnErrorCode(true)
        .get("/company?proposedName={proposedName}")
        .param().name("proposedName").type(RestParamType.query).description("The proposed name of the company").required(true).dataType("string").endParam()
        .param().name("UID").type(RestParamType.header).description("UID").required(true).dataType("string").endParam()
        .param().name("sessionID").type(RestParamType.header).description("sessionID").required(true).dataType("string").endParam()
        .param().name("requestID").type(RestParamType.header).description("requestID").required(true).dataType("string").endParam()
        //.responseMessage().code(200).responseModel(QueryNameResponse.class).endResponseMessage() //OK
        //.responseMessage().code(500).responseModel(APIErrorResponse.class).endResponseMessage() //Not-OK
        .responseMessage().code(200).endResponseMessage()
        .route()
        .validate(header("proposedName").isNotNull())
        .validate(header("UID").isNotNull())
        .validate(header("sessionID").isNotNull())
        .validate(header("requestID").isNotNull())
        .to("direct:nameAvailabilityRoute");

    from("direct:nameAvailabilityRoute")
        .routeId("nameAvailabilityAsicRouteId")
        .log("Searching for name availability ${header.proposedName}")
        .setBody(simple("${header.proposedName}"))
        .bean(BeanService.class, "beanServiceHelloWorld('${body}')")
        //.to("cxf:bean:queryNameEndpoint")
        .process(new BasicProcessor())
        .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(200));


  }


}
