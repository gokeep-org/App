package com.app.domain;

import org.neo4j.ogm.annotation.*;

@RelationshipEntity
public class Friend {
    @Id
    @GeneratedValue
    private Long id;

    @StartNode
    private Person personFrom;

    @EndNode
    private Person personTo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getPersonFrom() {
        return personFrom;
    }

    public void setPersonFrom(Person personFrom) {
        this.personFrom = personFrom;
    }

    public Person getPersonTo() {
        return personTo;
    }

    public void setPersonTo(Person personTo) {
        this.personTo = personTo;
    }
}
