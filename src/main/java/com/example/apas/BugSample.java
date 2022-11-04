package com.example.apas;

import com.example.apas.command.RegisterVisitorCommand;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class BugSample {

    private final CommandGateway commandGateway;

    public BugSample(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void run() {

        log.info("Starting to send commands...");

        log.info("Sending RegisterVisitorCommand...");
        String aggregateIdentifierVisitor = UUID.randomUUID().toString();
        commandGateway.sendAndWait(
                RegisterVisitorCommand.builder()
                        .aggregateIdentifierVisitor(aggregateIdentifierVisitor)
                        .name("Visitor")
                        .build()
        );
        log.info("RegisterVisitorCommand sent");
    }
}
