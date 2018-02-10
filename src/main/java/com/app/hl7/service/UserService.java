package com.app.hl7.service;

import com.app.hl7.entity.User;
import org.springframework.context.annotation.Bean;

public interface UserService {
    public void addUser(User user);
}
