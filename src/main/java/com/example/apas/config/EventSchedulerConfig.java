package com.example.apas.config;

import org.axonframework.axonserver.connector.AxonServerConnectionManager;
import org.axonframework.axonserver.connector.event.axon.AxonServerEventScheduler;
import org.axonframework.serialization.Serializer;
import org.springframework.context.annotation.Bean;

// Workaround
//@Configuration
public class EventSchedulerConfig {

    @Bean
    public AxonServerEventScheduler axonServerEventScheduler(AxonServerConnectionManager axonServerConnectionManager, Serializer serializer) {

        AxonServerEventScheduler eventScheduler = AxonServerEventScheduler.builder()
                .connectionManager(axonServerConnectionManager)
                .eventSerializer(serializer)
                .build();

        eventScheduler.start();

        return eventScheduler;
    }
}
