package com.app;

import com.app.entities.Friend;
import com.app.entities.Person;
import com.app.repositories.FriendRepository;
import com.app.repositories.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;
import java.util.Optional;
import java.util.UUID;


@Slf4j
@SpringBootTest(
        classes = {AppStart.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ExtendWith(SpringExtension.class)
public class AppTest {
    @Resource
    private PersonRepository personRepository;

    @Resource
    private FriendRepository friendRepository;


    @AfterEach
    public void afterEach() {
        deleteAll();
    }

    @Test
    @DisplayName("测试创建节点")
    public void testCreateNode() {
        Person person =randomCreateNode();
        assert person.getId() != null : "创建数据失败";
        log.info("创建节点数据: {}", person);
    }


    @Test
    @DisplayName("测试创建关系")
    public void testCreateRelationship() {
        Person personA = randomCreateNode();
        Person personB = randomCreateNode();
        Friend friend = new Friend();
        friend.setPersonFrom(personA);
        friend.setPersonTo(personB);
        friendRepository.save(friend);
        assert friend.getId() != null : "创建关系测试未通过";
    }


    @Test
    @DisplayName("测试查询节点")
    public void testFindById() {
        Person person = randomCreateNode();
        Optional<Person> personOptional = personRepository.findById(person.getId());
        assert personOptional.isPresent() : "查询结果失败";
        log.info("通过id查询节点: {}", personOptional.get());
    }

    public Person randomCreateNode() {
        Person person = new Person();
        person.setName("test-" + UUID.randomUUID().toString().substring(0, 4));
        personRepository.save(person);
        return person;
    }

    private void deleteAll() {
        personRepository.deleteAll();
        friendRepository.deleteAll();
    }


}
