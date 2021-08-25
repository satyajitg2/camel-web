package com.web.camel.camelweb;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BasicProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        BasicResponse response = new BasicResponse();
        String exBody = (String) exchange.getIn().getBody();
 /*       QueryNameResponse response = new QueryNameResponse();
        MessageContentsList messageContentsList = (MessageContentsList) exchange.getIn().getBody();
        QueryNameAvailabilityReplyType replyType = (QueryNameAvailabilityReplyType) messageContentsList.get(0);
        NameAvailabilityResponseType responseType = replyType.getBusinessDocumentBody();

 */
        response.setCode("200");
        response.setShortDescription("Some description");
        response.setLongDescription("Some long description.");
        exchange.getOut().setBody(response);
    }


}