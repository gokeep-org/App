package com.app.entities;

import lombok.Data;
import org.neo4j.ogm.annotation.*;

@Data
@RelationshipEntity
public class Friend {
    @Id
    @GeneratedValue
    private Long id;

    @StartNode
    private Person personFrom;

    @EndNode
    private Person personTo;
}
