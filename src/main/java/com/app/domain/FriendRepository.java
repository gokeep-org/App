package com.app.domain;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRepository extends Neo4jRepository<Friend, Long> {
}
