package org.acme.aws.lambda;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class KafkaProducerService {

    public String produce(String kafkaMessage) {
        System.out.println(kafkaMessage);
        kafkaMessage = "Hello World!"; // for demo
        return String.format("%s", kafkaMessage);
    }
}