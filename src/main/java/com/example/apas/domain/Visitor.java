package com.example.apas.domain;

import com.example.apas.command.RegisterVisitorCommand;
import com.example.apas.event.VisitorRegisteredEvent;
import org.axonframework.axonserver.connector.event.axon.AxonServerEventScheduler;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneOffset;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class Visitor {

    @AggregateIdentifier
    protected String aggregateIdentifierVisitor;

    public Visitor() {
        // Empty constructor required for Axon
    }

    @CommandHandler
    public Visitor(RegisterVisitorCommand command, AxonServerEventScheduler eventScheduler) {

        apply(
                VisitorRegisteredEvent.builder()
                        .aggregateIdentifierVisitor(command.getAggregateIdentifierVisitor())
                        .name(command.getName())
                        .build()
        );

        // Fix
//         eventScheduler.start();

        eventScheduler.schedule(
                LocalDate.now().atTime(LocalTime.now().plusMinutes(5)).toInstant(ZoneOffset.UTC),
                VisitorRegisteredEvent.builder()
                        .aggregateIdentifierVisitor(command.getAggregateIdentifierVisitor())
                        .name(command.getName())
                        .build()
        );
    }

    @EventSourcingHandler
    public void on(VisitorRegisteredEvent event) {
        this.aggregateIdentifierVisitor = event.getAggregateIdentifierVisitor();
    }
}
