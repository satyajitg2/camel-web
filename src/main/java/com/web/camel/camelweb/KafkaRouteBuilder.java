package com.web.camel.camelweb;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class KafkaRouteBuilder extends RouteBuilder {

    final String KAFKA_ENDPOINT = "kafka:%s?brokers=localhost:9092";

    @Override
    public void configure() throws Exception {
        //Command to Write to Kafka Endpoint as a Producer.
        //./bin/windows/kafka-console-producer.bat --broker-list localhost:9092 --topic javainuse-topic
        //Command to read from Kafka Endpoint as a Consumer
        //./bin/windows/kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic stock-live
        fromF(KAFKA_ENDPOINT, "javainuse-topic")
                .log(LoggingLevel.ERROR, "[${header.kafka.OFFSET}] [${body}]")
                .bean(KafkaMessageEnrich.class)
                .toF(KAFKA_ENDPOINT, "stock-live");

    }

    private class KafkaMessageEnrich {
        //If there is single method, its used in default.
        public String enrich(String message)
        {
            return message + "Enrich with blah blah.......";
        }
    }
}