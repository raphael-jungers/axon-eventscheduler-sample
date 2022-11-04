package com.example.apas.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VisitorRegisteredEvent {

    private String aggregateIdentifierVisitor;

    private String name;
}
