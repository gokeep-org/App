package com.app.rest;

import javax.annotation.Resource;
import javax.ws.rs.*;

import com.app.action.factory.TestActionFactory;
import com.app.entities.Friend;
import com.app.repositories.FriendRepository;
import com.app.entities.Person;
import com.app.repositories.PersonRepository;
import com.app.domain.output.test.TestInfoOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping
public class TestRest {
    private static final Logger logger = LoggerFactory.getLogger(TestRest.class);

    @Resource
    private PersonRepository personRepository;

    @Resource
    private FriendRepository friendRepository;

    @GetMapping("/info")
    public TestInfoOutput test(@QueryParam("flag") boolean flag) throws Exception {
        Person person = new Person();
        person.setName("张三" + UUID.randomUUID().toString().substring(0, 5));
        personRepository.save(person);
        return TestActionFactory.getTestInfoAction(flag).execute();
    }

    @GetMapping("/info2")
    public TestInfoOutput test2(@QueryParam("flag") boolean flag) throws Exception {
        Friend friend = new Friend();
        Optional<Person> person1Optional = personRepository.findById(3L);
        Optional<Person> person2Optional = personRepository.findById(1L);
        friend.setPersonFrom(person1Optional.get());
        friend.setPersonTo(person2Optional.get());
        friendRepository.save(friend);
        return TestActionFactory.getTestInfoAction(flag).execute();
    }

    @PostMapping("/hook")
    public String hookTest(Map<String, Object> body){
        if (body != null)
            logger.info("receiver result is{}", body.toString());
        return "receiver hook result";
    }
}
