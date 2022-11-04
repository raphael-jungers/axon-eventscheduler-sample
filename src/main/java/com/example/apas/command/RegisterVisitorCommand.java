package com.example.apas.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterVisitorCommand {

    @TargetAggregateIdentifier
    private String aggregateIdentifierVisitor;

    private String name;
}
