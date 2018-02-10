package com.app.hl7.jpa;

import com.app.hl7.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, Long>{
}
