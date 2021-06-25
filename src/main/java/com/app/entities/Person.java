package com.app.entities;

import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;


@Data
@NodeEntity(label = "Person")
public class Person {

    @Id
    @GeneratedValue
    private Long id;

    @Property(name = "name")
    private String name;
}
