/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.acme.aws.lambda;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.apache.camel.builder.RouteBuilder;

@ApplicationScoped
public class CamelRoute extends RouteBuilder {
    @Inject
    KafkaProducerService kafkaProducerService;

    @Override
    public void configure() {        
        from("direct:input").routeId("TestKafkaProducer")
                .log("Inside Camel Route Received Producer Payload ==> ${body}")
                .setBody().body(String.class, s -> kafkaProducerService.produce(s))
                .to("kafka:{{kafka.topic}}?brokers={{kafka.brokers}}")
                .end();
    }
}
