package com.app;

import com.app.hl7.entity.User;
import com.app.hl7.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserTest {
    @Autowired
    private UserService userService;

    @Test
    public void testAddUser(){
        User user = new User();
        user.setUsername("徐宁");
        userService.addUser(user);
    }
}
